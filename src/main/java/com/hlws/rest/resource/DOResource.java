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

import com.hlws.model.DO;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.DOService;
import com.hlws.util.DummyBuilder;

@RestController
@RequestMapping("do")
public class DOResource {
	
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
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem occured while creating DO";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data); 
	}
	
	@PutMapping(value = "/{doId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public APIResponse<String> update(@PathVariable("doId") String doId, @RequestBody DO doObj) {
		
		String message = "DO updated successfully";
		String data = "updated";
		try {
			doService.update(doObj);
		}catch(Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
