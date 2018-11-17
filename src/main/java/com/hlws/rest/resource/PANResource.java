package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
import com.hlws.model.Vehicle;
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
	public APIResponse<Pan> register(@RequestBody Pan pan, Authentication authentication){
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
	public APIResponse<List<Pan>> getOne(@PathVariable("panId") String panNo){
		String message = "PAN data retrieved successfully";
		List<Pan> data;
		try {	
			Pan pan = service.getOne(panNo);
			data = pan == null ? new ArrayList<>(): Arrays.asList(pan);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while retrieving PAN data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{panNo}")
	@ResponseBody
	public APIResponse<Pan> update(@PathVariable("panNo") String panNo, 
			@RequestBody Pan panData){
		String message = "PAN data updated successfully";;
		Pan data;
		try {
			data = service.save(panData, false);			
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occurred while updating PAN data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value="/{pan}/updatevehicles")
	@ResponseBody
	public APIResponse<String> updatevehicles(@PathVariable("pan") String pan, @RequestBody List<Vehicle> vehicles){
		String message = "Vehicles updated succesfully";
		try {
			service.updatevehicles(pan, vehicles);
		}catch(Exception e) {
			return ResponseUtil.createFailedResponse(e.getMessage());
		}
		return ResponseUtil.createSuccessResponse(message, "updated");
	}
	
	@GetMapping(value = "/vehicle/{searchtext}")
	@ResponseBody
	public APIResponse<List<Pan>> get(@PathVariable("searchtext") String searchtext){
		String message = "Vehicles retrieved successfully for entered criteria";
		List<Pan> data;
		try {
			data = service.getVehiclesBySearchText(searchtext);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while looking for vehicle number";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
}
