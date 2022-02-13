package com.doctor.spa.service;

import java.util.Map;

public interface MessengerService {

	Map<String, String> getMessengerInfo();
	
	Boolean updateMessengerInfo(Map<String, String> info);
	
}
