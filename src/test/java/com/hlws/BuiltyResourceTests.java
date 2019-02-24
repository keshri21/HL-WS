package com.hlws;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.hlws.model.Builty;
import com.hlws.model.Party;
import com.hlws.model.User;
import com.hlws.rest.resource.BuiltyResource;
import com.hlws.util.DateUtil;
import com.hlws.util.DummyBuilder;
import com.hlws.util.JSONUtil;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/builty")
public class BuiltyResourceTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldCreateBuilty() throws Exception{
		Builty builty = DummyBuilder.createDummyBuilty(1).get(0);
		this.mockMvc.perform(post("/builty").contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(builty)))
				.andDo(print()).andExpect(status().isCreated())
				.andDo(document("create"));
	}
	
	@Test
	public void shouldGetAllActiveBuilty() throws Exception{
		this.mockMvc.perform(get("/builty"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-active"));
	}
	
	@Test
	public void shouldGetAllBuilty() throws Exception{
		this.mockMvc.perform(get("/builty?get=all"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-all"));
	}
	
	@Test
	public void shouldGetCompletedBuilty() throws Exception{
		this.mockMvc.perform(get("/builty?get=completed"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-completed"));
	}
	
	@Test
	public void shouldGetOneBuilty() throws Exception{		
		this.mockMvc.perform(get("/builty/324232"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-one"));
	}
	
	@Test
	public void shouldUpdateBuilty() throws Exception{
		Builty builty = DummyBuilder.createDummyBuilty(1).get(0);
		builty.setFreight(1050);
		this.mockMvc.perform(put("/builty/324232").contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(builty)))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("update"));
	}
	
	@Test
	public void shouldApproveBuilty() throws Exception{
		this.mockMvc.perform(put("/builty/324232/approve"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("approve"));
	}
	
	@Test
	public void shouldUpdateReceiptBuilty() throws Exception{
		Builty builty1 = new Builty();
		builty1.setBuiltyNo("112233");
		builty1.setReceivedDate(new Date());
		builty1.setReceivedQuantity(100.0);
		
		Builty builty2 = new Builty();
		builty2.setBuiltyNo("443355");
		builty2.setReceivedDate(new Date());
		builty2.setReceivedQuantity(150.0);
		
		List<Builty> list = new ArrayList<>();
		list.add(builty1);
		list.add(builty2);
		this.mockMvc.perform(put("/builty/receipt").contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(list)))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("receipt"));
	}
	
	
	
	
	
}
