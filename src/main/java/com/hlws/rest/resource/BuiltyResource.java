package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Builty;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.BuiltyService;
import com.hlws.util.DummyBuilder;


@RestController
@RequestMapping("builty")
public class BuiltyResource {

	@Autowired
	private BuiltyService builtyService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<String> create(@RequestBody Builty builty){
		
		String message = "Builty created successfully";
		String data;
		try {
			data = builtyService.createBuilty(builty);
		}catch(Exception e) {
			message = "Internal Server Error: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message, "");
		}
		return ResponseUtil.createSuccessResponse(message, data); //TODO return created builty nummber
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Builty>> get(@RequestParam(value = "get", defaultValue = "active") String filter){
		String message = "Builties retrieved successfully";
		
		//TODO use filter query param to return all or complete list else return active builties
		List<Builty> builtyList = new ArrayList<Builty>();
		try {
			builtyList = builtyService.getBuiltyList(filter);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while retrieving builties";	
			return ResponseUtil.createFailedResponse(message, builtyList);
		}
		return ResponseUtil.createSuccessResponse(message, builtyList);
	}
	
	@PutMapping(value = "/receipt")
	@ResponseBody
	public APIResponse<String> updateReceipt(@RequestBody List<Builty> builtyList){
		String message = "Builty receipt updated successfully";
		String data = "";
		try {
			builtyService.updateReceipt(builtyList);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while updating receipt";	
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{builtyId}")
	@ResponseBody
	public APIResponse<Builty> getOne(@PathVariable("builtyId") Long builtyId){
		String message;
		Builty builty = new Builty();
		try {
			message = "Builty retrieved successfully";
			builty = DummyBuilder.createDummyBuilty(1).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while retrieving builty";	
			return ResponseUtil.createFailedResponse(message, builty);
		}
		return ResponseUtil.createSuccessResponse(message, builty);
	}
	
	@PutMapping(value = "/{builtyId}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("builtyId") Long builtyId, 
			@RequestBody Builty builty){
		String message;
		String data = "";
		try {
			message = "Builty updated successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while updating builty";	
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{builtyId}/approve")
	@ResponseBody
	public APIResponse<String> approve(@PathVariable("builtyId") Long builtyId){
		String message;
		String data = "";
		try {
			message = "Builty approved successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while approving builty";	
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
}
