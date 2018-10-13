package com.hlws.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hlws.model.Collary;
import com.hlws.model.RefData;

@Repository
@DependsOn({"partyDALImpl"})
public class RefDataDALImpl implements IRefDataDAL{

	private final MongoTemplate mongoTemplate;
	
	private final IPartyDAL partyRepository;
	
	@Autowired
	public RefDataDALImpl(MongoTemplate mongoTemplate, IPartyDAL partyRepository) {
		this.mongoTemplate = mongoTemplate;
		this.partyRepository = partyRepository;
	}
	
	@Override
	public RefData get() {
		RefData refData = new RefData();
		refData.setPartyList(partyRepository.getAll());
		Collections.sort(refData.getPartyList());
		refData.setAreaList(getAreas());
		refData.setCollaryList(getCollaries());
		return refData;
	}
	
	@Cacheable("collaries")
	private List<String> getCollaries(){
		List<String> collaries = new ArrayList<>();
		mongoTemplate.findAll(Collary.class, "collary")
			.forEach(collary -> collaries.add(collary.getName()));
		Collections.sort(collaries);
		return collaries;
	}
	
	@Cacheable("areas")
	private List<String> getAreas(){
		List<String> areas = new ArrayList<>();
		mongoTemplate.findAll(Collary.class, "area")
			.forEach(area -> areas.add(area.getName()));
		Collections.sort(areas);
		return areas;
	}

	
}
