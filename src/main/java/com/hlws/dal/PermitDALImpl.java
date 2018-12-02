package com.hlws.dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.model.Permit;

@Repository
public class PermitDALImpl implements IPermitDAL {

	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "permit";
	
	@Autowired
	public PermitDALImpl(MongoTemplate mongoTemplate) {		
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	@CacheEvict(value="permits", allEntries=true)
	public Permit save(Permit permit) {
		mongoTemplate.save(permit, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return null;
	}

	@Override	
	public List<Permit> getAll() {
		List<Permit> permits = new ArrayList<>();
		permits = mongoTemplate.findAll(Permit.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return permits;
	}

	@Override
	@Cacheable("permits")
	public List<Permit> getActive() {
		List<Permit> permits = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("enddate").gt(new Date()));
		permits = mongoTemplate.find(query, Permit.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return permits;
	}

	
	@Override
	public Permit getOne(Long permitNo) {
		Query query = new Query();
		query.addCriteria(Criteria.where("permitnumber").is(permitNo));
		return mongoTemplate.findOne(query, Permit.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	
	@Override
	public void updatePermitBalance(Long permitNo, Double balance) {
		Query query = new Query();
		query.addCriteria(Criteria.where("permitnumber").is(permitNo));
		Update update = new Update().inc("permitbalance", balance);
		mongoTemplate.updateFirst(query, update, Permit.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));		
	}

	@Override
	public void delete(Permit permit) {
		// TODO Auto-generated method stub

	}

}
