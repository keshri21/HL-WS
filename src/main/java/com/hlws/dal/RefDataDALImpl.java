package com.hlws.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hlws.State;
import com.hlws.model.Area;
import com.hlws.model.FreightRefData;
import com.hlws.model.RefData;

@Repository
@DependsOn({"partyDALImpl"})
public class RefDataDALImpl implements IRefDataDAL{

	private final MongoTemplate mongoTemplate;
	
	private final IPartyDAL partyRepository;
	
	private static final String AREA_COLLECTION = "area";
	
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
		refData.setFreightData(getFreightRefData());
		return refData;
	}
	
	@Cacheable("areas")
	private List<Area> getAreas(){
		List<Area> areas = new ArrayList<>();
		areas = mongoTemplate.findAll(Area.class, AREA_COLLECTION);
			//.forEach(area -> areas.add(area.getName()));
		Collections.sort(areas);
		return areas;
	}
	
	@Cacheable("states")
	private List<String> getStates(){
		List<String> states = new ArrayList<>();
		mongoTemplate.findAll(State.class, "state")
			.forEach(state -> states.add(state.getName()));
		Collections.sort(states);
		return states;
	}
	
	@Cacheable("freightRefData")
	private FreightRefData getFreightRefData() {		
		FreightRefData frieghtData = mongoTemplate.findAll(FreightRefData.class, getSpecificCollectionName("freightrefdata")).get(0);
		return frieghtData;
	}
	
}
