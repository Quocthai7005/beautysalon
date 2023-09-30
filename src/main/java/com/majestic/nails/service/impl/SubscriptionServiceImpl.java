package com.majestic.nails.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.majestic.nails.entity.Mail;
import com.majestic.nails.entity.Subscription;
import com.majestic.nails.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majestic.nails.service.MailService;
import com.majestic.nails.service.SubscriptionService;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
    SubscriptionRepository subscriptionRepository;

	@Autowired
	MailService mailService;

	@Autowired
	RedisTemplate<String, ?> redisTemplate;

	@Override
	public Boolean subscribe(Subscription subscription) {
		subscriptionRepository.save(subscription);
		Mail subscribeMail = new Mail();
		subscribeMail.setMailFrom("nguyenquocthai7005@gmail.com");
		subscribeMail.setMailTo(subscription.getEmail());
		subscribeMail.setMailSubject("subscription email");

		String id = "uuid-" + LocalDateTime.now().toString();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", subscription.getFistName());
		model.put("lastName", subscription.getLastName());
		model.put("subscriptionUrl",
				"http://localhost:8080/subscribe/confirm?id=" + id + "&email=" + subscription.getEmail());
		subscribeMail.setModel(model);

		mailService.sendEmail(subscribeMail);
		return true;
	}

	@Override
	public Boolean unSubscribe(String email, String id) {
		Subscription subscription = subscriptionRepository.findByIdAndEmail(id, email);
		if (subscription != null) {
			subscription.setConfirm(false);
			subscriptionRepository.save(subscription);
			return true;
		}
		return false;
	}

	@Override
	public List<Subscription> listAll() {
		redisTemplate.opsForList();
		return subscriptionRepository.findAll();
	}

	@Override
	public Boolean confirm(String email, String id) {
		Subscription subscription = subscriptionRepository.findByIdAndEmail(id, email);
		if (subscription != null) {
			subscription.setConfirm(true);
			subscriptionRepository.save(subscription);
			return true;
		}
		return false;
	}

}
