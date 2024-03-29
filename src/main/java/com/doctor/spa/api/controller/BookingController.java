package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ApiPublicBookingController")
@RequestMapping(value = "/api/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;

	@PostMapping(value = "/create")
	public ResponseEntity<ResponseBody<BookingDto>> createBooking(
			@RequestBody BookingDto data) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.createBooking(data)));
	}

}
