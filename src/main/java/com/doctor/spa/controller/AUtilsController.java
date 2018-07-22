package com.doctor.spa.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.ImageDto;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/admin")
public class AUtilsController {
	
	@Autowired
	PageTextService pageTextService;

	@RequestMapping(value="/utils", method=RequestMethod.GET)
	public String goMain() {	
		return Pages.A_PICTURE_LIST;
	}
	
	@RequestMapping(value="/login", method = {RequestMethod.GET})
	public String goLogin(Model model, String error, String logout) {

	    model.addAttribute("message", "");
	    model.addAttribute("error", false);
	    if (error != null) {
	      model.addAttribute("message", "Your username and password are invalid.");
	      model.addAttribute("error", true);
	    }
	    if (logout != null) {
	      model.addAttribute("message", "Logged out successfully.");
	    }	
		return Pages.A_LOGIN;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	@GetMapping(value="/image-list/all")
	public ResponseEntity<ResponseBody<List<ImageDto>>> getImages() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.getHeaderImage()));
	}
	
	@PostMapping(value="/image-update")
	public ResponseEntity<ResponseBody<Boolean>> getChildServices(@RequestBody List<ImageDto> images) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.updateImage(images)));
	}
	
	@GetMapping(value="/image-list/shown")
	public ResponseEntity<ResponseBody<List<ImageDto>>> getHeaderImages() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.getShownImage()));
	}
	
	@GetMapping(value="/contact/info")
	public ResponseEntity<ResponseBody<Map<String, String>>> getContactInfo() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.getContact()));
	}
	
	@PostMapping(value="/contact/update")
	public ResponseEntity<ResponseBody<Boolean>> updateContace(@RequestBody Map<String, String> contact) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.updateContact(contact)));
	}
}
