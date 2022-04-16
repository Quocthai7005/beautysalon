package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MessengerController {
	
	@Autowired
	MessengerService messengerService;

	@GetMapping(value = "/api/messenger/info")
	public ResponseEntity<ResponseBody<Map<String, String>>> getMessengerInfo() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, messengerService.getMessengerInfo()));
	}
	
	@PostMapping(value = "/api/admin/messenger/update")
	public ResponseEntity<ResponseBody<Boolean>> updateMessengerInfo(@RequestBody Map<String, String> dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, messengerService.updateMessengerInfo(dto)));
	}
}
