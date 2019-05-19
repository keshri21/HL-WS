package com.hlws.rest.resource;

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

import com.hlws.dto.UserDTO;
import com.hlws.model.User;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.UserService;

@RestController
@RequestMapping("user")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public APIResponse<User> create(@RequestBody User user){
		String message = "User created successfully";
		User data;
		try {
			data = userService.save(user);
		}catch(Exception e) {
			message = "Some error occurred while creating user";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<User>> get(@RequestParam(value = "get", defaultValue = "active") String filter){
		String message = "user list retrieved successfully";
		List<User> data;
		try {
			data = userService.getListOfUser(filter);
		}catch(Exception e) {
			message = "Some error occurred while retrieving user list";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping
	@ResponseBody
	public APIResponse<User> update(@RequestBody User user){
		
		String message = "Some error occurred while updating user";
		try {
			if(userService.update(user)) {
				message = "User details updated successfully";
			}			
		}catch(Exception e) {			
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, user);
	}
	
	@GetMapping(value = "/{userName}")
	@ResponseBody
	public APIResponse<User> getOne(@PathVariable("userName") String userName){
		String message = "user retrieved successfully";
		User data;
		try {
			//TODO handle company name dynamically
			data = userService.findByUserName(userName, "hl");
		}catch(Exception e) {
			message = "Some error occurred while retrieving user";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@DeleteMapping(value = "/{userName}")
	@ResponseBody
	public APIResponse<String> delete(@PathVariable("userName") String userName){
		String message = "user deactivated successfully";
		String data = "Deleted";
		try {
			userService.deactivate(userName);
		}catch(Exception e) {
			message = "Some error occurred while deactivating user";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping(value = "/role/{rolename}")
	@ResponseBody
	public APIResponse<List<UserDTO>> getByRole(@PathVariable("rolename") String role){
		String message = "user list retrieved successfully";
		List<UserDTO> data;
		try {
			data = userService.getByRole(role);
		}catch(Exception e) {
			message = "Some error occurred while retrieving users";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping("/updatePassword")
	@ResponseBody
	public APIResponse<Void> updatePassword(@RequestBody User user){
		String message = "Some error occurred while updating password";
		try {
			if(userService.changePassword(user)) {
				message = "Password updated successfully";
			}
		}catch (Exception e) {			
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, null);
		
	}
}
