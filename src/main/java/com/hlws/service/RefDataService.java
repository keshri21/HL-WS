package com.hlws.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPartyDAL;
import com.hlws.model.RefData;

@Service
public class RefDataService {
	
	@Autowired
	IPartyDAL partyRepository;
	
	public RefData getRefData(){
		//get party list
		RefData refData = new RefData();
		refData.setPartyList(partyRepository.getAll());
		//TODO add area and collary list in mongo and get it form db. See ig there is any relation between area and collary
		refData.setAreaList(Arrays.asList("BHATGAON", "HASDEO", "BISRAMPUR"));
		refData.setCollaryList(Arrays.asList("MAHAN II", "DIPKA EXP", "GEVRA OC"));
		return refData;
	}

}
