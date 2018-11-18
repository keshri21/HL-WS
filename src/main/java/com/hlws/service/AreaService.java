package com.hlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hlws.dal.IAreaDAL;
import com.hlws.model.Area;

@Service
public class AreaService {
	
	@Autowired
	IAreaDAL areaRepository;

	public Area createArea(Area area) throws Exception{
		if(StringUtils.isEmpty(area.getName())) {
			throw new Exception("Area name is required field");
		}
		area.setName(area.getName().toUpperCase());
		return areaRepository.save(area);
	}
	
	public List<Area> getAll() {
		List<Area> arealist = areaRepository.getAll(); 
		return arealist == null ? new ArrayList<>() : arealist;
	}
	
	public Area updateArea(Area area) throws Exception{
		if(StringUtils.isEmpty(area.getId())) {
			throw new Exception("Area can't be updated since area id not present");
		}else if(StringUtils.isEmpty(area.getName())) {
			throw new Exception("Area name is required field");
		}
		area.setName(area.getName().toUpperCase());
		return areaRepository.save(area);
	}
	public void delete(Area area) throws Exception{
		if(StringUtils.isEmpty(area.getId())) {
			throw new Exception("Area can't be deleted since area id not present");
		}
		areaRepository.delete(area);
	}
}
