package com.majestic.nails.api.controller.admin;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.dto.PostDto;
import com.majestic.nails.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller("ApiNewsController")
@RequestMapping(value = "/api/admin")
public class AdminPostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping(value="/news-list/no")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Integer>> getChildServiceNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, postService.getPostsNo(groupId, searchText)));
	}
	
	@GetMapping(value="/news-list/all")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Page<PostDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, postService.getPostsWithConditions(groupId, searchText, pageable)));
	}
	
	@GetMapping(value="/news/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<PostDto>> getNews(@PathVariable long id) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, postService.getPost(id)));
	}
	
	@GetMapping(value = "/news-validate/url/noid")
	public ResponseEntity<Map<String, Boolean>> checkUrlNoId(@RequestParam String url) {
		return ResponseEntity.ok(postService.validateUrlNoId(url));
	}
	
	@GetMapping(value = "/news-validate/url")
	public ResponseEntity<Map<String, Boolean>> checkUrl(@RequestParam String url, @RequestParam Long id) {
		return ResponseEntity.ok(postService.validateUrl(id, url));
	}
	
	@PostMapping(value = "/news-create")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<PostDto>> createPost(
			@RequestPart MultipartFile imgFile,
			@RequestPart PostDto data) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, postService.createPost(data, imgFile)));
	}
	
	@PostMapping(value = "/news-update")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> updatePost(
			@RequestPart MultipartFile imgFile,
			@RequestPart PostDto dto) {
		return ResponseEntity.ok(new com.majestic.nails.common.response.ResponseBody<>(HttpStatus.OK, postService.updatePost(dto, imgFile)));
	}
	
	@DeleteMapping(value = "/news-delete/{id}")
	public ResponseEntity<com.majestic.nails.common.response.ResponseBody<Boolean>> deleteService(@PathVariable long id) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.deletePost(id)));
	}
}
