package com.hlws.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hlws.dto.UserDTO;
import com.hlws.model.User;
import com.mongodb.client.result.UpdateResult;

@Repository
public class UserDALImpl implements IUserDAL {

	private final MongoTemplate mongoTemplate;
	private static final String FIXED_COLLECTION_NAME = "users";
	
	@Autowired
	public UserDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	@CacheEvict(value="users", allEntries=true)
	public User save(User user) {
		mongoTemplate.save(user, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return user;
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
	@Cacheable(value="users", key="#role")
	public List<UserDTO> getByRole(String role){
		Query query = new Query();
		query.addCriteria(Criteria.where("roleName").is(role).and("active").is(true));
		return mongoTemplate.find(query, UserDTO.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public void deactivate(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		Update update = new Update().set("active", false);
		mongoTemplate.updateFirst(query, update, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
	}

	@Override
	public boolean changePassword(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Update update = new Update().set("password", user.getPassword());
		UpdateResult result = mongoTemplate.updateFirst(query, update, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return result.wasAcknowledged();
	}

	@Override
	public boolean updateUser(User user) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Update update = new Update().set("firstName", user.getFirstName());
		update.set("lastName", user.getLastName());
		update.set("role", user.getRoleName());
		UpdateResult result = mongoTemplate.updateFirst(query, update, User.class, getSpecificCollectionName(FIXED_COLLECTION_NAME));
		return result.wasAcknowledged();
	}
	
	

}
