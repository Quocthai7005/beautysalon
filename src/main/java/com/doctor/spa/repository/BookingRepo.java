package com.doctor.spa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long>{

}
