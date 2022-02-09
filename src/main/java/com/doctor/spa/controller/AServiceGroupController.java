package com.doctor.spa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/admin")
public class AServiceGroupController {
	
	@Autowired
	ProductService serService;
	
	@RequestMapping(value="/service-group-list", method=RequestMethod.GET)
	public String goMain() {	
		return Pages.A_SERVICE_GROUP_LIST;
	}
	
	@RequestMapping(value="/service-group-add", method=RequestMethod.GET)
	public String goToAddPage() {	
		return Pages.A_SERVICE_GROUP_ADD;
	}
	
	@GetMapping(value = "/service-group-edit/{id}")
	public String editService(@PathVariable long id) {	
		return Pages.A_SERVICE_GROUP_EDIT;
	}
	
	@GetMapping(value="/service-group-list/search")
	public ResponseEntity<ResponseBody<Page<ProductDto>>> getServiceGroups(Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getServices(pageable)));
	}
	
	@GetMapping(value="/service-group-list/all")
	public ResponseEntity<ResponseBody<List<ProductDto>>> getAllServiceGroups() {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getAllServices()));
	}
	
	@GetMapping(value = "/service-group-list/no")
	public ResponseEntity<ResponseBody<Integer>> getServiceGroupsNo() {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getServiceNo()));
	}
	
	@PostMapping(value = "/service-group-create")
	public ResponseEntity<ResponseBody<Boolean>> createService(
			@RequestPart MultipartFile imgFile,
			@RequestPart ProductDto data) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.createService(data, imgFile)));
	}
	
	@PostMapping(value = "/service-group-update")
	public ResponseEntity<ResponseBody<Boolean>> updateService(@RequestBody ProductDto dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.updateService(dto)));
	}
	
	@GetMapping(value = "/service-group-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(serService.validateUrl(id, url));
	}
	
	@GetMapping(value = "/service-group-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(serService.validateUrlNoId(url));
	}
	
	@DeleteMapping(value = "/service-group-delete/{id}")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.deleteService(id)));
	}
	
	@RequestMapping(value="/service/all", method=RequestMethod.GET)
	public ResponseEntity<ResponseBody<Page<ProductDto>>> getAllServices(Pageable pageable) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getServices(pageable)));
	}
	
	@RequestMapping(value="/service/no", method=RequestMethod.GET)
	public ResponseEntity<ResponseBody<Integer>> getServicesNo() {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getServiceNo()));
	}
	
	@GetMapping(value = "/service/{id}")
	public ResponseEntity<ResponseBody<ProductDto>> getService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, serService.getServiceById(id)));
	}
}
