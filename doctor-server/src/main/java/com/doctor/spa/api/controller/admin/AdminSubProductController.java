package com.doctor.spa.api.controller.admin;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.service.SubProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller("ApiSubProductController")
@RequestMapping(value = "/api/admin")
public class AdminSubProductController {
	
	@Autowired
	ProductService serService;
	
	@Autowired
	SubProductService subProductService;
	
	@GetMapping(value="/service-list/no")
	public ResponseEntity<ResponseBody<Long>> getChildServiceNo(@RequestParam Long groupId) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.getProductNo(groupId)));
	}
	
	@GetMapping(value="/service-list/all")
	public ResponseEntity<ResponseBody<Page<SubProductDto>>> getChildServices(@RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.getSubProductByGroupId(groupId, pageable)));
	}
	
	@DeleteMapping(value = "/service-delete/{id}")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.deleteSubProduct(id)));
	}
	
	@PostMapping(value = "/service-create")
	public ResponseEntity<ResponseBody<SubProductDto>> createService(
			@RequestPart MultipartFile imgFile,
			@RequestPart SubProductDto data) throws JsonParseException, JsonMappingException, IOException {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.createSubProduct(data, imgFile)));
	}
	
	@GetMapping(value = "/service-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(subProductService.validateUrlNoId(url));
	}
	
	@GetMapping(value = "/service-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(subProductService.validateUrl(url, id));
	}
	
	@GetMapping(value = "/service/child/{id}")
	public ResponseEntity<ResponseBody<SubProductDto>> getChildService(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.getSubProductById(id)));
	}
	
	@PostMapping(value = "/service-update")
	public ResponseEntity<ResponseBody<Boolean>> updateService(@RequestBody SubProductDto dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, subProductService.updateSubProduct(dto)));
	}
}
