package com.doctor.spa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.service.BookingService;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/admin")
public class ABookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping(value="/bookings/all")
	public ResponseEntity<ResponseBody<Page<BookingDto>>> getAllBookings(@RequestParam(value = "status", required=false) String status, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.getBookingsWithStatus(pageable, status)));
	}

	@GetMapping(value="/bookings")
	public String goToBookings() {
		return Pages.A_BOOKING_LIST;
	}
}
