package com.hlws.rest.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hlws.exceptions.InvalidDataException;
import com.hlws.model.DO;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.DOService;

@RestController
@RequestMapping("do")
public class DOResource {
	
	private final Logger LOG = LoggerFactory.getLogger(DOResource.class); 
	
	@Autowired
	private DOService doService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<DO> create(@RequestBody DO doObj) {
		
		String message = "DO created successfully";
		DO data;
		try {
			data = doService.create(doObj);
		}catch(InvalidDataException ie) {
			LOG.error("Erro creating DO: {}, {}", ie.getMessage(), ie);
			message = ie.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		catch(Exception e) {
			LOG.error("Error creating DO: {}, {}", e.getMessage(), e);
			message = "Some problem occured while creating DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data); 
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public APIResponse<String> update(@RequestBody DO doObj) {
		if(doObj.getId() == null) {
			return ResponseUtil.createFailedResponse("Do id must be set while updating DO details");
		}
		String message = "DO updated successfully";
		String data = "updated";
		try {
			doService.update(doObj);
		}catch(Exception e) {
			LOG.error("Error updating DO: {}, {}", e.getMessage(), e);
			message = "Some problem occured while updating DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data); 
	}
	
	@GetMapping(value = "/{doId}")
	@ResponseBody
	public APIResponse<DO> getOne(@PathVariable("doId") String doId){
		
		String message = "DO retrieved successfully";
		DO data;
		try {
			data = doService.getOne(doId);
		}catch(Exception e) {
			LOG.error("Error retrieving DO details for {}: {}, {}", doId, e.getMessage(), e);
			message = "Some problem occured while retrieving DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<DO>> get(@RequestParam(value = "get", defaultValue = "active") String filter){
		
		String message = "DO retrieved successfully";
		List<DO> data;
		try {
			data = doService.getDOList(filter);
		}catch(Exception e) {
			LOG.error("Error retrieving {} DO list: {}, {}", filter, e.getMessage(), e);
			message = "Some problem occured while retrieving all DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/active")
	@ResponseBody
	public APIResponse<List<DO>> getActive(){
		
		String message = "DO retrieved successfully";
		List<DO> data;
		try {
			data = doService.getRunning();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem occured while retrieving active DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/completed")
	@ResponseBody
	public APIResponse<List<DO>> getCompleted(){
		
		String message = "DO retrieved successfully";
		List<DO> data;
		try {
			data = doService.getCompleted();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem occured while retrieving completed DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
