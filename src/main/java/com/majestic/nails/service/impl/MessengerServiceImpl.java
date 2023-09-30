package com.majestic.nails.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.majestic.nails.entity.PageText;
import com.majestic.nails.repository.MessengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majestic.nails.service.MessengerService;

@Service
public class MessengerServiceImpl implements MessengerService {
	
	@Autowired
    MessengerRepository messengerRepository;

	@Override
	public Map<String, String> getMessengerInfo() {
		Map<String, String> result = new HashMap<String, String>();
		List<PageText> messengerInfo = messengerRepository.findBySection("messenger");
		messengerInfo.forEach(s -> {
			result.put(s.getTitle(), s.getContent());
		});
		return result;
	}

	@Override
	@Transactional
	public Boolean updateMessengerInfo(Map<String, String> info) {
		
		PageText appId = messengerRepository.findByTitle("appId");
		PageText pageId = messengerRepository.findByTitle("pageId");
		PageText xfbml = messengerRepository.findByTitle("xfbml");
		PageText version = messengerRepository.findByTitle("version");
		PageText autoLogAppEvents = messengerRepository.findByTitle("autoLogAppEvents");
		PageText minimized = messengerRepository.findByTitle("minimized");
		
		appId.setContent(info.get("appId"));
		pageId.setContent(info.get("pageId"));
		xfbml.setContent(info.get("xfbml"));
		version.setContent(info.get("version"));
		autoLogAppEvents.setContent(info.get("autoLogAppEvents"));
		minimized.setContent(info.get("minimized"));
		
		messengerRepository.save(appId);
		messengerRepository.save(pageId);
		messengerRepository.save(xfbml);
		messengerRepository.save(version);
		messengerRepository.save(autoLogAppEvents);
		messengerRepository.save(minimized);
		
		return true;
	}

}
