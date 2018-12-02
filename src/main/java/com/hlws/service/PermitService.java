package com.hlws.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPermitDAL;
import com.hlws.exceptions.InvalidDataException;
import com.hlws.model.Permit;
import com.hlws.util.AppUtil;

@Service
public class PermitService {
	
	@Autowired
	IPermitDAL permitRepository;
	
	public Permit create(Permit permit) throws InvalidDataException{
		validatePermit(permit, false);
		permit.setPermitbalance(permit.getQuantity());
		permit.setCreateddate(new Date());
		permit.setCreatedby(AppUtil.getLoggedInUser().getUsername());
		permitRepository.save(permit);
		return permit;
	}
	
	public Permit update(Permit permit) throws Exception{
		validatePermit(permit, true);
		permit.setLastmodifiedby(AppUtil.getLoggedInUser().getUsername());
		permit.setLastmodifieddate(new Date());
		permitRepository.save(permit);
		return permit;
	}
	
	public List<Permit> getPermitList(String filter) {
		switch (filter) {
			case "all":
				return permitRepository.getAll();
			default:
				return permitRepository.getActive();
		}
	}
	
	public Permit getByPermitNumber(Long permitNo) {
		return permitRepository.getOne(permitNo);
	}
	
	private void validatePermit(Permit permit, boolean isUpdate) throws InvalidDataException{
		if(isUpdate && null == permit.getId()) {
			throw new InvalidDataException("Permit ID is missing");
		}else if(null == permit.getQuantity() || permit.getQuantity() <= 0) {
			throw new InvalidDataException("Permit quantity must be specified");
		}else if(null == permit.getEnddate()) {
			throw new InvalidDataException("Permit end date must be specified");
		}
	}

}
