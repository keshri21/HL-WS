package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Pan;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;


@RestController
@RequestMapping("pan")
public class PANResource {

	@PostMapping
	@ResponseBody
	public APIResponse<Long> register(@RequestBody Pan pan){
		String message;
		Long data = 123l;
		try {
			message = "PAN data registered successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while registering PAN";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Pan>> get(){
		String message;
		List<Pan> panList = new ArrayList<Pan>();
		try {
			message = "PAN data retrieved successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message, panList);
		}
		return ResponseUtil.createSuccessResponse(message, panList);
	}
	
	@GetMapping(value = "/{panId}")
	@ResponseBody
	public APIResponse<Pan> getOne(@PathVariable("panId") String panNo){
		String message;
		Pan data = new Pan();
		try {
			message = "PAN data retrieved successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{panId}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("panId") String panNo, 
			@RequestBody Pan panData){
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
