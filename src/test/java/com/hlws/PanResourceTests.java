package com.hlws;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hlws.model.Account;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.hlws.rest.resource.PANResource;
import com.hlws.util.DummyBuilder;
import com.hlws.util.JSONUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/pan")
public class PanResourceTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldRegisterPAN() throws Exception{
		Pan pan = DummyBuilder.createDummyPan(1).get(0);
		this.mockMvc.perform(post("/pan")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(pan)))
				.andDo(print()).andExpect(status().isCreated())
				.andDo(document("register"));
	}
	
	@Test
	public void shouldGetOnePAN() throws Exception{
		this.mockMvc.perform(get("/pan/SHDJY8387K"))				
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-one"));
	}
	
	@Test
	public void shouldUpdatePan() throws Exception{
		Pan pan = DummyBuilder.createDummyPan(1).get(0);
		pan.setMobile(3334555553l);
		
		Account ac2 = new Account();
		ac2.setAccountHoldername("Sara tendulkar");
		ac2.setAccountNo("0037382822");
		ac2.setBankName("SBI");
		ac2.setIfscCode("SBI0000238");
		
		pan.getAccounts().add(ac2);
		
		this.mockMvc.perform(put("/pan/DHFWA3839K")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(pan)))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document("update"));
		
	}
	
	@Test
	public void shouldGetAll() throws Exception{
		this.mockMvc.perform(get("/pan"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-all"));

	}
	
	

}
