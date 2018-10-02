package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hlws.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDALImpl implements IUserDAL {

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
		query.addCriteria(Criteria.where("username").is(userName));
		Update update = new Update().push("password", password);
		mongoTemplate.updateFirst(query, update, collectionName);

	}

	@Override
	public List<User> getAll() {
		return mongoTemplate.findAll(User.class, collectionName);
	}

	@Override
	public List<User> findActiveUsers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("active").is(true));
		return mongoTemplate.find(query, User.class, collectionName);
	}

	@Override
	public User findByUserName(String userName, String companyId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").regex(userName));
		return mongoTemplate.findOne(query, User.class, companyId + "-users");
	}

	@Override
	public User findById(String id) {
		return mongoTemplate.findById(id, User.class, collectionName);
	}

	@Override
	public void deactivate(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		Update update = new Update().set("active", false);
		mongoTemplate.updateFirst(query, update, User.class, collectionName);
	}

}
