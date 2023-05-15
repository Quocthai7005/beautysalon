package com.doctor.spa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

	Subscription findByIdAndEmail(String id, String email);
}
