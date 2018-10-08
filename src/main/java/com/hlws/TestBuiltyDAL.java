package com.hlws;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.hlws.dal.BuiltyDALImpl;
import com.hlws.dto.BuiltyDTO;
import com.hlws.exceptions.DALInitiationException;
import com.hlws.model.Builty;
import com.hlws.util.DummyBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SpringBootApplication
@EnableMongoAuditing
public class TestBuiltyDAL implements CommandLineRunner {
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		MongoCredential credential = MongoCredential.createCredential("vikas", "hl", "vikas123".toCharArray());
		//MongoClientURI uri = new MongoClientURI("mongodb://vikas:vikas123@ds137720.mlab.com:37720/hl");
		//MongoClient mongo = new MongoClient(new ServerAddress("ds137720.mlab.com", 37720), Arrays.asList(credential));
		MongoClient loclClient = new MongoClient("127.0.0.1");
		//MongoClient mongo = new MongoClient(uri);
		//MongoTemplate template = new MongoTemplate(mongo, "hl");
		MongoTemplate template = new MongoTemplate(loclClient, "test");
		
		MyPojo pojo = new MyPojo();
		pojo.setData("date test");
		pojo.setDt(new Date());
		pojo.setDt2(new Date());
		pojo.setCreatedDate(new Date());
		Query query = new Query().addCriteria(Criteria.where("createdDate").lt(new Date()));
		template.findAllAndRemove(query, "date-test");
		template.save(pojo, "date-test");
		
		//Query query = new Query().addCriteria(Criteria.where("createdDate").lt(new Date()));
		List<MyPojo> pojoList = template.find(query, MyPojo.class, "date-test");
		ObjectMapper mapper = new ObjectMapper();
		for (MyPojo myPojo : pojoList) {
			System.out.println(mapper.writeValueAsString(myPojo));
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TestBuiltyDAL.class, args);
	}
}

class MyPojo{
	
	private String id;
	private Date dt;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss", timezone="IST")
	private Date createdDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="IST")
	private Date dt2;
	private String data;
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDt2() {
		return dt2;
	}
	public void setDt2(Date dt2) {
		this.dt2 = dt2;
	}
	@Override
	public String toString() {
		return "MyPojo [dt=" + dt + ", createdDate=" + createdDate + ", dt2=" + dt2 + ", data=" + data + "]";
	}
	
	
	
	
}
