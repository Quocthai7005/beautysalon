package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.UserDto;
import com.majestic.nails.entity.User;
import com.majestic.nails.service.impl.UserDetailsServiceImpl;
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
