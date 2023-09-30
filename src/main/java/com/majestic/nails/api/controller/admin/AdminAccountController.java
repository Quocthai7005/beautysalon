package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.PasswordChange;
import com.majestic.nails.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ApiAccountController")
@RequestMapping(value = "/api/admin")
public class AdminAccountController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/api/account/update")
	public ResponseEntity<ResponseBody<Boolean>> updatePassword(@RequestBody PasswordChange dto) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, accountService.updatePassword(dto)));
	}

}
