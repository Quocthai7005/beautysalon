package com.doctor.spa.dto;

public class PostDto extends BaseDto {
	
	private String name;
	private String url;
	private String intro;
	private String content;
	private String imageKey;
	private String imageBaseUrl;
	private String parentServiceName;
	private Long parentServiceId;
	private ProductDto service;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ProductDto getService() {
		return service;
	}
	public void setService(ProductDto service) {
		this.service = service;
	}
	public String getParentServiceName() {
		return parentServiceName;
	}
	public void setParentServiceName(String parentServiceName) {
		this.parentServiceName = parentServiceName;
	}
	public Long getParentServiceId() {
		return parentServiceId;
	}
	public void setParentServiceId(Long parentServiceId) {
		this.parentServiceId = parentServiceId;
	}
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	public String getImageBaseUrl() {
		return imageBaseUrl;
	}
	public void setImageBaseUrl(String imageBaseUrl) {
		this.imageBaseUrl = imageBaseUrl;
	}

}
