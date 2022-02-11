package com.doctor.spa.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/admin")
public class ANewsController {
	
	@Autowired
	NewsService newsService;

	@RequestMapping(value="/news-list", method=RequestMethod.GET)
	public String goToList() {	
		return Pages.A_POST_LIST;
	}
	
	@GetMapping(value="/news-list/no")
	public ResponseEntity<ResponseBody<Integer>> getChildServiceNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsNo(groupId, searchText)));
	}
	
	@GetMapping(value="/news-list/all")
	public ResponseEntity<ResponseBody<Page<NewsDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsWithConditions(groupId, searchText, pageable)));
	}
	
	@GetMapping(value="/news/{id}")
	public ResponseEntity<ResponseBody<NewsDto>> getNews(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPost(id)));
	}
	
	@GetMapping(value = "/news-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(newsService.validateUrlNoId(url));
	}
	
	@GetMapping(value = "/news-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(newsService.validateUrl(id, url));
	}
	
	@PostMapping(value = "/news-create")
	public ResponseEntity<ResponseBody<Boolean>> createPost(
			@RequestPart MultipartFile imgFile,
			@RequestPart NewsDto data) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.createPost(data, imgFile)));
	}
	
	@PostMapping(value = "/news-update")
	public ResponseEntity<ResponseBody<Boolean>> updatePost(
			@RequestPart MultipartFile imgFile,
			@RequestPart NewsDto dto) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.updatePost(dto, imgFile)));
	}
	
	@DeleteMapping(value = "/news-delete/{id}")
	public ResponseEntity<ResponseBody<Boolean>> deleteService(@PathVariable long id) {	
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.deletePost(id)));
	}
	
	@RequestMapping(value="/news-edit/{id}", method=RequestMethod.GET)
	public String goToEdit() {	
		return Pages.A_POST_EDIT;
	}
	
	@RequestMapping(value="/news-add", method=RequestMethod.GET)
	public String goToAdd() {	
		return Pages.A_POST_ADD;
	}
}
