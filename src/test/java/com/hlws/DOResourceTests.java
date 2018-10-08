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
import com.hlws.rest.resource.DOResource;
import com.hlws.util.DateUtil;
import com.hlws.util.DummyBuilder;
import com.hlws.util.JSONUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/do")
public class DOResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void shouldCreateDOOrder() throws Exception{
		DO doOrder = DummyBuilder.createDummyDO(1).get(0);
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
		
		DO doOrder = DummyBuilder.createDummyDO(1).get(0);
		doOrder.setRefundAmt(120.0);
		doOrder.setRefundDate(new Date());
		this.mockMvc.perform(put("/do/2345")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(doOrder))).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("update"));
	}
	
	
}
