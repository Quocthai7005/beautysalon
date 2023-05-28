package com.doctor.spa.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;
import com.doctor.spa.mapper.BookingMapper;
import com.doctor.spa.repository.BookingRepository;
import com.doctor.spa.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final BookingMapper bookingMapper;

	public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
		this.bookingRepository = bookingRepository;
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
		bookingRepository.save(booking);
		return dto;
	}

	@Override
	public Page<BookingDto> getBookings(Pageable pageable) {
		return bookingRepository.findAll(pageable).map(bookingMapper::toDto);
	}

	@Override
	public Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status) {
		return ((status == null || "".equals(status) ?
				bookingRepository.findAll(pageable) :
				bookingRepository.findByStatus(status, pageable)))
				.map(bookingMapper::toDto);
	}

	@Override
	public Integer getBookingsNo(String status) {
		List<Booking> bookings;
		if (status == null || "".equals(status)) {
			bookings = bookingRepository.findAll();
		} else {
			bookings = bookingRepository.findByStatus(status);
		}
		return bookings.size();
	}

	@Override
	public BookingDto getBookingDetail(long id) {
		Booking booking = bookingRepository.findById(id);
		return bookingMapper.toDto(booking);
	}

	@Override
	public Boolean updateStatus(long id, String status) {
		Booking booking = bookingRepository.findById(id);
		booking.setStatus(status);
		bookingRepository.save(booking);
		return true;
	}
}
