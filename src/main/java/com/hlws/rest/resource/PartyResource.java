package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Party;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.util.DummyBuilder;


@RestController
@RequestMapping("party")
public class PartyResource {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Long> register(@RequestBody Party party){
		String message;
		Long data = 123l;
		try {
			message = "Party registered successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while registering party";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Party>> get(){
		String message;
		List<Party> data = new ArrayList<Party>();
		try {
			message = "party list retrieved successfully";
			data = DummyBuilder.createDummyParty(4);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving party list";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{partyId}")
	@ResponseBody
	public APIResponse<Party> getOne(@PathVariable("partyId") Long partyId){
		String message;
		Party data = new Party();
		try {
			message = "Party retrieved successfully";
			data = DummyBuilder.createDummyParty(1).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving party";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{partyId}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("partyId") Long partyId, 
			@RequestBody Party party){
		String message;
		String data = "";
		try {
			message = "PAN data updated successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while updating PAN data";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
