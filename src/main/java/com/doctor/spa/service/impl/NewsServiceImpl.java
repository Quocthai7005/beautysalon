package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.entity.News;
import com.doctor.spa.mapper.NewsMapper;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.repository.NewsRepo;
import com.doctor.spa.repository.ProductRepo;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsRepo newsRepo;

	@Autowired
	SubProductRepo childServiceRepo;

	@Autowired
	ProductRepo serviceRepo;

	@Autowired
	SubProductMapper childServiceMapper;

	@Autowired
	NewsMapper newMapper;

	@Autowired
	AwsS3Service imageService;

	@Override
	public Page<NewsDto> getPosts(Pageable pageable) {
		return new PageImpl<>(newsRepo
				.findByDeletedFalse(pageable)
				.getContent()
				.stream()
				.map(newMapper::toDto)
				.collect(Collectors.toList()));
	}

	@Override
	public Integer getPostsNo(Long id, String searchText) {
		List<News> result = new ArrayList<News>();
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				result = newsRepo.findByDeletedFalse();
			} else if (id == 0 && !searchText.equals("")) {
				result = newsRepo.findByNameContainsAndDeletedFalse(searchText);
			} else if (id != 0 && searchText.equals("")) {
				result = newsRepo.findByProductIdAndDeletedFalse(id);
			} else {
				result = newsRepo.findByProductIdAndNameContainsAndDeletedFalse(id, searchText);
			}
		}
		return result.size();
	}

	@Override
	public News getSinglePost(String url) {
		return newsRepo.findByUrl(url);
	}

	@Override
	@Transactional
	public List<NewsDto> getLatestPost() {
			return  newsRepo
					.findTop4ByDeletedFalseOrderByCreatedDateDesc().stream()
					.map(newMapper::toDto)
					.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubProductDto> getChildServices(String url) {
		return childServiceRepo
				.findTop4ByParentProductIdAndDeletedFalse(newsRepo.findByUrl(url).getProduct().getId())
				.stream()
				.map(x -> {
					x.setUrl(x.getParentProduct().getUrl() + "/" + x.getUrl());
					return x;
				}).map(childServiceMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Integer getPostsNo() {
		return newsRepo.countByDeletedFalse();
	}

	@Override
	public Page<NewsDto> getPostsWithConditions(Long id, String searchText, Pageable pageable) {

		Page<News> newsPosts = new PageImpl<>(Collections.emptyList());
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByDeletedFalse(pageable);
			} else if (id == 0 && !searchText.equals("")) {
				newsPosts = newsRepo.findByNameContainsAndDeletedFalse(searchText, pageable);
			} else if (id != 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByProductIdAndDeletedFalse(id, pageable);
			} else {
				newsPosts = newsRepo.findByProductIdAndNameContainsAndDeletedFalse(id, searchText, pageable);
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
		List<News> news = newsRepo.findByUrlAndDeletedFalse(url);
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
		List<News> services = newsRepo.findByUrlAndIdNot(id, url);
		Boolean isValid = services.isEmpty();
		result.put("valid", isValid);
		return result;
	}

	@Override
	@Transactional
	public NewsDto createPost(NewsDto dto, MultipartFile image) {
		News news = new News();
		news.setName(dto.getName());
		news.setUrl(dto.getUrl());
		news.setImage(imageService.uploadFile(image));
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setProduct(serviceRepo.findById(dto.getParentServiceId()));
		news = newsRepo.save(news);
		dto.setId(news.getId());
		return dto;
	}

	@Override
	public Boolean deletePost(Long id) {
		newsRepo.deleteById(id);
		return true;
	}

	@Override
	public NewsDto getPost(long id) {
		return newMapper.toDto(newsRepo.findById(id));
	}

	@Override
	@Transactional
	public Boolean updatePost(NewsDto dto, MultipartFile image) {
		News news = newsRepo.findById(dto.getId());
		news.setName(dto.getName());
		news.setImage(imageService.uploadFile(image));
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setProduct(serviceRepo.findById(dto.getParentServiceId()));
		newsRepo.save(news);
		return true;
	}

	@Override
	@Transactional
	public List<NewsDto> getAll() {
		return newsRepo.findAll()
			.stream()
			.map(newMapper::toDto)
			.collect(Collectors.toList());
	}

}
