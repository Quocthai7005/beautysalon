package com.doctor.spa.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.doctor.spa.util.ViewConstant.GET_SINGLE_POST;
import static com.doctor.spa.util.ViewConstant.LOGIN_URL;

@Controller
@RequestMapping(value = "/admin/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/")
    public ResponseEntity<ResponseBody<String>> getPost(@RequestParam Map<String, String> parameters) {
        return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, loginService.getLoginToken(LOGIN_URL, parameters)));
    }
}
