package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hlws.model.Area;

@Repository
public class AreaDALImpl implements IAreaDAL {
	
	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "area";
	
	@Autowired
	public AreaDALImpl(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	@CacheEvict(value="areas", allEntries=true)
	public Area save(Area area) {
		mongoTemplate.save(area, FIXED_COLLECTION_NAME);
		return area;
	}

	@Override
	@Cacheable("areas")
	public List<Area> getAll() {
		return mongoTemplate.findAll(Area.class, FIXED_COLLECTION_NAME);
	}

	@Override
	public void delete(Area area) {
		mongoTemplate.remove(area, FIXED_COLLECTION_NAME);

	}

}
