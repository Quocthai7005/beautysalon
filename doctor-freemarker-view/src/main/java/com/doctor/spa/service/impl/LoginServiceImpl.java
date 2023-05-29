package com.doctor.spa.service.impl;

import com.doctor.spa.client.ApiClient;
import com.doctor.spa.client.api.JsonParser;
import com.doctor.spa.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    static JsonParser<String> jsonParser;

    @Autowired
    ApiClient apiClient;

    @Override
    public String getLoginToken(String url, Map<String, String> params) {
        ResponseEntity<String> entity = apiClient.get(url, params);
        try {
            return jsonParser.parseJson(entity.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
