package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.doctor.spa.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.PostDto;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.mapper.PostMapper;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.repository.PostRepository;
import com.doctor.spa.repository.ProductRepository;
import com.doctor.spa.repository.SubProductRepository;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.service.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	SubProductRepository childServiceRepo;

	@Autowired
	ProductRepository serviceRepo;

	@Autowired
	SubProductMapper childServiceMapper;

	@Autowired
	PostMapper newMapper;

	@Autowired
	AwsS3Service imageService;

	@Override
	public Page<PostDto> getPosts(Pageable pageable) {
		return new PageImpl<>(postRepository
				.findByDeletedFalse(pageable)
				.getContent()
				.stream()
				.map(newMapper::toDto)
				.collect(Collectors.toList()));
	}

	@Override
	public Integer getPostsNo(Long id, String searchText) {
		List<Post> result = new ArrayList<Post>();
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				result = postRepository.findByDeletedFalse();
			} else if (id == 0 && !searchText.equals("")) {
				result = postRepository.findByNameContainsAndDeletedFalse(searchText);
			} else if (id != 0 && searchText.equals("")) {
				result = postRepository.findByProductIdAndDeletedFalse(id);
			} else {
				result = postRepository.findByProductIdAndNameContainsAndDeletedFalse(id, searchText);
			}
		}
		return result.size();
	}

	@Override
	public Post getSinglePost(String url) {
		return postRepository.findByUrl(url);
	}

	@Override
	@Transactional
	public List<PostDto> getLatestPost() {
			return  postRepository
					.findTop4ByDeletedFalseOrderByCreatedDateDesc().stream()
					.map(newMapper::toDto)
					.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubProductDto> getChildServices(String url) {
		return childServiceRepo
				.findTop4ByParentProductIdAndDeletedFalse(postRepository.findByUrl(url).getProduct().getId())
				.stream()
				.map(x -> {
					x.setUrl(x.getParentProduct().getUrl() + "/" + x.getUrl());
					return x;
				}).map(childServiceMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Integer getPostsNo() {
		return postRepository.countByDeletedFalse();
	}

	@Override
	public Page<PostDto> getPostsWithConditions(Long id, String searchText, Pageable pageable) {

		Page<Post> newsPosts = new PageImpl<>(Collections.emptyList());
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				newsPosts = postRepository.findByDeletedFalse(pageable);
			} else if (id == 0 && !searchText.equals("")) {
				newsPosts = postRepository.findByNameContainsAndDeletedFalse(searchText, pageable);
			} else if (id != 0 && searchText.equals("")) {
				newsPosts = postRepository.findByProductIdAndDeletedFalse(id, pageable);
			} else {
				newsPosts = postRepository.findByProductIdAndNameContainsAndDeletedFalse(id, searchText, pageable);
			}
		}
		
		return new PageImpl<>(newsPosts.
				getContent().
				stream().
				map(newMapper::toDto)
				.collect(Collectors.toList()));
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<Post> posts = postRepository.findByUrlAndDeletedFalse(url);
		Boolean isValid = posts.isEmpty();
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
		List<Post> services = postRepository.findByUrlAndIdNot(id, url);
		Boolean isValid = services.isEmpty();
		result.put("valid", isValid);
		return result;
	}

	@Override
	@Transactional
	public PostDto createPost(PostDto dto, MultipartFile image) {
		Post post = new Post();
		post.setName(dto.getName());
		post.setUrl(dto.getUrl());
		post.setImage(imageService.uploadFile(image));
		post.setContent(dto.getContent());
		post.setIntro(dto.getIntro());
		post.setProduct(serviceRepo.findById(dto.getParentServiceId()));
		post = postRepository.save(post);
		dto.setId(post.getId());
		return dto;
	}

	@Override
	public Boolean deletePost(Long id) {
		postRepository.deleteById(id);
		return true;
	}

	@Override
	public PostDto getPost(long id) {
		return newMapper.toDto(postRepository.findById(id));
	}

	@Override
	@Transactional
	public Boolean updatePost(PostDto dto, MultipartFile image) {
		Post post = postRepository.findById(dto.getId());
		post.setName(dto.getName());
		post.setImage(imageService.uploadFile(image));
		post.setContent(dto.getContent());
		post.setIntro(dto.getIntro());
		post.setProduct(serviceRepo.findById(dto.getParentServiceId()));
		postRepository.save(post);
		return true;
	}

	@Override
	@Transactional
	public List<PostDto> getAll() {
		return postRepository.findAll()
			.stream()
			.map(newMapper::toDto)
			.collect(Collectors.toList());
	}

}
