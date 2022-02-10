package com.doctor.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.service.SubProductService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.ConstUtil;
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
		ProductDto service = serService.getServiceByUrl(url);
		List<ProductDto> otherServices = serService.getServiceOtherThan(url);
		List<ProductDto> menuServices = serService.getAllServices();
		List<PageText> pageTexts = pageTextService.findByPage("home");
		model.addAttribute("menuServices", menuServices);
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("service", service);
		model.addAttribute("otherServices", otherServices);
		model.addAttribute("menu", ConstUtil.menuService);
		return Pages.SERVICE;
	}
	
	@GetMapping(value = "/{service}/{childService}")
	public String goToChildService(@PathVariable String service, @PathVariable String childService, Model model) {
		SubProductDto childServiceDto = childSerService.getChildServiceByUrl(childService);
		List<SubProductDto> otherChildServiceDtos = childSerService.getChildServiceOtherThan(service, childService);
		List<ProductDto> otherServiceDtos = serService.getServiceOtherThan(service);
		List<ProductDto> menuServices = serService.getAllServices();
		List<PageText> pageTexts = pageTextService.findByPage("home");
		model.addAttribute("menuServices", menuServices);
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("otherChildServices", otherChildServiceDtos);
		model.addAttribute("childService", childServiceDto);
		model.addAttribute("services", otherServiceDtos);
		model.addAttribute("menu", ConstUtil.menuService);
		return Pages.SERVICE_DETAIL;
	}
}
