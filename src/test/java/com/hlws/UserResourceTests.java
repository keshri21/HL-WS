package com.hlws;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hlws.model.User;
import com.hlws.rest.resource.UserResource;
import com.hlws.util.DummyBuilder;

import net.bytebuddy.utility.RandomString;
import net.minidev.json.JSONUtil;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/user")
public class UserResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void shouldGetUserList() throws Exception{
		this.mockMvc.perform(get("/user")).andDo(print())
			.andExpect(status().isOk()).andDo(document("get-all"));
	}
	
	@Test
	public void shouldGetOneUser() throws Exception{
		this.mockMvc.perform(get("/user/username")).andDo(print())
			.andExpect(status().isOk())
			.andDo(document("get-one"));
	}
	
	@Test
	public void shouldCreateUser() throws Exception{
		User user = DummyBuilder.createDummyUsers(1).get(0);
		this.mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(com.hlws.util.JSONUtil.toJson(user)))
			.andDo(print()).andExpect(status().isCreated()).andDo(document("create"));
	}
	
	@Test
	public void shouldUpdateUser() throws Exception{
		User user = DummyBuilder.createDummyUsers(1).get(0);
		user.setLastName("kumar");
		this.mockMvc.perform(put("/user/username")
				.contentType(MediaType.APPLICATION_JSON)
				.content(com.hlws.util.JSONUtil.toJson(user)))
		.andDo(print()).andExpect(status().isOk()).andDo(document("update"));
	}
	
	@Test
	public void shouldDeactivateUser() throws Exception{
	
		this.mockMvc.perform(delete("/user/username")
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk()).andDo(document("delete"));
	}
	
	

}
