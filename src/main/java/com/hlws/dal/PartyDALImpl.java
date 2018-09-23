package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hlws.model.Party;

@Repository
public class PartyDALImpl implements IPartyDAL {
	
	private final MongoTemplate mongoTemplate;
	private String collectionName = "hl-party";
	
	@Autowired
	public PartyDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Party save(Party party) {
		mongoTemplate.save(party, collectionName);
		return party;
	}

	@Override
	public List<Party> getAll() {
		return mongoTemplate.findAll(Party.class, collectionName);
	}

	@Override
	public List<Party> findByPartyName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.find(query, Party.class, collectionName);
	}

	@Override
	public Party findById(String id) {
		return mongoTemplate.findById(id, Party.class);
	}

	@Override
	public void delete(Party party) {
		mongoTemplate.remove(party);
	}

}
