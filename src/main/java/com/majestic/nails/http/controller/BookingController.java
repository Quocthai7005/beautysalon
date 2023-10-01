package com.majestic.nails.http.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.BookingDto;
import com.majestic.nails.util.Menus;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.majestic.nails.service.BookingService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;

	@PostMapping(value = "/create")
	public ResponseEntity<ResponseBody<BookingDto>> createBooking(
			@RequestBody BookingDto data) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.createBooking(data)));
	}

	@RequestMapping(value="/booking-success", method= RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("menu", Menus.menuHome);
		return Pages.BOOKING_SUCCESS;
	}

}
