package com.doctor.spa.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spa.service.SubProductService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.Menus;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/service")
public class ProductController {
	
	@Autowired
	ProductService serService;
	
	@Autowired
	SubProductService childSerService;
	
	@Autowired
	PageTextService pageTextService;
	
	@GetMapping(value = "/{url}")
	public String goToService(@PathVariable String url, Model model) {
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("service", serService.getServiceByUrl(url));
		model.addAttribute("otherServices", serService.getServiceOtherThan(url));
		model.addAttribute("menu", Menus.menuService);
		return Pages.SERVICE;
	}
	
	@GetMapping(value = "/{service}/{childService}")
	public String goToChildService(@PathVariable String service, @PathVariable String childService, Model model) {
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("otherChildServices", childSerService.getSubProductOtherThan(service, childService));
		model.addAttribute("childService", childSerService.getSubProductByUrl(childService));
		model.addAttribute("services", serService.getServiceOtherThan(service));
		model.addAttribute("menu", Menus.menuService);
		return Pages.SERVICE_DETAIL;
	}
}
