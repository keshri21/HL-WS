package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hlws.exceptions.DALInitiationException;
import com.hlws.model.Party;

@Repository
public class PartyDALImpl implements IPartyDAL {
	
	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "party";
	
	@Autowired
	public PartyDALImpl(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Party save(Party party) {
		mongoTemplate.save(party, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return party;
	}

	@Override
	public List<Party> getAll() {
		return mongoTemplate.findAll(Party.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public List<Party> findByPartyName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.find(query, Party.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public Party findById(Integer id) {
		return mongoTemplate.findById(id, Party.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public void delete(Party party) {
		mongoTemplate.remove(party, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

}
