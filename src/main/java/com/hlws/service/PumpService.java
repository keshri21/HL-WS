package com.hlws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hlws.dal.IPumpDAL;
import com.hlws.model.StringRefData;

@Service
public class PumpService {
	
	@Autowired
	IPumpDAL pumpRepository;
	
	@CacheEvict(value="pumps", allEntries=true)
	public void createOrupdate(StringRefData pump) {
		pumpRepository.save(pump);
	}
	
	@CacheEvict(value="pumps", allEntries=true)
	public void remove(StringRefData pump) {
		pumpRepository.delete(pump);
	}
	
	@Cacheable("pumps")
	public List<StringRefData> getAll() {
		return pumpRepository.getList();
	}

}
