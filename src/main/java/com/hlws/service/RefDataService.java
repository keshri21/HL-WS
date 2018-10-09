package com.hlws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IRefDataDAL;
import com.hlws.model.RefData;

@Service
public class RefDataService {
	
	@Autowired
	IRefDataDAL refDataRepository;
	public RefData getRefData(){
		
		return refDataRepository.get();
	}

}
