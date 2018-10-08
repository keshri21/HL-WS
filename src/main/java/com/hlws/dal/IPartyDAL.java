package com.hlws.dal;

import java.util.List;

import com.hlws.model.Party;

public interface IPartyDAL extends IBaseDAL {
	
	Party save(Party party);
	List<Party> getAll();
	List<Party> findByPartyName(String name);
	Party findById(Integer id);
	void delete(Party party);
}
