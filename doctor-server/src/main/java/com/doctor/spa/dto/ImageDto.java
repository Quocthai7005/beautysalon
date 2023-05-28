package com.doctor.spa.dto;

public class ImageDto {
	
	private Long id;
	private String base64Image;
	private Boolean isShownHome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Boolean getIsShownHome() {
		return isShownHome;
	}

	public void setIsShownHome(Boolean isShownHome) {
		this.isShownHome = isShownHome;
	}

}
