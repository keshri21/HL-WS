package com.hlws.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPermitDAL;
import com.hlws.model.Permit;
import com.hlws.util.AppUtil;

@Service
public class PermitService {
	
	@Autowired
	IPermitDAL permitRepository;
	
	public Permit create(Permit permit) {
		permit.setCreateddate(new Date());
		permit.setCreatedby(AppUtil.getLoggedInUser().getUsername());
		permitRepository.save(permit);
		return permit;
	}
	
	public Permit update(Permit permit) {
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

}
