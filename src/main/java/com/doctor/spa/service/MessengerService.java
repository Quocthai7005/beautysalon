package com.doctor.spa.service;

import java.util.Map;

import com.doctor.spa.entity.PageText;

public interface MessengerService {

	Map<String, String> getMessengerInfo();
	
	Boolean updateMessengerInfo(Map<String, String> info);
	
}
