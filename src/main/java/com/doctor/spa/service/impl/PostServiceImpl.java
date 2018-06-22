package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.entity.ChildService;
import com.doctor.spa.entity.Post;
import com.doctor.spa.mapper.ChildServiceMapper;
import com.doctor.spa.mapper.PostMapper;
import com.doctor.spa.repository.ChildServiceRepo;
import com.doctor.spa.repository.PostRepo;
import com.doctor.spa.repository.ServiceGroupRepo;
import com.doctor.spa.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepo newsRepo;
	
	@Autowired
	ChildServiceRepo childServiceRepo;
	
	@Autowired
	ServiceGroupRepo serviceRepo;
	
	@Autowired
	ChildServiceMapper childServiceMapper;
	
	@Autowired
	PostMapper newMapper;

	@Override
	public Page<PostDto> getPosts(Pageable pageable) {
		Page<Post> newsPosts = new PageImpl<>(Collections.emptyList());
		newsPosts = newsRepo.findByDeletedFalse(pageable);
		List<PostDto> newsDtos = new ArrayList<PostDto>();
		newsPosts.getContent().forEach(post -> {
			PostDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return new PageImpl<PostDto>(newsDtos);
	}

	@Override
	public Integer getPostsNo(Long id, String searchText) {
		List<Post> result = new ArrayList<Post>();
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				result = newsRepo.findByDeletedFalse();
			} else if (id == 0 && !searchText.equals("")) {
				result = newsRepo.findBySearchTextByDeletedFalse(searchText);
			} else if (id != 0 && searchText.equals("")) {
				result = newsRepo.findByServiceGroupIdByDeletedFalse(id);
			} else {
				result = newsRepo.findByServiceGroupIdBySearchTextByDeletedFalse(id, searchText);
			}
		}
		Integer postNo = result.size();
		return postNo;
	}

	@Override
	public Post getSinglePost(String url) {
		Post post = newsRepo.findByUrl(url);
		return post;
	}

	@Override
	public List<PostDto> getLatestPost(String url) {
		List<Post> posts = newsRepo.findFirst4ByUrlNotLikeOrderByCreatedDateDesc(url);
		List<PostDto> newsDtos = new ArrayList<PostDto>();
		posts.forEach(post -> {
			PostDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return newsDtos;
	}

	@Override
	public List<ChildServiceDto> getChildServices(String url) {
		Post post = newsRepo.findByUrl(url);
		List<ChildServiceDto> childServiceDtos = new ArrayList<ChildServiceDto>();
		List<ChildService> childServices = childServiceRepo.findByServiceGroupIdByDeletedFalse(post.getService().getId());
		childServices.forEach(childService -> {
			childService.setUrl(childService.getParentService().getUrl() + "/" + childService.getUrl());
			ChildServiceDto childServiceDto = childServiceMapper.toDto(childService);
			childServiceDtos.add(childServiceDto);
		});
		return childServiceDtos;
	}

	@Override
	public Integer getPostsNo() {
		// TODO Auto-generated method stub
		return newsRepo.countByDeletedFalse();
	}

	@Override
	public Page<PostDto> getPostsWithConditions(Long id, String searchText, Pageable pageable) {
		
		Page<Post> newsPosts = new PageImpl<>(Collections.emptyList());
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByDeletedFalse(pageable);
			} else if (id == 0 && !searchText.equals("")) {
				newsPosts = newsRepo.findBySearchTextByDeletedFalse(searchText, pageable);
			} else if (id != 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByServiceGroupIdByDeletedFalse(id, pageable);
			} else {
				newsPosts = newsRepo.findByServiceGroupIdBySearchTextByDeletedFalse(id, searchText, pageable);
			}
		}
		
		List<PostDto> newsDtos = new ArrayList<PostDto>();
		newsPosts.getContent().forEach(post -> {
			PostDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return new PageImpl<PostDto>(newsDtos);
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<Post> news = newsRepo.findByUrlByDeletedFalse(url);
		Boolean isValid = news.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public Map<String, Boolean> validateUrl(Long id, String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		if (id == null) {
			result.put("valid", false);
			return result;
		}
		List<Post> services = newsRepo.findByUrlByIdNotEqual(id, url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	@Transactional
	public Boolean createPost(PostDto dto) {
		if (dto == null) {
			return false;
		}
		// change to entity
		com.doctor.spa.entity.ServiceGroup service = new com.doctor.spa.entity.ServiceGroup();
		service = serviceRepo.findById(dto.getParentServiceId());
		
		Post news = new Post();
		news.setName(dto.getName());
		news.setImage(dto.getImage());
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setService(service);
		newsRepo.save(news);
		return true;
	}

	@Override
	public Boolean deletePost(Long id) {
		if (id == null) {
			return false;
		}	
		newsRepo.deleteById(id);	
		return true;
	}

	@Override
	public PostDto getPost(long id) {
		Post news = newsRepo.findById(id);
		PostDto dto = newMapper.toDto(news);
		return dto;
	}

	@Override
	@Transactional
	public Boolean updatePost(PostDto dto) {
		if (dto == null) {
			return false;
		}
		// change to entity
		com.doctor.spa.entity.ServiceGroup service = new com.doctor.spa.entity.ServiceGroup();
		service = serviceRepo.findById(dto.getParentServiceId());
		
		Post news = newsRepo.findById(dto.getId());
		news.setName(dto.getName());
		news.setImage(dto.getImage());
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setService(service);
		newsRepo.save(news);
		return true;
	}
}
