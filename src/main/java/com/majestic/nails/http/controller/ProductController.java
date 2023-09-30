package com.majestic.nails.http.controller;

import com.majestic.nails.util.Menus;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.majestic.nails.service.SubProductService;
import com.majestic.nails.service.PageTextService;
import com.majestic.nails.service.ProductService;

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
		model.addAttribute("otherChildServices", childSerService.getChildServiceOtherThan(service, childService));
		model.addAttribute("childService", childSerService.getChildServiceByUrl(childService));
		model.addAttribute("services", serService.getServiceOtherThan(service));
		model.addAttribute("menu", Menus.menuService);
		return Pages.SERVICE_DETAIL;
	}
}
