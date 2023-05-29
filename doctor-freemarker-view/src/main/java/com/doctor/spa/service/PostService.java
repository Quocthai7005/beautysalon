package com.doctor.spa.service;

import com.doctor.spa.dto.PostDto;

import java.util.Map;

public interface PostService {
    PostDto getSinglePost(String url, Map<String, String> params);
}
