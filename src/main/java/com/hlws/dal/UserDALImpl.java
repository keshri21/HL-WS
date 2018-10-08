package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.model.User;

@Repository
public class UserDALImpl implements IUserDAL {

	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "users";
	
	@Autowired
	public UserDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public User save(User user) {
		mongoTemplate.save(user, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return user;
	}

	@Override
	public void updatePassword(String userName, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userName));
		Update update = new Update().set("password", password);
		mongoTemplate.updateFirst(query, update, getSpecificCollectionName(FIXED_COLLECTION_NAME));

	}

	@Override
	public List<User> getAll() {
		return mongoTemplate.findAll(User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public List<User> findActiveUsers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("active").is(true));
		return mongoTemplate.find(query, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public User findByUserName(String userName, String companyId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").regex(userName));
		return mongoTemplate.findOne(query, User.class, "users" + "-" + companyId);
	}

	@Override
	public User findById(String id) {
		return mongoTemplate.findById(id, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public void deactivate(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		Update update = new Update().set("active", false);
		mongoTemplate.updateFirst(query, update, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

}
