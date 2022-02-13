package com.doctor.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spa.service.AwsS3Service;

@Controller
@RequestMapping(value = "/s3")
public class AwsS3Controller {

	@Autowired
	AwsS3Service awsS3Service;

}
