package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller("ApiProductController")
@RequestMapping(value = "/api/admin")
public class AProductController {
	
	@Autowired
	ProductService prodService;
	
	@GetMapping(value = "/service-group-edit/{id}")
	public String editService(@PathVariable long id) {	
		return Pages.A_SERVICE_GROUP_EDIT;
	}
	
	@GetMapping(value="/service-group-list/search")
	public ResponseEntity<ResponseBody<Page<ProductDto>>> getServiceGroups(Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServices(pageable)));
	}
	
	@GetMapping(value="/service-group-list/all")
	public ResponseEntity<ResponseBody<List<ProductDto>>> getAllServiceGroups() {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getAllServices()));
	}
	
	@GetMapping(value = "/service-group-list/no")
	public ResponseEntity<ResponseBody<Integer>> getServiceGroupsNo() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServiceNo()));
	}
	
	@PostMapping(value = "/service-group-create")
	public ResponseEntity<ResponseBody<Boolean>> createService(
			@RequestPart MultipartFile imgFile,
			@RequestPart ProductDto data) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.createService(data, imgFile)));
	}
	
	@PostMapping(value = "/service-group-update")
	public ResponseEntity<ResponseBody<ProductDto>> updateService(@RequestBody ProductDto dto) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.updateService(dto)));
	}
	
	@GetMapping(value = "/service-group-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(prodService.validateUrl(id, url));
	}
	
	@GetMapping(value = "/service-group-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(prodService.validateUrlNoId(url));
	}
	
	@DeleteMapping(value = "/service-group-delete/{id}")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.deleteService(id)));
	}
	
	@RequestMapping(value="/service/all", method=RequestMethod.GET)
	public ResponseEntity<ResponseBody<Page<ProductDto>>> getAllServices(Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServices(pageable)));
	}
	
	@RequestMapping(value="/service/no", method=RequestMethod.GET)
	public ResponseEntity<ResponseBody<Integer>> getServicesNo() {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServiceNo()));
	}
	
	@GetMapping(value = "/service/{id}")
	public ResponseEntity<ResponseBody<ProductDto>> getService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServiceById(id)));
	}
}
