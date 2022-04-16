package com.doctor.spa.api.controller;

import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.service.AwsBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api/admin")
public class AwsBillingController {

	@Autowired
	AwsBillingService awsBillingService;
	
	@GetMapping(value = "/billing/detail")
	public ResponseEntity<ResponseBody<GetCostAndUsageResult>> billingDetail(@RequestParam String startDate, @RequestParam String endDate) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, awsBillingService.getCEWithDimension(startDate, endDate)));
	}
}
