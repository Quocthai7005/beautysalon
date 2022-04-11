package com.doctor.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.service.SubProductService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.ConstUtil;
import com.doctor.spa.util.Pages;

@Controller
public class HomeController {
	
	@Autowired
	PageTextService pageTextService;
	
	@Autowired
	ProductService serService;
	
	@Autowired
	NewsService postService;
	
	@Autowired
	SubProductService childSerService;
	
	@RequestMapping(value={"home", "/"}, method=RequestMethod.GET)
	public String goHome(Model model) {
		model.addAttribute("postDtos", postService.getLatestPost());
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("menu", ConstUtil.menuHome);
		return Pages.HOME;
	}
	
	@RequestMapping(value="contact", method=RequestMethod.GET)
	public String goContact(Model model) {
		model.addAttribute("childServiceDtos", childSerService.getHomeShownChildService());
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("menu", ConstUtil.menuContact);
		return Pages.CONTACT;
	}
	
	@RequestMapping(value="booking", method=RequestMethod.GET)
	public String goBooking(Model model) {
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("menu", ConstUtil.menuBooking);
		return Pages.BOOKING;
	}
}
