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
import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.dto.ServiceGroupDto;
import com.doctor.spa.entity.Post;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.mapper.ServiceGroupMapper;
import com.doctor.spa.service.PostService;
import com.doctor.spa.service.PageTextService;
import com.doctor.spa.service.ServiceGroupService;
import com.doctor.spa.util.ConstUtil;
import com.doctor.spa.util.Pages;

@Controller
@RequestMapping(value = "/news")
public class PostController {

	@Autowired
	PostService newsService;
	
	@Autowired
	ServiceGroupService serService;
	
	@Autowired
	ServiceGroupMapper serviceMapper;
	
	@Autowired
	PageTextService pageTextService;

	@GetMapping
	public String goToService(Model model) {
		List<ServiceGroupDto> menuServices = serService.getAllServices();
		List<PageText> pageTexts = pageTextService.findByPage("home");
		model.addAttribute("pageTexts", pageTexts);
		model.addAttribute("menu", ConstUtil.menuNews);
		model.addAttribute("menuServices", menuServices);
		return com.doctor.spa.util.Pages.POST;
	}

	@GetMapping(value = "/page")
	public ResponseEntity<ResponseBody<Page<PostDto>>> getChildServices(@RequestParam String searchText, @RequestParam Long groupId, Pageable pageable) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsWithConditions(groupId, searchText, pageable)));
	}

	@GetMapping(value = "/page/post/no")
	public ResponseEntity<ResponseBody<Integer>> getPostNo(@RequestParam Long groupId, @RequestParam String searchText) {
		return ResponseEntity.ok(new ResponseBody<>(HttpStatus.OK, newsService.getPostsNo(groupId, searchText)));
	}

	@GetMapping(value = "/post/{url}")
	public String goToPost(@PathVariable String url, Model model) {
		Post post = newsService.getSinglePost(url);
		List<PostDto> latestPosts =  newsService.getLatestPost();
		List<ChildServiceDto> childServices = newsService.getChildServices(url);
		List<ServiceGroupDto> menuServices = serService.getAllServices();
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
