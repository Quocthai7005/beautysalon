package com.majestic.nails.http.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.BookingDto;
import com.majestic.nails.service.*;
import com.majestic.nails.util.Menus;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class HomeController {
	
	@Autowired
    PageTextService pageTextService;
	
	@Autowired
    ProductService serService;
	
	@Autowired
    PostService postService;
	
	@Autowired
    SubProductService childSerService;

	@Autowired
    BookingService bookingService;
	
	@RequestMapping(value={"home", "/"}, method=RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("menu", Menus.menuHome);
		return Pages.HOME;
	}
	
	@RequestMapping(value="contact", method=RequestMethod.GET)
	public String goContact(Model model) {
		model.addAttribute("menu", Menus.menuContact);
		return Pages.CONTACT;
	}

	@RequestMapping(value="pricelist", method=RequestMethod.GET)
	public String goPriceList(Model model) {
		model.addAttribute("serviceMap", childSerService.getAllAndGroupByParent());
		model.addAttribute("menu", Menus.menuPriceList);
		return Pages.PRICELIST;
	}
	
	@RequestMapping(value="booking", method=RequestMethod.GET)
	public String goBooking(Model model) {
		model.addAttribute("menuServices", childSerService.getAllAndGroupByParent());
		model.addAttribute("menu", Menus.menuBooking);
		return Pages.BOOKING;
	}

	@PostMapping(value = "/book")
	public ResponseEntity<ResponseBody<BookingDto>> createPost(
			@RequestPart BookingDto data) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, bookingService.createBooking(data)));
	}
}
