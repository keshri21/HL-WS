package com.hlws.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Area;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.AreaService;

@RestController
@RequestMapping("area")
public class AreaResource {

	@Autowired
	AreaService service;
	
	@PostMapping
	@ResponseBody
	public APIResponse<Area> create(@RequestBody Area area){
		String message = "Area created successfully";
		Area data;
		try {
			data = service.createArea(area);
		}catch(DuplicateKeyException de) {
			message = "Area name already exists";
			return ResponseUtil.createFailedResponse(message);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Something went wrong: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping
	@ResponseBody
	public APIResponse<Area> update(@RequestBody Area area){
		String message = "Area updated successfully";
		Area data;
		try {
			data = service.createArea(area);
		}catch(DuplicateKeyException de) {
			message = "Area name already exists";
			return ResponseUtil.createFailedResponse(message);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Something went wrong: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Area>> get(){
		String message = "Area list retrievd successfully";
		List<Area> data;
		try {
			data = service.getAll();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Something went wrong: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
