package com.doctor.spa.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;
import com.doctor.spa.mapper.BookingMapper;
import com.doctor.spa.repository.BookingRepo;
import com.doctor.spa.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
	@Autowired
	BookingMapper bookingMapper;
	
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

	@Override
	public Page<BookingDto> getBookings(Pageable pageable) {
		Page<Booking> bookings = new PageImpl<>(Collections.emptyList());
		bookings = bookingRepo.findAll(pageable);
		List<BookingDto> bookingDtos = new ArrayList<>();
		for (Booking booking: bookings) {
			bookingDtos.add(bookingMapper.toDto(booking));
		}
		return new PageImpl<BookingDto>(bookingDtos);
	}

	@Override
	public Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status) {

		Page<Booking> bookings = new PageImpl<>(Collections.emptyList());
		if (status == null || "".equals(status)) {
			bookings = bookingRepo.findAll(pageable);
		} else {
			bookings = bookingRepo.findByStatus(status, pageable);
		}
		List<BookingDto> bookingDtos = new ArrayList<>();
		for (Booking booking: bookings) {
			bookingDtos.add(bookingMapper.toDto(booking));
		}
		return new PageImpl<BookingDto>(bookingDtos);
	}

	@Override
	public Integer getBookingsNo(String status) {
		List<Booking> bookings;
		if (status == null || "".equals(status)) {
			bookings = bookingRepo.findAll();
		} else {
			bookings = bookingRepo.findByStatus(status);
		}
		return bookings.size();
	}

	@Override
	public BookingDto getBookingDetail(long id) {
		Booking booking = bookingRepo.findById(id);
		return bookingMapper.toDto(booking);
	}

	@Override
	public Boolean updateStatus(long id, String status) {
		Booking booking = bookingRepo.findById(id);
		booking.setStatus(status);
		bookingRepo.save(booking);
		return true;
	}
}
