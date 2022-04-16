package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping(value = "/api/admin")
public class ANewsController {
	
	@Autowired
	NewsService newsService;
	
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
	public ResponseEntity<ResponseBody<NewsDto>> createPost(
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
}
