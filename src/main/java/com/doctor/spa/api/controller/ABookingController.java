package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("ApiBookingController")
@RequestMapping(value = "/api/admin")
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

	@PostMapping(value="/bookings/update")
	public ResponseEntity<ResponseBody<Boolean>> updateStatus(
			@RequestParam String status,
			@RequestParam long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.updateStatus(id, status)));
	}
}
