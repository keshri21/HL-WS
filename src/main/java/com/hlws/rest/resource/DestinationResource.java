package com.hlws.rest.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;


@RestController
@RequestMapping("destination")
public class DestinationResource {

	@PostMapping
	@ResponseBody
	public APIResponse<String> add(@RequestBody String destination){
		String message;
		String data = "";
		try {
			message = "Destination added successfully";
		}catch(Exception e) {
			message = "Some error ocurred while adding destination";
			return ResponseUtil.createSuccessResponse(message, data);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
