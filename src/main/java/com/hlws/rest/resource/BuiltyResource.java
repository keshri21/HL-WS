package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.hlws.dto.BuiltyDTO;
import com.hlws.model.Builty;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.BuiltyService;


@RestController
@RequestMapping("builty")
public class BuiltyResource {

	@Autowired
	private BuiltyService builtyService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Builty> create(@RequestBody Builty builty){
		
		String message = "Builty created successfully";
		Builty data;
		try {
			data = builtyService.createBuilty(builty);
		}catch(Exception e) {
			message = "Internal Server Error: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data); //TODO return created builty nummber
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Builty>> get(@RequestParam(value = "get", defaultValue = "active") String filter){
		String message = "Builties retrieved successfully";
		
		//TODO use filter query param to return all or complete list else return active builties
		List<Builty> builtyList = new ArrayList<Builty>();
		try {
			builtyList = builtyService.getBuiltyList(filter);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while retrieving builties";	
			return ResponseUtil.createFailedResponse(message, builtyList);
		}
		return ResponseUtil.createSuccessResponse(message, builtyList);
	}
	
	@PutMapping(value = "/receipt")
	@ResponseBody
	public APIResponse<String> updateReceipt(@RequestBody List<BuiltyDTO> builtyList){
		String message = "Builty receipt updated successfully";
		String data = "";
		try {
			builtyService.updateReceipt(builtyList);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while updating receipt";	
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{builtyId}")
	@ResponseBody
	public APIResponse<Builty> getOne(@PathVariable("builtyId") String builtyId){
		String message = "Builty retrieved successfully";
		Builty builty = new Builty();
		try {
			builty = builtyService.getOne(builtyId);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while retrieving builty";	
			return ResponseUtil.createFailedResponse(message, builty);
		}
		return ResponseUtil.createSuccessResponse(message, builty);
	}
	
	@PutMapping
	@ResponseBody
	public APIResponse<String> update(@RequestBody Builty builty){
		if(builty.getId() == null) {
			return ResponseUtil.createFailedResponse("Builty id must be set while updating builty details");
		}
		String message = "Builty updated successfully";
		String data = "updated";
		try {
			builtyService.update(builty);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while updating builty";	
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@DeleteMapping(value = "/{builtyId}")
	@ResponseBody
	public APIResponse<Boolean> delete(@PathVariable("builtyId") String builtyId){
		String message = "Builty deleted successfully";
		Boolean data;
		try {
			data = builtyService.delete(builtyId);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while deleting builty";	
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{builtyId}/approve")
	@ResponseBody
	public APIResponse<String> approve(@PathVariable("builtyId") String builtyId){
		String message = "Builty approved successfully";
		String data = "";
		try {
			builtyService.approve(builtyId);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some error occured while approving builty";	
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping("/temp")
	@ResponseBody
	public APIResponse<Builty> save(@RequestBody Builty builty){
		String message = "Builty saved temporarily";
		Builty data;
		try {
			data = builtyService.saveBuilty(builty);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some prooblem occured while saving builty";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	
	@GetMapping("/temp")
	@ResponseBody
	public APIResponse<List<Builty>> getSavedList(){
		String message = "Saved list retrieved successfully";
		List<Builty> data;
		try {
			data = builtyService.getAllFromTemp();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem ocucred while retriving saved list";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping("/temp/{tempBuiltyId}")
	@ResponseBody
	public APIResponse<Builty> getOneFromTemp(@PathVariable("tempBuiltyId") String id){
		String message = "Saved builty retrieved successfully";
		Builty data;
		try {
			data = builtyService.getOneFromTemp(id);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem ocucred while retriving saved builty";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
}
