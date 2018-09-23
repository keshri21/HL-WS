package com.hlws.dal;

import java.util.List;

import com.hlws.model.Party;

public interface IPartyDAL {
	
	Party save(Party party);
	List<Party> getAll();
	List<Party> findByPartyName(String name);
	Party findById(String id);
	void delete(Party party);
}
