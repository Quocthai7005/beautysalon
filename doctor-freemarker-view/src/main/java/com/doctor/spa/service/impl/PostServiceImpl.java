package com.doctor.spa.service.impl;

import com.doctor.spa.client.ApiClient;
import com.doctor.spa.client.api.JsonParser;
import com.doctor.spa.client.impl.JsonParserImpl;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	static JsonParser<PostDto> jsonParser;

	@Autowired
	ApiClient apiClient;

	@PostConstruct
	private void initialize() {
		this.jsonParser = new JsonParserImpl<PostDto>(PostDto.class);
	}


	@Override
	public PostDto getSinglePost(String url, Map<String, String> params) {
		ResponseEntity<String> entity = apiClient.get(url, params);
		try {
			return jsonParser.parseJson(entity.getBody());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
