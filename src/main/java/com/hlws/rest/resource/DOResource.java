package com.hlws.rest.resource;

import java.util.ArrayList;
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

import com.hlws.model.DO;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.DOService;
import com.hlws.util.DummyBuilder;

@RestController
@RequestMapping("do")
public class DOResource {
	
//	@Autowired
//	private DOService doService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Long> create(@RequestBody DO doObj) {
		
		String message = "DO created successfully";
		Long data = 123l;
		return ResponseUtil.createSuccessResponse(message, data); //TODO return created doId
	}
	
	@PutMapping(value = "/{doId}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public APIResponse<String> update(@PathVariable("doId") Long doId, @RequestBody DO doObj) {
		
		String message = "DO created successfully";
		String data = "updated";
		return ResponseUtil.createSuccessResponse(message, data); 
	}
	
	@GetMapping(value = "/{doId}")
	@ResponseBody
	public APIResponse<DO> getOne(@PathVariable("doId") Long doId){
		
		String message = "DO created successfully";
		DO data = new DO();
		data = DummyBuilder.createDummyDO(1).get(0);
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<DO>> getAll(){
		
		String message = "DO retrieved successfully";
		List<DO> data = new ArrayList<DO>();
		data = DummyBuilder.createDummyDO(3);
		
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/active")
	@ResponseBody
	public APIResponse<List<DO>> getActive(){
		
		String message = "DO retrieved successfully";
		List<DO> data = new ArrayList<DO>();
		data = DummyBuilder.createDummyDO(2);
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/completed")
	@ResponseBody
	public APIResponse<List<DO>> getCompleted(){
		
		String message = "DO retrieved successfully";
		List<DO> data = new ArrayList<DO>();
		data = DummyBuilder.createDummyDO(2);
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
