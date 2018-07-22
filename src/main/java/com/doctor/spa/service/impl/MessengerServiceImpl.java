package com.doctor.spa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.PageText;
import com.doctor.spa.repository.MessengerRepo;
import com.doctor.spa.service.MessengerService;

@Service
public class MessengerServiceImpl implements MessengerService {
	
	@Autowired
	MessengerRepo messengerRepo;

	@Override
	public Map<String, String> getMessengerInfo() {
		Map<String, String> result = new HashMap<String, String>();
		List<PageText> messengerInfo = messengerRepo.findBySection("messenger");
		messengerInfo.forEach(s -> {
			result.put(s.getTitle(), s.getContent());
		});
		return result;
	}

	@Override
	@Transactional
	public Boolean updateMessengerInfo(Map<String, String> info) {
		
		PageText appId = messengerRepo.findByTitle("appId");
		PageText pageId = messengerRepo.findByTitle("pageId");
		PageText xfbml = messengerRepo.findByTitle("xfbml");
		PageText version = messengerRepo.findByTitle("version");
		PageText autoLogAppEvents = messengerRepo.findByTitle("autoLogAppEvents");
		PageText minimized = messengerRepo.findByTitle("minimized");
		
		appId.setContent(info.get("appId"));
		pageId.setContent(info.get("pageId"));
		xfbml.setContent(info.get("xfbml"));
		version.setContent(info.get("version"));
		autoLogAppEvents.setContent(info.get("autoLogAppEvents"));
		minimized.setContent(info.get("minimized"));
		
		messengerRepo.save(appId);
		messengerRepo.save(pageId);
		messengerRepo.save(xfbml);
		messengerRepo.save(version);
		messengerRepo.save(autoLogAppEvents);
		messengerRepo.save(minimized);
		
		return true;
	}

}
