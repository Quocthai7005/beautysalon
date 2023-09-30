package com.doctor.spa.dto;

public class SubProductDto extends BaseDto{
	
	private String name;
	
	private String parentServiceName;
	
	private Long parentServiceId;
	
	private String content;
	
	private String intro;
	
	private String imageKey;
	
	private String imageBaseUrl;

	private String url;
	
	private Boolean isShownHome;

	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Boolean getIsShownHome() {
		return isShownHome;
	}

	public void setIsShownHome(Boolean isShownHome) {
		this.isShownHome = isShownHome;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
