package com.hlws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlws.model.Collary;
import com.hlws.model.DO;
import com.hlws.model.Pan;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class TestBuiltyDAL implements CommandLineRunner {
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//MongoCredential credential = MongoCredential.createCredential("vikas", "hl", "vikas123".toCharArray());
		MongoClientURI uri = new MongoClientURI("mongodb://vikas:vikas123@ds137720.mlab.com:37720/hl");
		//MongoClient mongo = new MongoClient(new ServerAddress("ds137720.mlab.com", 37720), Arrays.asList(credential));
		//MongoClient loclClient = new MongoClient("127.0.0.1");
		MongoClient mongo = new MongoClient(uri);
		MongoTemplate template = new MongoTemplate(mongo, "hl");
		//MongoTemplate template = new MongoTemplate(loclClient, "test");
		
		MyPojo pojo = new MyPojo();
		pojo.setData("date test");
		pojo.setDt(new Date());
		pojo.setDt2(new Date());
		pojo.setCreatedDate(new Date());
		Query query = new Query().addCriteria(Criteria.where("createdDate").lt(new Date()));
		//template.findAllAndRemove(query, "date-test");
		//template.save(pojo, "date-test");
		
		Query query1 = new Query().addCriteria(Criteria.where("vehicles.vehicleNo").regex("1010"));
		List<Pan> lis = template.find(query1, Pan.class, "pan");
		Query query2 = new Query().addCriteria(Criteria.where("_id").is("5bc239357215fe06e8fd5d85"));
		List<State> states = this.populateStates();
		//template.insert(states, "state");
		List<DO> dolist = template.find(query2, DO.class, "do-hl");
		System.out.println("Find by id: " + dolist);
	
		//Query query = new Query().addCriteria(Criteria.where("createdDate").lt(new Date()));
		/*List<MyPojo> pojoList = template.find(query, MyPojo.class, "date-test");
		ObjectMapper mapper = new ObjectMapper();
		for (MyPojo myPojo : pojoList) {
			System.out.println(mapper.writeValueAsString(myPojo));
		}
		
		List<Collary> ar = new ArrayList<>();
		List<String> areas = new ArrayList<>();
		Collary c1 = new Collary();
		c1.setName("HASEO");
		ar.add(c1);
		Collary c2 = new Collary();
		c2.setName("BHATA");
		ar.add(c2);
		c1.setName("PARA");
		ar.add(c1);
		template.insert(ar, "area");
		template.findAll(Collary.class, "area").forEach(area -> areas.add(area.getName()));*/
		//System.out.println(lis);
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TestBuiltyDAL.class, args);
	}
	
	private List<State> populateStates() {
		List<State> states = new ArrayList<>();
		states.add(new State("Andaman and Nicobar Islands"));
		states.add(new State("Andhra Pradesh"));
		states.add(new State("Arunachal Pradesh"));
		states.add(new State("Assam"));
		states.add(new State("Bihar"));
		states.add(new State("Chandigarh"));
		states.add(new State("Chhattisgarh"));
		states.add(new State("Dadra and Nagar Haveli"));
		states.add(new State("Daman and Diu"));
		states.add(new State("Delhi"));
		states.add(new State("Goa"));
		states.add(new State("Gujrat"));
		states.add(new State("Haryana"));
		states.add(new State("Himachal Pradesh"));
		states.add(new State("Jammu and Kashmir"));
		states.add(new State("Jharkhand"));
		states.add(new State("Karnataka"));
		states.add(new State("Kerala"));
		states.add(new State("Lakshadweep"));
		states.add(new State("Madhya Pradesh"));
		states.add(new State("Maharashtra"));
		states.add(new State("Manipur"));
		states.add(new State("Meghalaya"));
		states.add(new State("Mizoram"));
		states.add(new State("Nagaland"));
		states.add(new State("Odisha"));
		states.add(new State("Puducherry"));
		states.add(new State("Punjab"));
		states.add(new State("Rajasthan"));
		states.add(new State("Sikkim"));
		states.add(new State("Tamil Nadu"));
		states.add(new State("Telangana"));
		states.add(new State("Tripura"));
		states.add(new State("Uttar Pradesh"));
		states.add(new State("Uttarakhand"));
		states.add(new State("West Bengal"));
		
		return states;
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
