package com.hlws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hlws.dal.IBuiltyDAL;
import com.hlws.dto.BuiltyDTO;

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
		switch(filterParam) {
			case "all": list = builtyRepository.getAll();
						break;
			case "completed": 	list = builtyRepository.findCompleted();
								break; 
			default:	list = builtyRepository.findRunning(); 
		}
		
		return list;
	}
	public void update(Builty builty) {
		builtyRepository.save(builty);
	}
	
	public Builty getOne(String id) {
		return builtyRepository.findById(id);
	}
	
	public void approve(String id) {
		builtyRepository.approve(id);
	}
	
	public void updateReceipt(List<BuiltyDTO> builtyList) throws Exception{
		builtyRepository.updateReceipt(builtyList);
	}
	
	public List<Builty> getAllFromTemp() {
		return builtyRepository.getAllFromTemp();
	}
	
	public Builty getOneFromTemp(String id) {
		return builtyRepository.getOneFromTemp(id);
	}
	
	public List<Builty> getAllSelected(List<String> ids){
		return builtyRepository.getAllSelected(ids);
	}
}
