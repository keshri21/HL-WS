package com.hlws.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.hlws.model.Account;
import com.hlws.model.Builty;
import com.hlws.model.DO;
import com.hlws.model.Destination;
import com.hlws.model.DestinationParty;
import com.hlws.model.FreightRange;
import com.hlws.model.Pan;
import com.hlws.model.Party;
import com.hlws.model.User;
import com.hlws.model.Vehicle;

public class DummyBuilder {
	
	public static List<Builty> createDummyBuilty(int count){
		List<Builty> list = new ArrayList<>();
		while(count > 0) {
			Builty builty = new Builty();
			builty.setBuiltyDate(new Date());
			builty.setDoId("23234l");
			builty.setDoDisplay("1231/34342-HASDEO-2000");
			builty.setVehicleNo("MH12ER332");
			builty.setDoOpeningBalance(100.0);
			builty.setInAdvance(2000);
			builty.setOutAdvance(1000);
			builty.setDiesel(1500);
			builty.setPumpName("Kesharwani Pump");
			builty.setFreight(1000);
			builty.setFreightToBePaidBy("party");
			builty.setIgpNo(23421112l);
			builty.setInvoiceNo(34534243l);
			builty.setInvoiceValue(1234.2);
			builty.setDriverName("Narendra Modi");
			builty.setDriverMobile(8888888888l);
			builty.setTotalAdvance(3000);
			builty.setTpNo("TP3487382");
			
			User user = new User();
			user.setFirstName("Rahul");
			user.setUsername("raga");
			builty.setTransporter("");
			user.setFirstName("Sonia");
			user.setUsername("sona");
			builty.setSubTransporter("");
			builty.setOtBuiltyCompany("Surya Logistics");
			builty.setOtBuiltyNumber(234221);
			builty.setWaybillNo("W3459339");
			builty.setPermitNo(23434232l);
			builty.setPermitBalance(332.5);
			builty.setPermitEndDate(new Date());
			builty.setGrossWeight(120.5);
			builty.setTierWeight(10.0);
			builty.setNetWeight(110.5);
			
			builty.setParty("NEEL SAGAR RESOURCES");
			builty.setAssesibleValue(2300.0);
			builty.setRefund(2000.0);
			builty.setInAdvance(1500);
			list.add(builty);
			count--;
		}
		
		return list;
	}
	
	public static List<DO> createDummyDO(int count){
		List<DO> list = new ArrayList<DO>();
		Date currDate = new Date();
		
		while(count > 0) {
			DO doOrder = new DO();
			doOrder.setAreaDoNo(11128);
			doOrder.setBspDoNo(198866l);
			doOrder.setArea("HASDEO");
			doOrder.setCollary("WEST JKD");
			doOrder.setQuantity(2200.0);
			doOrder.setDoDate(currDate);
			//doOrder.setReceivedDate(DateUtil.format(currDate));
			doOrder.setDueDate(new Date());
			doOrder.setBy("Courier");
			doOrder.setDoAmt(1200000.0);
			doOrder.setDoAmtpmt(2333.8);
			doOrder.setQuantityDeduction(12.3);
			doOrder.setLepseQuantity(200.0);
			doOrder.setEmd(200.0);
			doOrder.setEmdAmt(0.0);
			doOrder.setRefundAmt(0.0);
			doOrder.setRefundDate(new Date());
			//doOrder.setTotalRefundAmt(0.0);
			doOrder.setRemarks("OK, WP");
			doOrder.setDoRate(0.0);
			doOrder.setDoRateTcs(112.32);
			//doOrder.setDisp("DISPATCH");
			doOrder.setSize("ROM");
			doOrder.setGrade("G-5");
			doOrder.setAuctionNo(155);
			doOrder.setPermitNos(Arrays.asList(1234));
			doOrder.setFinishDate(new Date());
			
			Party party = new Party();
			party.setId("1122");
			party.setName("SPONGE Udyog Pvt. Ltd");
			
			List<String> destinations = new ArrayList<>();
			destinations.add("BHILAI");
			destinations.add("RAIPUR");
			doOrder.setParty(party);
			
			//doOrder.setDestinationParty(destinations);
			//doOrder.setDestinations(destinations);
			
			doOrder.getInAdvanceLimit().add(1000);
			doOrder.getInAdvanceLimit().add(1500);
			
			doOrder.getFreightToBePaidBy().add("Party");
			
			list.add(doOrder);
			count--;
		}
		
		return list;
	}
	
	public static List<Pan> createDummyPan(int count){
		List<Pan> list = new ArrayList<>();
		while(count > 0) {
			Pan panData = new Pan();
			panData.setPanNo("DHFWA3839K");
			panData.setPanHolderName("Sachin Tendulkar");
			panData.setCity("Mumbai");
			panData.setMobile(8888833333l);
			panData.setTds(false);
			Vehicle v1 = new Vehicle();
			v1.setVehicleNo("MH12HG342");
			Set<Vehicle> vList = new HashSet<>();
			vList.add(v1);
			panData.setVehicles(vList);
			
			Account ac = new Account();
			ac.setAccountHoldername("Anjali tendulkar");
			ac.setAccountNo("002822921338");
			ac.setBankName("HDFC");
			ac.setIfscCode("HDFC0000008");					
			
			List<Account> acList = new ArrayList<>();
			acList.add(ac);
			//acList.add(ac2);
			
			panData.setAccounts(acList);
			list.add(panData);
			count--;
			
		}
		return list;
	}
	
	public static List<Party> createDummyParty(int count) {
		List<Party> list = new ArrayList<>();
		while(count > 0) {
			Party party = new Party();
			party.setId("1234");
			party.setName("MONNT ISPAT & ENERGY LTD.");
			Destination d1= new Destination();
			d1.setName("Raipur");
			party.getDestinations().add("Raipur");
			Destination d2= new Destination();
			d1.setName("Durg");
			party.getDestinations().add("Durg");
			list.add(party);
			count--;
		}
		
		return list;
	}
	
	public static List<User> createDummyUsers(int count){
		List<User> list = new ArrayList<User>();
		while(count > 0) {
			User user = new User();
			user.setActive(true);
			user.setFirstName("vikas" + count);
			user.setLastName("keshri");
			user.setUsername("ABC_XYZ12");
			user.setRoleName("OFFICE");
			user.setPassword("{password}");
			list.add(user);
			count--;
		}
		return list;
	}
	
	public static List<Vehicle> createDummyVehicles(int count){
		List<Vehicle> list = new ArrayList<>();
		String vArr[] = {"MH12KJ2349", "CG34SD6234", "MP18TT7234", "CG18WE234"};
		Random random = new Random();
		while(count > 0) {
			Vehicle v = new Vehicle();
			
			v.setVehicleNo(vArr[random.nextInt(vArr.length) - 1]);
			
			count--;
		}
		return list;
	}

}
