package com.doctor.spa.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;
import com.doctor.spa.repository.BookingRepo;
import com.doctor.spa.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
	@Override
	public Boolean createBooking(BookingDto dto) {
		if (dto == null) {
			return false;
		}
		Booking booking = new Booking();
		booking.setName(dto.getName());
		booking.setEmail(dto.getEmail());
		booking.setPhone(dto.getPhone());
		booking.setQuestion(dto.getQuestion());
		booking.setStatus(dto.getStatus());
		LocalDateTime date = LocalDateTime.parse(dto.getConsultDate());
		booking.setConsultDate(date);
		bookingRepo.save(booking);
		return true;
	}

}
