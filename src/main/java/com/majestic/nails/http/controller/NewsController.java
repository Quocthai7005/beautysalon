package com.majestic.nails.http.controller;

import com.majestic.nails.common.response.ResponseBody;
import com.majestic.nails.mapper.ProductMapper;
import com.majestic.nails.util.Menus;
import com.majestic.nails.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.majestic.nails.dto.PostDto;
import com.majestic.nails.service.PostService;
import com.majestic.nails.service.PageTextService;
import com.majestic.nails.service.ProductService;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	@Autowired
	PostService postService;
	
	@Autowired
	ProductService serService;
	
	@Autowired
    ProductMapper serviceMapper;
	
	@Autowired
	PageTextService pageTextService;

	@GetMapping
	public String goToService(Model model) {
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("menu", Menus.menuNews);
		model.addAttribute("menuServices", serService.getAllServices());
		return Pages.POST;
	}

	@GetMapping(value = "/page")
	public ResponseEntity<ResponseBody<Page<PostDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.getPostsWithConditions(groupId, searchText, pageable)));
	}

	@GetMapping(value = "/page/post/no")
	public ResponseEntity<ResponseBody<Integer>> getPostNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, postService.getPostsNo(groupId, searchText)));
	}

	@GetMapping(value = "/post/{url}")
	public String goToPost(@PathVariable String url, Model model) {
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("childServices", postService.getChildServices(url));
		model.addAttribute("menu", Menus.menuNews);
		model.addAttribute("post", postService.getSinglePost(url));
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("latestPosts", postService.getLatestPost());
		return Pages.POST_DETAIL;
	}
}
