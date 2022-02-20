package com.doctor.spa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long>{

	Subscription findByIdByEmail(String id, String email);
}
