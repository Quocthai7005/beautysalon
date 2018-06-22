package com.doctor.spa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.dto.ServiceGroupDto;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.service.ChildSerService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ServiceGroupService;
import com.doctor.spa.util.ConstUtil;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {
	
	@Autowired
	ServiceGroupService serService;
	
	@Autowired
	ChildSerService childSerService;
	
	@Autowired
	PageTextService pageTextService;
	
	@GetMapping(value = "/{url}")
	public String goToService(@PathVariable String url, Model model) {
		ServiceGroupDto service = serService.getServiceByUrl(url);
		List<ServiceGroupDto> otherServices = serService.getServiceOtherThan(url);
		List<ServiceGroupDto> menuServices = serService.getAllServices();
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
		ChildServiceDto childServiceDto = childSerService.getChildServiceByUrl(childService);
		List<ChildServiceDto> otherChildServiceDtos = childSerService.getChildServiceOtherThan(service, childService);
		List<ServiceGroupDto> otherServiceDtos = serService.getServiceOtherThan(service);
		List<ServiceGroupDto> menuServices = serService.getAllServices();
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
