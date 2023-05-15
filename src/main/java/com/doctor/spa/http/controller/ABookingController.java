package com.doctor.spa.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(value="/bookings/no")
	public ResponseEntity<ResponseBody<Integer>> getChildServiceNo(@RequestParam String status) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.getBookingsNo(status)));
	}

	@GetMapping(value="/bookings/detail/{id}")
	public ResponseEntity<ResponseBody<BookingDto>> getBookingDetail(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.getBookingDetail(id)));
	}

	@GetMapping(value="/bookings")
	public String goToBookings() {
		return Pages.A_BOOKING_LIST;
	}

	@GetMapping(value="/bookings/detail")
	public String goToBookingDetail() {
		return Pages.A_BOOKING_DETAIL;
	}

	@PostMapping(value="/bookings/update")
	public ResponseEntity<ResponseBody<Boolean>> updateStatus(
			@RequestParam String status,
			@RequestParam long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.updateStatus(id, status)));
	}
}
