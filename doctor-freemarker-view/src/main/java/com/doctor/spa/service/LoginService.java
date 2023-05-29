package com.doctor.spa.service;

import java.util.Map;

public interface LoginService {

    String getLoginToken(String url, Map<String, String> params);
}
