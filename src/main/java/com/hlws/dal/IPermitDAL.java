package com.hlws.dal;

import java.util.List;

import com.hlws.model.Permit;

public interface IPermitDAL extends IBaseDAL{

	Permit save(Permit permit);
	List<Permit> getAll();
	List<Permit> getActive();
	Permit getOne(Long permitNo);
	void updatePermitBalance(Long permitNo, Double balance);
	void delete(Permit permit);
}
