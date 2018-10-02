package com.hlws;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.hlws.dal.UserDALImpl;
import com.hlws.model.User;
import com.hlws.util.DummyBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class TestUserDAL {

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("localhost");
		MongoTemplate template = new MongoTemplate(mongo, "test");
		
		UserDALImpl udal = new UserDALImpl(template);
		
		User user = DummyBuilder.createDummyUsers(1).get(0);
		udal.save(user);
		
		User user1 = udal.findByUserName(user.getUsername(), "hl");
		System.out.println(user1);
	}
}
