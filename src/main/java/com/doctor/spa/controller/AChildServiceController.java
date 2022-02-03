package com.doctor.spa.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.service.ChildSerService;
import com.doctor.spa.service.ServiceGroupService;
import com.doctor.spa.util.Pages;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/admin")
public class AChildServiceController {
	
	@Autowired
	ServiceGroupService serService;
	
	@Autowired
	ChildSerService childService;

	@RequestMapping(value="/service-list", method=RequestMethod.GET)
	public String goToList() {	
		return Pages.A_SERVICE_LIST;
	}
	
	@RequestMapping(value="/service-edit/{id}", method=RequestMethod.GET)
	public String goToEdit() {	
		return Pages.A_SERVICE_EDIT;
	}
	
	@GetMapping(value="/service-list/no")
	public ResponseEntity<ResponseBody<Integer>> getChildServiceNo(@RequestParam Long groupId) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.getServiceNo(groupId)));
	}
	
	@GetMapping(value="/service-list/all")
	public ResponseEntity<ResponseBody<Page<ChildServiceDto>>> getChildServices(@RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.getChildServiceByGroupId(groupId, pageable)));
	}
	
	@DeleteMapping(value = "/service-delete/{id}")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.deleteService(id)));
	}
	
	@RequestMapping(value="/service-add", method=RequestMethod.GET)
	public String goToAddPage() {	
		return Pages.A_SERVICE_ADD;
	}
	
	/*
	 * @PostMapping(value = "/service-create") public
	 * ResponseEntity<ResponseBody<Boolean>> createService(@RequestBody
	 * ChildServiceDto dto) { return ResponseEntity.ok(new
	 * ResponseBody<>(HttpStatus.OK, childService.createService(dto))); }
	 */
	
	/*
	 * @PostMapping(value = "/service-create", consumes =
	 * MediaType.MULTIPART_FORM_DATA_VALUE) public
	 * ResponseEntity<ResponseBody<Boolean>> createService2(
	 * 
	 * @RequestParam MultipartFile image,
	 * 
	 * @RequestParam ChildServiceDto data) { return ResponseEntity.ok(new
	 * ResponseBody<>(HttpStatus.OK, childService.createService(data, image))); }
	 */
	
	@PostMapping(value = "/service-create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ResponseBody<Boolean>> createService2(
			@RequestPart MultipartFile image,
			@ModelAttribute ChildServiceDto data) throws JsonParseException, JsonMappingException, IOException {
		//ObjectMapper mapper = new ObjectMapper();
		//ChildServiceDto dto = mapper.readValue(data, ChildServiceDto.class);
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.createService(data, image)));
	}
	
	@GetMapping(value = "/service-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(childService.validateUrlNoId(url));
	}
	
	@GetMapping(value = "/service-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(childService.validateUrl(url, id));
	}
	
	@GetMapping(value = "/service/child/{id}")
	public ResponseEntity<ResponseBody<ChildServiceDto>> getChildService(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.getChildServiceById(id)));
	}
	
	@PostMapping(value = "/service-update")
	public ResponseEntity<ResponseBody<Boolean>> updateService(@RequestBody ChildServiceDto dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, childService.updateService(dto)));
	}
}
