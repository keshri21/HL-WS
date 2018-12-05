package com.hlws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonS3Service {

	private AmazonS3 s3client;
	
	@Value("${aws.s3.endpointurl}")
	private String endpoint;
	@Value("${aws.s3.bucketname}")
	private String bucketname;
	@Value("${aws.s3.accesskey}")
	private String accesskey;
	@Value("${aws.s3.secretkey}")
	private String secretkey;
	
	@PostConstruct
	private void initializeS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accesskey, this.secretkey);
		this.s3client = AmazonS3ClientBuilder.standard().
				withCredentials(new AWSStaticCredentialsProvider(credentials)).
				withRegion("ap-south-1").build();
	}
	
	public String uploadFile(MultipartFile multipartFile, String type, HttpServletRequest request) {

		String fileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile, type, request);
			fileUrl = endpoint + "/" + bucketname + "/" + fileName;
			uploadFileTos3bucket(fileName, file);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private String generateFileName(MultipartFile multiPart, String type, HttpServletRequest request) throws Exception {
		String pan = request.getParameter("panno");
		String original = multiPart.getOriginalFilename();
		String extension = original.substring(original.lastIndexOf("."), original.length());
		switch (type) {
		case "pancopy":
			return pan.toUpperCase().concat(extension);
		case "rccopy":
			String vehicle = request.getParameter("vehicleno");
			if(StringUtils.isEmpty(vehicle)) {
				throw new Exception("Vehicle number is mandatory for RC copy upload");
			}
			return pan.concat("_").concat(vehicle).toUpperCase().concat(extension);
		case "declaration":
			return pan.concat("_declaration").toUpperCase().concat(extension);
		case "passbook":
			String accountno = request.getParameter("accountno");
			return pan.concat("_").concat(accountno).toUpperCase().concat(extension);
		case "docopy":
			String bspdo = request.getParameter("bspdo"),
					areado = request.getParameter("areado");
			return "DO_".concat(bspdo).concat("_").concat(areado).toUpperCase().concat(extension);
		default:
			throw new Exception("Invalid file upload request");
		}
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		this.s3client.putObject(new PutObjectRequest(bucketname, fileName, file).
				withCannedAcl(CannedAccessControlList.PublicRead));
	}
}
