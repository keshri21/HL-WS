package com.hlws.rest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.User;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.util.DummyBuilder;

@RestController
@RequestMapping("user")
public class UserResource {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<Long> create(@RequestBody User user){
		String message;
		Long data  = 123l;
		try {
			message = "user created successfully";
		}catch(Exception e) {
			message = "Some error occurred while creating user";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<User>> get(){
		String message;
		List<User> data  = new ArrayList<User>();
		try {
			message = "user list retrieved successfully";
			data = DummyBuilder.createDummyUsers(4);
		}catch(Exception e) {
			message = "Some error occurred while retrieving user list";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value = "/{userName}")
	@ResponseBody
	public APIResponse<String> update(@PathVariable("userName") String userName, 
			@RequestBody User user){
		String message;
		String data  = "";
		try {
			message = "user updated successfully";
		}catch(Exception e) {
			message = "Some error occurred while updating user";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/{userName}")
	@ResponseBody
	public APIResponse<User> getOne(@PathVariable("userName") String userName){
		String message;
		User data  = new User();
		try {
			message = "user retrieved successfully";
			data = DummyBuilder.createDummyUsers(1).get(0);
		}catch(Exception e) {
			message = "Some error occurred while retrieving user";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@DeleteMapping(value = "/{userName}")
	@ResponseBody
	public APIResponse<String> deactivate(@PathVariable("userName") String userName){
		String message;
		String data  = "";
		try {
			message = "user deactivated successfully";
		}catch(Exception e) {
			message = "Some error occurred while deactivating user";
			return ResponseUtil.createFailedResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
