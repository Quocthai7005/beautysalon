package com.doctor.spa.api.controller.admin;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.UserDto;
import com.doctor.spa.entity.User;
import com.doctor.spa.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ApiUserController")
@RequestMapping(value = "/api/user/")
public class AdminUserManagementController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseBody<User>> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, userDetailsService.createUser(dto)));
    }
}
