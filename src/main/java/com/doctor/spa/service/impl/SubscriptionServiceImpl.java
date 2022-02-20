package com.doctor.spa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doctor.spa.entity.Subscription;
import com.doctor.spa.repository.SubscriptionRepo;
import com.doctor.spa.service.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	SubscriptionRepo subscriptionRepo;

	@Override
	public Boolean subscribe(Subscription subscription) {
		subscriptionRepo.save(subscription);
		
		//send email with subscription code
		//create email template
		return null;
	}

	@Override
	public Boolean unSubscribe(String id) {
		Subscription subscription = subscriptionRepo.findById(id);
		subscription.setConfirm(false);
		subscriptionRepo.save(subscription);
		return null;
	}

	@Override
	public Boolean confirm(String id) {
		Subscription subscription = subscriptionRepo.findById(id);
		subscription.setConfirm(true);
		subscriptionRepo.save(subscription);
		return null;
	}

	@Override
	public List<Subscription> listAll() {
		return subscriptionRepo.findAll();
	}

}
