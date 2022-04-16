package com.doctor.spa.api.controller;

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api/news")
public class NewsController {

	@Autowired
	NewsService newsService;
	
	@Autowired
	ProductService serService;
	
	@Autowired
	ProductMapper serviceMapper;
	
	@Autowired
	PageTextService pageTextService;

	@GetMapping(value = "/page")
	public ResponseEntity<ResponseBody<Page<NewsDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsWithConditions(groupId, searchText, pageable)));
	}

	@GetMapping(value = "/page/post/no")
	public ResponseEntity<ResponseBody<Integer>> getPostNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsNo(groupId, searchText)));
	}
}
