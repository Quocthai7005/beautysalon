package com.doctor.spa.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	private final BookingRepo bookingRepo;
	private final BookingMapper bookingMapper;

	public BookingServiceImpl(BookingRepo bookingRepo, BookingMapper bookingMapper) {
		this.bookingRepo = bookingRepo;
		this.bookingMapper = bookingMapper;
	}

	@Override
	public BookingDto createBooking(BookingDto dto) {
		Booking booking = new Booking();
		booking.setName(dto.getName());
		booking.setEmail(dto.getEmail());
		booking.setPhone(dto.getPhone());
		booking.setQuestion(dto.getQuestion());
		booking.setStatus(dto.getStatus());
		LocalDateTime date = LocalDateTime.parse(dto.getConsultDate());
		booking.setConsultDate(date);
		bookingRepo.save(booking);
		return dto;
	}

	@Override
	public Page<BookingDto> getBookings(Pageable pageable) {
		return bookingRepo.findAll(pageable).map(bookingMapper::toDto);
	}

	@Override
	public Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status) {
		return ((status == null || "".equals(status) ?
				bookingRepo.findAll(pageable) :
				bookingRepo.findByStatus(status, pageable)))
				.map(bookingMapper::toDto);
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
