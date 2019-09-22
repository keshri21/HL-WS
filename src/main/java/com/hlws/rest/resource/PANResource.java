package com.hlws.rest.resource;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.PanService;


@RestController
@RequestMapping("pan")
public class PANResource {

	private final Logger LOG = LoggerFactory.getLogger(PANResource.class);
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
			LOG.error("Error retrieving pan list: {}, {}", e.getMessage(), e);
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
			Pan pan = service.getPAN(panNo);
			data = pan == null ? new ArrayList<>(): Arrays.asList(pan);
		}catch(Exception e) {
			LOG.error("Error retrieving pan details for {}: {}, {}", panNo, e.getMessage(), e);
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
			LOG.error("Error updating pan detail for {}: {}, {}", panNo, e.getMessage(), e);
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
			LOG.error("Error updating vehicle details for pan={} and vehicles={}: {}, {}", pan, vehicles, e.getMessage(), e);
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
			LOG.error("Error looking up vehicles for criteria={}: {}, {}", searchtext, e.getMessage(), e);
			message = "Some error ocurred while looking for vehicle number";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/vehicle/{vehicleno}/canbedeleted")
	@ResponseBody
	public APIResponse<Boolean> checkEligibility(@PathVariable("vehicleno") String vehicleno){
		String message = "Eligibilty checked successfully";
		Boolean data = false;
		try {
			data = service.ifVehicleCanBeDeleted(vehicleno);
		}catch(Exception e) {
			LOG.error("Error checking eligibiilty to delete vehicle {}: {}, {}", vehicleno, e.getMessage(), e);
			message = "Some error ocurred while checking for eligibility";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/export")
	@ResponseBody
	public APIResponse<Integer> exportPANData(){
		String message = "Cache key to export PAN data generated successfully";
		Integer data;
		try {
			data = service.exportPANData();
		}catch(Exception e) {
			LOG.error("Error generating xls for PAN data: {}, {}", e.getMessage(), e);
			message = "Some problem occurred while exporting PAN data";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);        		
         
	}
	
	@GetMapping("/downloadData")
	public ResponseEntity<InputStreamResource> downloadData(@RequestParam(value = "key") Integer cacheKey){
		
		ByteArrayInputStream in = service.getFromCache(cacheKey);
		// return IOUtils.toByteArray(in);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=pan-list.xlsx");
        headers.add("Content-Type", "application/vnd.ms-excel");
		
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
	}
	
	
}
