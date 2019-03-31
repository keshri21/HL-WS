package com.hlws.dal;

import java.util.List;

import com.hlws.model.StringRefData;

public interface IPumpDAL extends IBaseDAL{
	public void save(StringRefData pump);
	public void delete(StringRefData pump);
	public List<StringRefData> getList();
}
