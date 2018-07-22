package com.doctor.spa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.service.MessengerService;
import com.doctor.spa.util.Pages;

@Controller
public class MessengerController {
	
	@Autowired
	MessengerService messengerService;

	@GetMapping(value = "/messenger/info")
	public ResponseEntity<ResponseBody<Map<String, String>>> getMessengerInfo() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, messengerService.getMessengerInfo()));
	}
	
	@PostMapping(value = "/admin/messenger/update")
	public ResponseEntity<ResponseBody<Boolean>> updateMessengerInfo(@RequestBody Map<String, String> dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, messengerService.updateMessengerInfo(dto)));
	}
	
	@RequestMapping(value={"/admin/messenger"}, method=RequestMethod.GET)
	public String goMain() {	
		return Pages.A_MESSENGER;
	}
}
