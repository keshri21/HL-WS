package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hlws.model.StringRefData;

@Repository
public class PumpDALImpl implements IPumpDAL {

	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "pump";
	
	@Autowired
	public PumpDALImpl(MongoTemplate mongoTemplate) {		
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void save(StringRefData pump) {
		// TODO Auto-generated method stub
		mongoTemplate.save(pump, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public void delete(StringRefData pump) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(pump, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public List<StringRefData> getList() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(StringRefData.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}
	
	

}
