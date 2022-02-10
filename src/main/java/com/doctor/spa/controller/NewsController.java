package com.doctor.spa.controller;

import java.util.List;

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
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.News;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.service.NewsService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ProductService;
import com.doctor.spa.util.ConstUtil;
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
		List<ProductDto> menuServices = serService.getAllServices();
		List<PageText> pageTexts = pageTextService.findByPage("home");
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("menu", ConstUtil.menuNews);
		model.addAttribute("menuServices", menuServices);
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
		News post = newsService.getSinglePost(url);
		List<NewsDto> latestPosts =  newsService.getLatestPost();
		List<SubProductDto> childServices = newsService.getChildServices(url);
		List<ProductDto> menuServices = serService.getAllServices();
		List<PageText> pageTexts = pageTextService.findByPage("home");
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("childServices", childServices);
		model.addAttribute("menu", ConstUtil.menuNews);
		model.addAttribute("post", post);
		model.addAttribute("menuServices", menuServices);
		model.addAttribute("latestPosts", latestPosts);
		return Pages.POST_DETAIL;
	}
}
