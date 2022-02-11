package com.doctor.spa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.doctor.spa.dto.BookingDto;

public interface BookingService {

	Boolean createBooking(BookingDto dto);

	Page<BookingDto> getBookings(Pageable pageable);

	Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status);
}
