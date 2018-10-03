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

import com.hlws.model.Pan;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.PanService;


@RestController
@RequestMapping("pan")
public class PANResource {

	@Autowired
	PanService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Pan> register(@RequestBody Pan pan){
		String message = "PAN data registered successfully";
		Pan data;
		try {
			data = service.save(pan, true);
			
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while registering PAN";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Pan>> get(){
		String message = "PAN data retrieved successfully";
		List<Pan> panList;
		try {
			
			panList = service.getAll();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, panList);
	}
	
	@GetMapping(value = "/{panId}")
	@ResponseBody
	public APIResponse<Pan> getOne(@PathVariable("panId") String panNo){
		String message = "PAN data retrieved successfully";
		Pan data;
		try {			
			data = service.getOne(panNo);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{panNo}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("panNo") String panNo, 
			@RequestBody Pan panData){
		String message = "PAN data updated successfully";;
		String data = "updated";
		try {
			service.save(panData, false);			
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while updating PAN data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
