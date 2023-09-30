package com.majestic.nails.api.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.entity.Subscription;
import com.majestic.nails.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("ApiSubscriptionController")
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@PostMapping(value = "/subscribe")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> subscribe(@RequestParam Subscription subscription) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, subscriptionService.subscribe(subscription)));
	}

	@GetMapping(value = "/subscribe/confirm/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> confirm(@PathVariable String id, @PathVariable String email) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, subscriptionService.confirm(id, email)));
	}

	@GetMapping(value = "/unsubscribe/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> unSubscribe(@PathVariable String id, @PathVariable String email) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subscriptionService.unSubscribe(id, email)));
	}
	
}
