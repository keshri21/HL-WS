package com.hlws;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.hlws.dal.BuiltyDALImpl;
import com.hlws.dto.BuiltyDTO;
import com.hlws.model.Builty;
import com.hlws.util.DummyBuilder;
import com.mongodb.MongoClient;

public class TestBuiltyDAL {
	public static void main(String[] args) {
		MongoClient mongo = new MongoClient("localhost");
		MongoTemplate template = new MongoTemplate(mongo, "test");
		
		BuiltyDALImpl bdal = new BuiltyDALImpl(template);
		
		/*List<Builty> list = DummyBuilder.createDummyBuilty(3);
		
		list.forEach(builty -> {
			bdal.save(builty);
		});
		
		List<Builty> builties = bdal.findRunning();
		List<Builty> builtiesC = bdal.findRunning();
		
		System.out.println(builties);
		System.out.println(builtiesC);*/
		
		BuiltyDTO dto = new BuiltyDTO();
		dto.setId("5babd9037215fe76e0db4007");
		dto.setReceivedDate("21/08/2018");
		dto.setReceivedQuantity(23.5);
		bdal.updateReceipt(Arrays.asList(dto));
		
		List<Builty> builties = bdal.findRunning();
		List<Builty> builtiesC = bdal.findCompleted();
		
		System.out.println(builties);
		System.out.println(builtiesC);
	}
}
