package com.doctor.spa.service.impl;

import com.doctor.spa.client.ApiClient;
import com.doctor.spa.dto.AuthSuccessDto;
import com.doctor.spa.entity.AuthRequest;
import com.doctor.spa.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    ApiClient apiClient;

    @Override
    public ResponseEntity<String> getLoginToken(String url, AuthRequest authRequest) throws Exception {
        ObjectMapper ojm = new ObjectMapper();
        ResponseEntity<String> entity = apiClient.post(new HashMap<>(), url, ojm.writeValueAsString(authRequest), MediaType.APPLICATION_JSON);
        try {
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
