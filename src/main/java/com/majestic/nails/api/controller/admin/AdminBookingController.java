package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.BookingDto;
import com.majestic.nails.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("ApiBookingController")
@RequestMapping(value = "/api/admin")
public class AdminBookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping(value="/bookings/all")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Page<BookingDto>>> getAllBookings(@RequestParam(value = "status", required=false) String status, Pageable pageable) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, bookingService.getBookingsWithStatus(pageable, status)));
	}

	@GetMapping(value="/bookings/no")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Integer>> getChildServiceNo(@RequestParam String status) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, bookingService.getBookingsNo(status)));
	}

	@GetMapping(value="/bookings/detail/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<BookingDto>> getBookingDetail(@PathVariable long id) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, bookingService.getBookingDetail(id)));
	}

	@PostMapping(value="/bookings/update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> updateStatus(
			@RequestParam String status,
			@RequestParam long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.updateStatus(id, status)));
	}
}
