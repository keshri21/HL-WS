package com.hlws.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hlws.service.PartyService;


@RestController
@RequestMapping("party")
public class PartyResource {

	@Autowired
	PartyService partyService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Party> register(@RequestBody Party party){
		String message = "Party registered successfully";
		Party data;
		try {
			data = partyService.save(party, true);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while registering party";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Party>> get(){
		String message = "party list retrieved successfully";
		List<Party> data;
		try {
			data = partyService.getAll();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving party list";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{partyId}")
	@ResponseBody
	public APIResponse<Party> getOne(@PathVariable("partyId") Integer partyId){
		String message = "Party retrieved successfully";
		Party data;
		try {
			data = partyService.getOne(partyId);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving party";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping
	@ResponseBody
	public APIResponse<String> update(@RequestBody Party party){
		String message = "Party data updated successfully";
		String data = "updated";
		if(party.getId() == null) {
			return ResponseUtil.createFailedResponse("Party id is not set while updating");
		}
		try {
			partyService.save(party, false);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while updating Party data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
