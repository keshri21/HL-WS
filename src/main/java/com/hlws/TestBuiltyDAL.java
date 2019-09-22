package com.hlws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlws.model.StringRefData;
import com.hlws.dto.BuiltyDTO;
import com.hlws.enums.BiltyPaymentStatus;
import com.hlws.model.Builty;
import com.hlws.model.DO;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

//@SpringBootApplication
public class TestBuiltyDAL{


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MongoCredential credential = MongoCredential.createCredential("vikas", "hl", "vikas123".toCharArray());
//		MongoClientURI uri = new MongoClientURI("mongodb://vikas:vikas123@ds137720.mlab.com:37720/hl");
//		//MongoClient mongo = new MongoClient(new ServerAddress("ds137720.mlab.com", 37720), Arrays.asList(credential));
//		//MongoClient loclClient = new MongoClient("127.0.0.1");
//		MongoClient mongo = new MongoClient(uri);
//		MongoTemplate template = new MongoTemplate(mongo, "hl");
//		//MongoTemplate template = new MongoTemplate(loclClient, "test");
//		
//		Criteria criteria = new Criteria();
//		criteria.orOperator(Criteria.where("dueDate").lt(new Date()), Criteria.where("doBalance").lte(0));
//		Query query = new Query(criteria);
//        List<DO> dos = template.find(query, DO.class, "do-hl");
//		//template.updateMulti(query, update, Builty.class, "builty-hl");
//		
//		/*Query query2 = new Query();
//		query2.addCriteria(Criteria.where("paymentStatus").is(2));
//		Update update2 = new Update();
//		update2.set("paymentStatus", BiltyPaymentStatus.INITIATED.getStatusCode());
//		List<Builty> bilties2 = template.find(query2, Builty.class, "builty-hl");*/
//		//template.updateMulti(query2, update2, Builty.class, "builty-hl");
//		System.out.println("DO list received: " + dos);
		//System.out.println(bilties2);
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
