package com.doctor.spa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/admin")
public class AMainController {
	
	@RequestMapping(value={"/main", ""}, method=RequestMethod.GET)
	public String goMain() {	
		return Pages.A_MAIN;
	}
}
