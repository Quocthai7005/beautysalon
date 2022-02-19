package com.doctor.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.service.SubscriptionService;

@Controller
@RequestMapping(value = "/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@GetMapping(value = "/subscribe/{id}")
	public ResponseEntity<ResponseBody<Boolean>> subscribe(@PathVariable String id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.subscribe(id)));
	}

	@GetMapping(value = "/subscribe/confirm/{id}")
	public ResponseEntity<ResponseBody<Boolean>> subscribe(@PathVariable String id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.subscribe(id)));
	}

	@GetMapping(value = "/unsubscribe/{id}")
	public ResponseEntity<ResponseBody<Boolean>> unSubscribe(@PathVariable String id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.unSubscribe(id)));
	}
}
