package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Vehicle;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;

@RestController
@RequestMapping("vehicle")
public class VehicleResource {

	@GetMapping
	@ResponseBody
	public APIResponse<List<Vehicle>> get(@RequestParam("vehicleNo") String vehicleNo){
		String message;
		List<Vehicle> data = new ArrayList<Vehicle>();
		try {
			message = "Vehicle retrieved successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while looking for vehicle number";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{vehicleId}")
	@ResponseBody
	public APIResponse<Vehicle> getOne(@PathVariable("vehicleId") Long vehicleId){
		String message;
		Vehicle data = new Vehicle();
		try {
			message = "Vehicle retrieved successfully";
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error ocurred while looking for vehicle id";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
