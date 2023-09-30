package com.majestic.nails.service;

import com.majestic.nails.dto.BookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

	BookingDto createBooking(BookingDto dto);

	Page<BookingDto> getBookings(Pageable pageable);

	Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status);

	Integer getBookingsNo(String status);
	
	BookingDto getBookingDetail(long id);
	
	Boolean updateStatus(long id, String status);
}
