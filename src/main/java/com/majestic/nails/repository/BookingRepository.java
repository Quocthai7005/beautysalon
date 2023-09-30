package com.majestic.nails.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.majestic.nails.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	Page<Booking> findByStatus(String status, Pageable pageable);

	List<Booking> findByStatus(String status);

	Booking findById(long id);

}
