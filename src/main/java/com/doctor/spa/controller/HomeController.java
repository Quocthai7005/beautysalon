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
		List<PageText> pageTexts = pageTextService.findByPage("home");
		List<ProductDto> services = serService.getAllServices();
		List<NewsDto> postDtos = postService.getLatestPost();
		model.addAttribute("postDtos", postDtos);
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("menuServices", services);
		model.addAttribute("menu", ConstUtil.menuHome);
		return Pages.HOME;
	}
	
	@RequestMapping(value="contact", method=RequestMethod.GET)
	public String goContact(Model model) {
		List<PageText> pageTexts = pageTextService.findByPage("home");
		List<ProductDto> services = serService.getAllServices();
		List<SubProductDto> childServiceDtos = childSerService.getHomeShownChildService();
		model.addAttribute("childServiceDtos", childServiceDtos);
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("menuServices", services);
		model.addAttribute("menu", ConstUtil.menuContact);
		return Pages.CONTACT;
	}
	
	@RequestMapping(value="booking", method=RequestMethod.GET)
	public String goBooking(Model model) {
		List<ProductDto> services = serService.getAllServices();
		model.addAttribute("menuServices", services);
		model.addAttribute("menu", ConstUtil.menuBooking);
		return Pages.BOOKING;
	}
}
