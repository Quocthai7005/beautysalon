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
	public String editService() {	
		return Pages.A_BUCKET_LIST;
	}

	@GetMapping(value="/bucket/news")
	public ResponseEntity<ResponseBody<Page<S3ObjectSummary>>> getNewsImages(
			Pageable pageable,
			@RequestParam(required = false) String lastKey) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getNewsImages(pageable, lastKey)));
	}

	@GetMapping(value="/bucket/news/no")
	public ResponseEntity<ResponseBody<Integer>> getNewsImagesNo(
			Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getNewsImagesNo()));
	}

	@GetMapping(value="/bucket/product/no")
	public ResponseEntity<ResponseBody<Integer>> getProductImagesNo(
			Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getProductImagesNo()));
	}

	@GetMapping(value="/bucket/product")
	public ResponseEntity<ResponseBody<Page<S3ObjectSummary>>> getProductImage(
			Pageable pageable,
			@RequestParam(required = false) String lastKey) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsS3Service.getProductImages(pageable, lastKey)));
	}
}
