package com.doctor.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "admin")
public class AwsS3Controller {

	@Autowired
	AwsS3Service awsS3Service;

	@GetMapping(value = "/bucket")
	public String toBucketList() {	
		return Pages.A_BUCKET_LIST;
	}

	@GetMapping(value = "/bucket/file")
	public String toFileDetail() {	
		return Pages.A_FILE_DETAIL;
	}

	@GetMapping(value="/bucket/files")
	public ResponseEntity<ResponseBody<Page<S3ObjectSummary>>> getFiles(
			Pageable pageable,
			@RequestParam(required = true) String directory) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getFiles(pageable, directory)));
	}

	@GetMapping(value="/bucket/files/no")
	public ResponseEntity<ResponseBody<Integer>> getFilesNo(
			@RequestParam(required = true) String directory) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getFilesNo(directory)));
	}
	
	@GetMapping(value="/bucket/file/detail")
	public ResponseEntity<ResponseBody<S3Object>> getFile(
			@RequestParam(required = true) String key) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getFile(key)));
	}
}
