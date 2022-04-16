package com.doctor.spa.controller;

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

import com.doctor.spa.common.response.ResponseBody;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.Menus;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	@Autowired
	NewsService newsService;
	
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
		return com.doctor.spa.util.Pages.POST;
	}

	@GetMapping(value = "/page")
	public ResponseEntity<ResponseBody<Page<NewsDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsWithConditions(groupId, searchText, pageable)));
	}

	@GetMapping(value = "/page/post/no")
	public ResponseEntity<ResponseBody<Integer>> getPostNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsNo(groupId, searchText)));
	}

	@GetMapping(value = "/post/{url}")
	public String goToPost(@PathVariable String url, Model model) {
		model.addAttribute("pageTexts", pageTextService.findByPage("home"));
		model.addAttribute("childServices", newsService.getChildServices(url));
		model.addAttribute("menu", Menus.menuNews);
		model.addAttribute("post", newsService.getSinglePost(url));
		model.addAttribute("menuServices", serService.getAllServices());
		model.addAttribute("latestPosts", newsService.getLatestPost());
		return Pages.POST_DETAIL;
	}
}
