package com.majestic.nails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majestic.nails.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

	Subscription findByIdAndEmail(String id, String email);
}
