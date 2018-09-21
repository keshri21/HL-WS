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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hlws.model.FreightRange;
import com.hlws.model.Party;
import com.hlws.rest.resource.PartyResource;
import com.hlws.util.DummyBuilder;
import com.hlws.util.JSONUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/party")
public class PartyResourceTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldCreateParty() throws Exception{
		Party party = DummyBuilder.createDummyParty(1).get(0);
		this.mockMvc.perform(post("/party").contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(party)))
				.andDo(print()).andExpect(status().isCreated())
				.andDo(document("create"));
	}
	
	@Test
	public void shouldUpdateParty() throws Exception{
		Party party = DummyBuilder.createDummyParty(1).get(0);
		party.setName("ENERGY SALES CORPORATION");
		party.getDestinations().add("PUNE");
		this.mockMvc.perform(put("/party/1234").contentType(MediaType.APPLICATION_JSON)
				.content(JSONUtil.toJson(party)))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("update"));
	}
	
	@Test
	public void shoulGetOne() throws Exception {
		this.mockMvc.perform(get("/party/1234"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-one"));
	}
	
	@Test
	public void shoulGetAll() throws Exception {
		this.mockMvc.perform(get("/party"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-all"));
	}
	
	

}
