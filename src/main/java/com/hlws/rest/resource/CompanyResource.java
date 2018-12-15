package com.hlws.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.model.Company;
import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyResource {

	@Autowired
	CompanyService service;
	
	@PostMapping
	@ResponseBody
	public APIResponse<Company> create(@RequestBody Company company){
		String message = "Company created successfully";
		Company data;
		try {
			data = service.create(company);
		}catch(DuplicateKeyException de) {
			message = "Company with same unique short name already exists";
			return ResponseUtil.createFailedResponse(message);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Something went wrong: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@PutMapping(value="/{companyId}")
	@ResponseBody
	public APIResponse<Boolean> delete(@PathVariable("companyId") String companyId){
		String message = "Company deleted successfully";
		Boolean data;
		try {
			data = service.delete(companyId);
		}catch(Exception e) {
			e.printStackTrace();
			message = "Something went wrong: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
	
	@GetMapping
	@ResponseBody
	public APIResponse<List<Company>> get(){
		String message = "Company list retrievd successfully";
		List<Company> data;
		try {
			data = service.getAll();
		}catch(Exception e) {
			e.printStackTrace();
			message = "Couldn't retrieve company list: " + e.getMessage();
			return ResponseUtil.createFailedResponse(message);
		}
		return ResponseUtil.createSuccessResponse(message, data);
	}
}
