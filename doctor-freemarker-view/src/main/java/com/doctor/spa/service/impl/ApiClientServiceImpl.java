package com.doctor.spa.service.impl;

import com.doctor.spa.client.ApiClient;
import com.doctor.spa.service.ApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiClientServiceImpl implements ApiClientService {

    @Autowired
    ApiClient apiClient;



}
