package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.VehicleService;
import com.hlws.util.DummyBuilder;

@RestController
@RequestMapping("vehicle")
public class VehicleResource {
	
	@Autowired
	VehicleService service;

	/*@GetMapping
	@ResponseBody
	public APIResponse<List<Vehicle>> get(@RequestParam("vehicleNo") String vehicleNo){
		String message;
		List<Vehicle> data = new ArrayList<Vehicle>();
		try {
			message = "Vehicle retrieved successfully";
			data = DummyBuilder.createDummyVehicles(2);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while looking for vehicle number";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}*/
	
	@GetMapping
	@ResponseBody
	public List<Vehicle> getVehicles(@RequestParam(value = "searchText", defaultValue = "all") String searchText, @RequestParam(value = "inlcudeOldOwner", defaultValue = "false") Boolean includeOldOwner){
		String message = "All Vehicles retrieved successfully";
		List<Vehicle> data;
		try {
			data = service.getVehicles(searchText, includeOldOwner);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while fetching vehicle list";
			return new ArrayList<Vehicle>();
		}
		return data;
	}
	
	@GetMapping(value = "/{vehicleId}")
	@ResponseBody
	public APIResponse<Pan> getOne(@PathVariable("vehicleId") Long vehicleId){
		String message;
		Pan data = new Pan();
		try {
			message = "Vehicle retrieved successfully";
			data = DummyBuilder.createDummyPan(1).get(0);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while looking for vehicle id";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
