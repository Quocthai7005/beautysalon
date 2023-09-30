package com.majestic.nails.api.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("ApiMessengerController")
public class MessengerController {
	
	@Autowired
	MessengerService messengerService;

	@GetMapping(value = "/api/messenger/info")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Map<String, String>>> getMessengerInfo() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, messengerService.getMessengerInfo()));
	}
	
	@PostMapping(value = "/api/admin/messenger/update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> updateMessengerInfo(@RequestBody Map<String, String> dto) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, messengerService.updateMessengerInfo(dto)));
	}
}
