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

import com.hlws.model.Pan;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.util.DummyBuilder;


@RestController
@RequestMapping("pan")
public class PANResource {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<String> register(@RequestBody Pan pan){
		String message;
		String data = "ASAFJ3739K";
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
			panList = DummyBuilder.createDummyPan(4);
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
			data = DummyBuilder.createDummyPan(1).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{panNo}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("panNo") String panNo, 
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
