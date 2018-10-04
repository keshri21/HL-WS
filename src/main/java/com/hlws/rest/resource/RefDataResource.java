package com.hlws.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.RefData;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.RefDataService;

@RestController
@RequestMapping("refdata")
public class RefDataResource {

	@Autowired
	RefDataService refDataService;
	@GetMapping
	@ResponseBody
	public APIResponse<RefData> getRefData(){
		String message = "Ref data retrieved successfully";
		RefData data;
		try {
			data = refDataService.getRefData();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Some problem occured while retrieving ref data";
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
