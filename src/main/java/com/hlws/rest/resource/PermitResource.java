package com.hlws.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
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

import com.hlws.model.Permit;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.PermitService;

@RestController
@RequestMapping("permit")
public class PermitResource {

	@Autowired
	PermitService permitservice;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Permit> create(@RequestBody Permit permit){
		String message = "Permit created successfully";
		Permit data;
		try {
			data = permitservice.create(permit);			
		}catch(DuplicateKeyException de) {
			message = "Permit number already exists";
			return ResponseUtil.createFailedResponse(message);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while creating permit";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping
	@ResponseBody
	public APIResponse<Permit> update(@RequestBody Permit permit){
		String message = "Permit updated successfully";
		Permit data;
		if(StringUtils.isEmpty(permit.getId())) {
			return ResponseUtil.createFailedResponse("Permit id is not set while updating");
		}
		try {
			data = permitservice.update(permit);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while updating permit";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Permit>> getPermits(@RequestParam(value = "get", defaultValue = "active") String filter){
		String message = "Permit list retrieved successfully";
		List<Permit> data;
		try {
			data = permitservice.getPermitList(filter);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving permit list";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{permitId}")
	@ResponseBody
	public APIResponse<Permit> getone(@PathVariable("permitId") String permitid){
		String message = "Permit retrieved successfully";
		Permit data;
		try {
			//TODO write query to get single permit
			data = permitservice.getPermitList(permitid).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving permit";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
