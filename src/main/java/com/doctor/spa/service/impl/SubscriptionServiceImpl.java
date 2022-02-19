package com.doctor.spa.service.impl;

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
		return null;
	}

	@Override
	public Boolean unSubscribe(String id) {
		return null;
	}

	@Override
	public Boolean confirm(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
