package com.hlws;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hlws.model.Vehicle;
import com.hlws.rest.resource.VehicleResource;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/restdocs/vehicle")
public class VehicleResourceTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldGetVehicleMatchingWith() throws Exception{
		this.mockMvc.perform(get("/vehicle?vehicleNo=234"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-matching"));
	}
	
	@Test
	public void shouldGetOneVehicle() throws Exception{
		this.mockMvc.perform(get("/vehicle/32421231"))
				.andDo(print()).andExpect(status().isOk())
				.andDo(document("get-one"));
	}
	
	
}
