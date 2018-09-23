package com.hlws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hlws.dal.IBuiltyDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.model.Builty;

@Service
public class BuiltyService {

	@Autowired
	IBuiltyDAL builtyRepository;

	public String createBuilty(Builty builty) throws Exception{
		builtyRepository.save(builty);

		//remove from temp if present
		builtyRepository.removeFromTemp(builty);
		return "";
	}

	public Builty saveBuilty(Builty builty) throws Exception{
		if(builty.getSavedReferenceNumber() > -1) {
			Long random = new Random().nextLong();
			builty.setSavedReferenceNumber(random);
		}
		builtyRepository.saveTemp(builty);
		return builty;
	}
	
	public List<Builty> getBuiltyList(String filterParam) throws Exception{
		List<Builty> list = new ArrayList<>();
		list = builtyRepository.findRunning();
		return list;
	}
	
	public void updateReceipt(List<Builty> builtyList) throws Exception{
		
	}
}
