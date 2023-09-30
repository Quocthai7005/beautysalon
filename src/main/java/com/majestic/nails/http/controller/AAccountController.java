package com.majestic.nails.http.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.majestic.nails.dto.PasswordChange;
import com.majestic.nails.service.AccountService;

@Controller
@RequestMapping(value = "/admin")
public class AAccountController {
	
	@Autowired
	AccountService accountService;

	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String goMain(Authentication authentication, Model model) {
		model.addAttribute("username", authentication.getName());
		return Pages.A_ACCOUNT;
	}
	
	@PostMapping(value = "/account/update")
	public ResponseEntity<ResponseBody<Boolean>> updatePassword(@RequestBody PasswordChange dto) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, accountService.updatePassword(dto)));
	}

}
