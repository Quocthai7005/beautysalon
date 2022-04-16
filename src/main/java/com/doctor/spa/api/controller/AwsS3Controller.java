package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.S3ObjectDto;
import com.doctor.spa.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("ApiS3Controller")
@RequestMapping(value = "/api/admin")
public class AwsS3Controller {

	@Autowired
	AwsS3Service awsS3Service;

	@GetMapping(value="/bucket/files")
	public ResponseEntity<ResponseBody<Page<S3ObjectDto>>> getFiles(
			Pageable pageable,
			@RequestParam(required = true) String directory) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getFiles(pageable, directory)));
	}

	@GetMapping(value="/bucket/files/no")
	public ResponseEntity<ResponseBody<Integer>> getFilesNo(
			@RequestParam(required = true) String directory) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getFilesNo(directory)));
	}
	
	@DeleteMapping(value = "/bucket/file/remove")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(
			@RequestParam String name,
			@RequestParam String directory) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.deleteFile(directory + "/" + name)));
	}
}
