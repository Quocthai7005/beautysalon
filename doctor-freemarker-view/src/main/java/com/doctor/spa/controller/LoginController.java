package com.doctor.spa.controller;

import com.doctor.spa.entity.AuthRequest;
import com.doctor.spa.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.doctor.spa.util.ViewConstant.LOGIN_URL;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/admin/login")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) throws Exception {
        return loginService.getLoginToken(LOGIN_URL, authRequest);
    }
}
