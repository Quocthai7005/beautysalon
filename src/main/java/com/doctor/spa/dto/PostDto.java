package com.doctor.spa.dto;

public class PostDto extends BaseDto {
	
	private String name;
	private String url;
	private String intro;
	private String content;
	private String image;
	private String parentServiceName;
	private Long parentServiceId;
	private ServiceGroupDto service;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ServiceGroupDto getService() {
		return service;
	}
	public void setService(ServiceGroupDto service) {
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

}
