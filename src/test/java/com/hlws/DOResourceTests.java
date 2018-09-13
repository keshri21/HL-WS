package com.hlws;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hlws.model.DO;
import com.hlws.model.FreightRange;
import com.hlws.model.Party;
import com.hlws.util.JSONUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/do")
public class DOResourceTests {

	private static SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void shouldCreateDOOrder() throws Exception{
		DO doOrder = createDummyDO(1).get(0);
		this.mockMvc.perform(post("/do")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(doOrder))).andDo(print())
			.andExpect(status().isCreated())
			.andDo(document("create"));
	}
	
	@Test
	public void shouldGetOneDO() throws Exception{
		this.mockMvc.perform(get("/do/1234"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-one"));
	}
	
	@Test
	public void shouldGetAllDO() throws Exception{
		this.mockMvc.perform(get("/do"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-all"));
	}
	
	@Test
	public void shouldGetAllActiveDO() throws Exception{
		this.mockMvc.perform(get("/do/active"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-active"));
	}
	
	@Test
	public void shouldGetAllCompletedDO() throws Exception{
		this.mockMvc.perform(get("/do/completed"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-completed"));
	}
	
	@Test
	public void shouldUpdateDO() throws Exception{
		Date date = new Date();
		date.setDate(date.getDate()+55);
		DO doOrder = createDummyDO(1).get(0);
		doOrder.setRefundAmt(120.0);
		doOrder.setRefundDate(format.format(date));
		this.mockMvc.perform(put("/do/2345")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(doOrder))).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("update"));
	}
	
	public static List<DO> createDummyDO(int count){
		List<DO> list = new ArrayList<DO>();
		Date currDate = new Date();
		Date dueDate = new Date();
		
		while(count > 0) {
			DO doOrder = new DO();
			dueDate.setDate(currDate.getDate()+45);
			doOrder.setAreaDoNo(11128);
			doOrder.setBspDoNo(198866);
			doOrder.setArea("HASDEO");
			doOrder.setCollary("WEST JKD");
			doOrder.setQuantity(2200.0);
			doOrder.setDoDate(format.format(currDate));
			doOrder.setReceivedDate(format.format(currDate));
			doOrder.setDueDate(format.format(dueDate));
			doOrder.setBuiltyCompany("Hindustan Logistic");
			doOrder.setBy("Courier");
			doOrder.setDoAmt(1200000.0);
			doOrder.setDoAmtpmt(2333.8);
			doOrder.setLiftedQuantity(2000.0);
			doOrder.setQuantityDeduction(12.3);
			doOrder.setLepseQuantity(200.0);
			doOrder.setEmd(200.0);
			doOrder.setEmdAmt(0.0);
			doOrder.setRefundAmt(0.0);
			doOrder.setRefundDate(format.format(dueDate));
			doOrder.setTotalRefundAmt(0.0);
			doOrder.setRemarks("OK, WP");
			doOrder.setDoRate(0.0);
			doOrder.setDoRateTcs(112.32);
			doOrder.setDisp("DISPATCH");
			doOrder.setSize("ROM");
			doOrder.setGrade("G-5");
			doOrder.setAuctionNo("155th");
			doOrder.setPermissionNo(23434322);
			dueDate.setDate(dueDate.getDate()-8);
			doOrder.setFinishDate(format.format(dueDate));
			
			Party party = new Party();
			party.setId(1122);
			party.setName("SPONGE Udyog Pvt. Ltd");
			
			List<String> destinations = new ArrayList<>();
			destinations.add("BHILAI");
			destinations.add("RAIPUR");
			FreightRange range = new FreightRange();
			range.setMin(1000);
			range.setMax(1100);
			doOrder.setFreight(range);
			doOrder.setParty(party);
			doOrder.setDestinationParty(party);
			doOrder.setDestinations(destinations);
			
			list.add(doOrder);
			count--;
		}
		
		return list;
	}
}
