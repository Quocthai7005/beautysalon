package com.doctor.spa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@Column(name = "intro")
	private String intro;

	@Column(name = "content")
	private String content;

	@Column(name = "image")
	private String image;

	@Column(name = "url")
	private String url;
	
	@Column(name = "display_order")
	private Integer displayOrder;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentProduct")
	private List<SubProduct> subProducts = new ArrayList<SubProduct>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Post> posts = new ArrayList<Post>();

	@Column(name = "price")
	private double price;

	public List<Post> getNews() {
		return posts;
	}

	public void setNews(List<Post> posts) {
		this.posts = posts;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<SubProduct> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(List<SubProduct> subProducts) {
		this.subProducts = subProducts;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
