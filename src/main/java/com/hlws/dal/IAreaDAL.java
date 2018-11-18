package com.hlws.dal;

import java.util.List;

import com.hlws.model.Area;

public interface IAreaDAL {

	Area save(Area area);
	List<Area> getAll();
	void delete(Area area);
}
