package com.doctor.spa.service.impl;

import com.doctor.spa.service.ApiClientService;
import com.doctor.spa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl extends PostService {

	@Autowired
	ApiClientService apiClientService;
}
