package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hlws.model.User;

public class UserDALImpl implements UserDAL {

	private final MongoTemplate mongoTemplate;
	private String collectionName = "hl-users";
	
	@Autowired
	public UserDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public User save(User user) {
		mongoTemplate.save(user, collectionName);
		return user;
	}

	@Override
	public void updatePassword(String userName, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		Update update = new Update().push("password", password);
		mongoTemplate.updateFirst(query, update, collectionName);

	}

	@Override
	public List<User> getAll() {
		return mongoTemplate.findAll(User.class, collectionName);
	}

	@Override
	public List<User> getActiveUsers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("active").is(true));
		return mongoTemplate.find(query, User.class, collectionName);
	}

	@Override
	public List<User> findByUserName(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").regex(userName));
		return mongoTemplate.find(query, User.class, collectionName);
	}

	@Override
	public User findById(String id) {
		return mongoTemplate.findById(id, User.class, collectionName);
	}

	@Override
	public void delete(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(user.getUserName()));
		Update update = new Update().set("active", false);
		mongoTemplate.updateFirst(query, update, collectionName);
	}

}
