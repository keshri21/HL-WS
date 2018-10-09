package com.hlws.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hlws.model.Collary;
import com.hlws.model.Party;
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
		List<String> collaries = new ArrayList<>();
		List<String> areas = new ArrayList<>();
		List<Party> parties = partyRepository.getAll();
		mongoTemplate.findAll(Collary.class, "collary")
				.forEach(collary -> collaries.add(collary.getName()));
		mongoTemplate.findAll(Collary.class, "area")
			.forEach(area -> areas.add(area.getName()));
		
		RefData refData = new RefData();
		refData.setPartyList(parties);
		refData.setAreaList(areas);
		refData.setCollaryList(collaries);
		return refData;
	}

	
}
