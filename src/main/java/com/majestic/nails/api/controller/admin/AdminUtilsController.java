package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.ImageDto;
import com.majestic.nails.service.PageTextService;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller("ApiUtilsController")
@RequestMapping(value = "/api/admin")
public class AdminUtilsController {
	
	@Autowired
	PageTextService pageTextService;
	
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
	
	@GetMapping(value="/image-list/all")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<List<ImageDto>>> getImages() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, pageTextService.getHeaderImage()));
	}
	
	@PostMapping(value="/image-update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> getChildServices(@RequestBody List<ImageDto> images) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, pageTextService.updateImage(images)));
	}
	
	@GetMapping(value="/image-list/shown")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<List<ImageDto>>> getHeaderImages() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, pageTextService.getShownImage()));
	}
	
	@GetMapping(value="/contact/info")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Map<String, String>>> getContactInfo() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, pageTextService.getContact()));
	}
	
	@PostMapping(value="/contact/update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> updateContace(@RequestBody Map<String, String> contact) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, pageTextService.updateContact(contact)));
	}
}
