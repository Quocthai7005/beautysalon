package com.majestic.nails.http.controller;

import com.majestic.nails.util.Pages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AMainController {
	
	@RequestMapping(value={"/main", ""}, method=RequestMethod.GET)
	public String goMain() {	
		return Pages.A_MAIN;
	}
}
