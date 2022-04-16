package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.PasswordChange;
import com.doctor.spa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ApiAccountController")
@RequestMapping(value = "/api/admin")
public class AAccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/api/account/update")
	public ResponseEntity<ResponseBody<Boolean>> updatePassword(@RequestBody PasswordChange dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, accountService.updatePassword(dto)));
	}

}
