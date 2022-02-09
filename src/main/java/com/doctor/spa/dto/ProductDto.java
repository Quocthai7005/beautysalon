package com.doctor.spa.dto;

import java.util.List;

public class ProductDto extends BaseDto {
	
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
	private List<SubProductDto> childServices;
	private List<NewsDto> news;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<SubProductDto> getChildServices() {
		return childServices;
	}
	public void setChildServices(List<SubProductDto> childServices) {
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
	public List<NewsDto> getNews() {
		return news;
	}
	public void setNews(List<NewsDto> news) {
		this.news = news;
	}

}
