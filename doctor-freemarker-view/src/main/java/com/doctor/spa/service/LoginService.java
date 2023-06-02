package com.doctor.spa.service;

import com.doctor.spa.dto.AuthSuccessDto;
import com.doctor.spa.entity.AuthRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LoginService {

    ResponseEntity<String> getLoginToken(String url, AuthRequest authRequest) throws Exception;
}
