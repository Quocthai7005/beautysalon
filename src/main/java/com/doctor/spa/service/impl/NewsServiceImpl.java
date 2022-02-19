package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.entity.News;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.mapper.NewsMapper;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.repository.NewsRepo;
import com.doctor.spa.repository.ProductRepo;
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
		Page<News> newsPosts = new PageImpl<>(Collections.emptyList());
		newsPosts = newsRepo.findByDeletedFalse(pageable);
		List<NewsDto> newsDtos = new ArrayList<NewsDto>();
		newsPosts.getContent().forEach(post -> {
			NewsDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return new PageImpl<NewsDto>(newsDtos);
	}

	@Override
	public Integer getPostsNo(Long id, String searchText) {
		List<News> result = new ArrayList<News>();
		if (id != null && searchText != null) {
			if (id == 0 && searchText.equals("")) {
				result = newsRepo.findByDeletedFalse();
			} else if (id == 0 && !searchText.equals("")) {
				result = newsRepo.findBySearchTextByDeletedFalse(searchText);
			} else if (id != 0 && searchText.equals("")) {
				result = newsRepo.findByProductIdByDeletedFalse(id);
			} else {
				result = newsRepo.findByProductIdBySearchTextByDeletedFalse(id, searchText);
			}
		}
		Integer postNo = result.size();
		return postNo;
	}

	@Override
	public News getSinglePost(String url) {
		News post = newsRepo.findByUrl(url);
		return post;
	}

	@Override
	public List<NewsDto> getLatestPost() {
		List<News> posts = newsRepo.findFirst4ByUrlNotLikeOrderByCreatedDateDesc();
		List<NewsDto> newsDtos = new ArrayList<NewsDto>();
		posts.forEach(post -> {
			NewsDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return newsDtos;
	}

	@Override
	public List<SubProductDto> getChildServices(String url) {
		News post = newsRepo.findByUrl(url);
		List<SubProductDto> childServiceDtos = new ArrayList<SubProductDto>();
		List<SubProduct> childServices = childServiceRepo
				.findFirst4BySubProductIdByDeletedFalse(post.getProduct().getId());
		childServices.forEach(childService -> {
			childService.setUrl(childService.getParentProduct().getUrl() + "/" + childService.getUrl());
			SubProductDto childServiceDto = childServiceMapper.toDto(childService);
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
	public Page<NewsDto> getPostsWithConditions(Long id, String searchText, Pageable pageable) {

		Page<News> newsPosts = new PageImpl<>(Collections.emptyList());
		if (id != null && searchText != null) {
			System.out.println(id + " " + searchText);
			if (id == 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByDeletedFalse(pageable);
			} else if (id == 0 && !searchText.equals("")) {
				newsPosts = newsRepo.findBySearchTextByDeletedFalse(searchText, pageable);
			} else if (id != 0 && searchText.equals("")) {
				newsPosts = newsRepo.findByProductIdByDeletedFalse(id, pageable);
			} else {
				newsPosts = newsRepo.findByProductIdBySearchTextByDeletedFalse(id, searchText, pageable);
			}
		}

		List<NewsDto> newsDtos = new ArrayList<NewsDto>();
		newsPosts.getContent().forEach(post -> {
			NewsDto dto = newMapper.toDto(post);
			newsDtos.add(dto);
		});
		return new PageImpl<NewsDto>(newsDtos);
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<News> news = newsRepo.findByUrlByDeletedFalse(url);
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
		List<News> services = newsRepo.findByUrlByIdNotEqual(id, url);
		Boolean isValid = services.isEmpty();
		result.put("valid", isValid);
		return result;
	}

	@Override
	@Transactional
	public Boolean createPost(NewsDto dto, MultipartFile image) {
		if (dto == null) {
			return false;
		}
		// change to entity
		com.doctor.spa.entity.Product service = new com.doctor.spa.entity.Product();
		service = serviceRepo.findById(dto.getParentServiceId());

		News news = new News();
		news.setName(dto.getName());
		news.setUrl(dto.getUrl());
		news.setImage(imageService.uploadFile(image));
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setProduct(service);
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
	public NewsDto getPost(long id) {
		News news = newsRepo.findById(id);
		NewsDto dto = newMapper.toDto(news);
		return dto;
	}

	@Override
	@Transactional
	public Boolean updatePost(NewsDto dto, MultipartFile image) {
		if (dto == null) {
			return false;
		}
		// change to entity
		com.doctor.spa.entity.Product service = new com.doctor.spa.entity.Product();
		service = serviceRepo.findById(dto.getParentServiceId());

		News news = newsRepo.findById(dto.getId());
		news.setName(dto.getName());
		news.setImage(imageService.uploadFile(image));
		news.setContent(dto.getContent());
		news.setIntro(dto.getIntro());
		news.setProduct(service);
		newsRepo.save(news);
		return true;
	}

	@Override
	@Transactional
	public List<NewsDto> getAll() {
		List<NewsDto> newsDtos = new ArrayList<NewsDto>();
		newsRepo.findAll().forEach(x -> {
			newsDtos.add(newMapper.toDto(x));
		});
		return newsDtos;
	}

}
