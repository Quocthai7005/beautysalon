package com.doctor.spa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subproduct")
public class SubProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="parent_product_id", nullable=false)
	private Product parentProduct;

	@Column(name = "name")
	private String name;

	@Column(name = "intro")
	private String intro;

	@Column(name = "content")
	private String content;

	@Column(name = "image")
	private String image;

	@Column(name = "is_shown_home")
	private boolean isShownHome;

	@Column(name = "url")
	private String url;

	public Product getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
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

	public String isImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public boolean isShownHome() {
		return isShownHome;
	}

	public void setShownHome(boolean isShownHome) {
		this.isShownHome = isShownHome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
