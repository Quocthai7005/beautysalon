package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.ProductDto;
import com.majestic.nails.service.ProductService;
import com.majestic.nails.util.Pages;
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
public class AdminProductController {

	private final ProductService prodService;

	public AdminProductController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@GetMapping(value = "/service-group-edit/{id}")
	public String editService(@PathVariable long id) {	
		return Pages.A_SERVICE_GROUP_EDIT;
	}
	
	@GetMapping(value="/service-group-list/search")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Page<ProductDto>>> getServiceGroups(Pageable pageable) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.getServices(pageable)));
	}
	
	@GetMapping(value="/service-group-list/all")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<List<ProductDto>>> getAllServiceGroups() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.getAllServices()));
	}
	
	@GetMapping(value = "/service-group-list/no")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Integer>> getServiceGroupsNo() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.getServiceNo()));
	}
	
	@PostMapping(value = "/service-group-create")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> createService(
			@RequestPart MultipartFile imgFile,
			@RequestPart ProductDto data) {	
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.createService(data, imgFile)));
	}
	
	@PostMapping(value = "/service-group-update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<ProductDto>> updateService(@RequestBody ProductDto dto) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.updateService(dto)));
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
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> deleteService(@PathVariable long id) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.deleteService(id)));
	}
	
	@RequestMapping(value="/service/all", method=RequestMethod.GET)
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Page<ProductDto>>> getAllServices(Pageable pageable) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.getServices(pageable)));
	}
	
	@RequestMapping(value="/service/no", method=RequestMethod.GET)
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Integer>> getServicesNo() {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, prodService.getServiceNo()));
	}
	
	@GetMapping(value = "/service/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<ProductDto>> getService(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, prodService.getServiceById(id)));
	}
}
