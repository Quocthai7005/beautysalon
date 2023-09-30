package com.majestic.nails.service;

import java.util.List;

import com.majestic.nails.entity.Subscription;

public interface SubscriptionService {

	Boolean subscribe(Subscription subscription);

	Boolean unSubscribe(String email, String id);

	Boolean confirm(String email, String id);
	
	List<Subscription> listAll();
}
