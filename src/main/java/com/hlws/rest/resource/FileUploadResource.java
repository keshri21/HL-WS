package com.hlws.rest.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hlws.response.APIResponse;
import com.hlws.response.ResponseUtil;
import com.hlws.service.AmazonS3Service;

@RestController
@RequestMapping("upload")
public class FileUploadResource {

	AmazonS3Service awsservice;
	
	@Autowired
	public FileUploadResource(AmazonS3Service awsservice) {
		this.awsservice = awsservice;
	}
	
	@PostMapping(value = "/{type}")
	public APIResponse<String> upload(@RequestPart(value = "file") MultipartFile file, 
			@PathVariable("type") String type, 
			HttpServletRequest request){
		String data = "";
		try {
			data = awsservice.uploadFile(file, type, request);		
		}catch(Exception e) {
			ResponseUtil.createFailedResponse("some problem while uploading files to AWS s3");
		}
		return ResponseUtil.createSuccessResponse("File uploaded successfully", data);
	}
}
