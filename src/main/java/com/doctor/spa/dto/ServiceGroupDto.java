package com.doctor.spa.dto;

import java.util.List;

public class ServiceGroupDto extends BaseDto {
	
	private String name;
	private String intro;	
	private String content;
	private String image;
	private String url;
	private Integer displayOrder;
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	private List<ChildServiceDto> childServices;
	private List<PostDto> news;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ChildServiceDto> getChildServices() {
		return childServices;
	}
	public void setChildServices(List<ChildServiceDto> childServices) {
		this.childServices = childServices;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<PostDto> getNews() {
		return news;
	}
	public void setNews(List<PostDto> news) {
		this.news = news;
	}

}
