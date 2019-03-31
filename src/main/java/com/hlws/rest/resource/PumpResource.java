package com.hlws.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.StringRefData;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.PumpService;

@RestController
@RequestMapping("pump")
public class PumpResource {
	
	@Autowired
	PumpService pumpService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public APIResponse<StringRefData> register(@RequestBody StringRefData pump){
		String message = "Party registered successfully";
		try {
			pumpService.createOrupdate(pump);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while creating/updating pump name";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, pump);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<StringRefData>> get(){
		String message = "Pump list retrieved successfully";
		List<StringRefData> data;
		try {
			data = pumpService.getAll();
		}catch (Exception e) {
			e.printStackTrace();
			message = "Some error occurred while getting list of pumps";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping("/remove")
	@ResponseBody
	public APIResponse<StringRefData> remove(@RequestBody StringRefData pump){
		String message = "Pump removed successfully";
		try {
			pumpService.remove(pump);
		}catch (Exception e) {
			e.printStackTrace();
			message = "Some error occurred while removing pump";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, pump);
	}
}
