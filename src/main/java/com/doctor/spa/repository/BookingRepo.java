package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doctor.spa.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

	Page<Booking> findByStatus(String status, Pageable pageable);

	List<Booking> findByStatus(String status);

	Booking findById(long id);

}
