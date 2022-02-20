package com.doctor.spa.service;

import java.util.List;

import com.doctor.spa.entity.Subscription;

public interface SubscriptionService {

	Boolean subscribe(Subscription subscription);

	Boolean unSubscribe(String id);

	Boolean confirm(String id);
	
	List<Subscription> listAll();
}
