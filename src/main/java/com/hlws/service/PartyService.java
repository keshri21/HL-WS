package com.hlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPartyDAL;
import com.hlws.model.Party;

@Service
public class PartyService {

	@Autowired
	IPartyDAL partyRepository;
	
	public Party save(Party party, boolean createFlag) {
		if(null != party) {
			party.setName(party.getName().toUpperCase());
			party.getDestinations().forEach(item -> item = item.toUpperCase());
		}
		return partyRepository.save(party);
	}
	
	public List<Party> getAll(){
		return partyRepository.getAll();
	}
	
	public Party getOne(Integer id) {
		return partyRepository.findById(id);
	}
	
	
}
