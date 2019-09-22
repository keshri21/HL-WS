package com.hlws.rest.resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private final Logger LOG = LoggerFactory.getLogger(BuiltyResource.class);

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
			LOG.error("Error creating builty: {}, {}", e.getMessage(), e);
			message = "Couldn't create bilty: " + e.getMessage();
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
			LOG.error("Error retrieving list of {} builty: {}, {}", filter, e.getMessage(), e);
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
			LOG.error("Error updating builty receipt: {}, {}", e.getMessage(), e);
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
			LOG.error("Erro creating DO: {}, {}", e.getMessage(), e);
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
			LOG.error("Error updating builty: {}, {}", e.getMessage(), e);
			message = "Couldn't update bilty: " + e.getMessage();	
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
			LOG.error("Error deleting builty {}: {}, {}", builtyId, e.getMessage(), e);
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
			LOG.error("Error approving builty {}: {}, {}", builtyId, e.getMessage(), e);
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
			LOG.error("Error in saving builty temporarily: {}, {}", e.getMessage(), e);
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
			LOG.error("Error retrieving saved builty list: {}, {}", e.getMessage(), e);
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
			LOG.error("Error retrieving builty {} from temporary list: {}, {}", id, e.getMessage(), e);
			message = "Some problem occurred while retrieving saved builty";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping("/readyForPayments")
	@ResponseBody
	public APIResponse<List<BuiltyDTO>> getBuiltiesForPayments(){
		String message = "Bilty list ready for payments retrieved successfully";
		List<BuiltyDTO> data;
		try {
			data = builtyService.getBuiltiesForPayments();
		}catch(Exception e) {
			LOG.error("Error retrieving builty list which are ready for payment: ", e);
			message = "Some problem occurred while retrieving list ready for payments";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping("/initiatedPayments")
	@ResponseBody
	public APIResponse<List<BuiltyDTO>> getBuiltiesForInitiatedPayments(){
		String message = "Bilty list for initiated payments retrieved successfully";
		List<BuiltyDTO> data;
		try {
			data = builtyService.getBuiltiesForInitiatedPayments();
		}catch(Exception e) {
			LOG.error("Error retrieving builty list for which payment is initiated: ", e);
			message = "Some problem occurred while retrieving bilty with initiated payments";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PostMapping("/payment/instruction")
	@ResponseBody
	public APIResponse<Integer> exportInstructions(@RequestBody List<BuiltyDTO> builties) throws IOException{
		String message = "Cache key generated successfully";
		Integer data;
		try {
			data = builtyService.getInstructions(builties);
		}catch(Exception e) {
			e.printStackTrace();
			LOG.error("Error generating payment instruction: {}, {}", e.getMessage(), e);
			message = "Some problem occurred while generating instruction for payments";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, data);			
			
	}
	
	@GetMapping("/payment/downloadInstruction")
	public ResponseEntity<InputStreamResource> downloadInstructions(@RequestParam(value = "key") Integer cacheKey){
		
		ByteArrayInputStream in = builtyService.getFromCache(cacheKey);
		// return IOUtils.toByteArray(in);
		
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=freight-payment.xlsx");
        headers.add("Content-Type", "application/vnd.ms-excel");
		
		 return ResponseEntity
	                .ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
	}
	
	@PutMapping("/revertInstruction")
	@ResponseBody
	public APIResponse<Boolean> resetInstruction(@RequestBody List<BuiltyDTO> bilties){
		String message = "Payment instruction reverted successfully";
		try {
			builtyService.revertPaymentInstruction(bilties);
		}catch(Exception e) {
			LOG.error("Error in reseting payment instruction {}", e);
			message = "Some problem occurred while reverting payment instruction";
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, true);
	}
	
	@PutMapping("/markComplete")
	@ResponseBody
	public APIResponse<Boolean> markComplete(@RequestBody List<BuiltyDTO> bilties){
		String message = "Payment marked complete successfully for selected PANs";
		try {
			builtyService.markForPaymentCompletion(bilties);
		}catch(Exception e) {
			LOG.error("Error in marking payment complete {}", e);
			return ResponseUtil.createFailedResponse(message, null);
		}
		return ResponseUtil.createSuccessResponse(message, true);
	}
	
	
}
