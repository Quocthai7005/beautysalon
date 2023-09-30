package com.majestic.nails.api.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.PostDto;
import com.majestic.nails.mapper.ProductMapper;
import com.majestic.nails.service.PostService;
import com.majestic.nails.service.PageTextService;
import com.majestic.nails.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("ApiPublicNewsController")
@RequestMapping(value = "/api/news")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	ProductService serService;
	
	@Autowired
    ProductMapper serviceMapper;
	
	@Autowired
	PageTextService pageTextService;

	@GetMapping(value = "/page")
	public ResponseEntity<ResponseBody<Page<PostDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.getPostsWithConditions(groupId, searchText, pageable)));
	}

	@GetMapping(value = "/page/post/no")
	public ResponseEntity<ResponseBody<Integer>> getPostNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.getPostsNo(groupId, searchText)));
	}
}
