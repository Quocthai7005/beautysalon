package com.majestic.nails.http.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.util.Pages;
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

import com.majestic.nails.dto.S3ObjectDto;
import com.majestic.nails.service.AwsS3Service;

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