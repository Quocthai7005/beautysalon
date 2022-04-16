package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.entity.Subscription;
import com.doctor.spa.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@PostMapping(value = "/subscribe")
	public ResponseEntity<ResponseBody<Boolean>> subscribe(@RequestParam Subscription subscription) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.subscribe(subscription)));
	}

	@GetMapping(value = "/subscribe/confirm/{id}")
	public ResponseEntity<ResponseBody<Boolean>> confirm(@PathVariable String id, @PathVariable String email) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.confirm(id, email)));
	}

	@GetMapping(value = "/unsubscribe/{id}")
	public ResponseEntity<ResponseBody<Boolean>> unSubscribe(@PathVariable String id, @PathVariable String email) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.unSubscribe(id, email)));
	}
	
}
