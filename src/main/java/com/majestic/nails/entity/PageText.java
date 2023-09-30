package com.majestic.nails.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "micellaneous_text")
public class PageText extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "page")
	private String page;
	
	@Column(name="is_shown_home")
	private Boolean isShownHome;
	
	@Column(name = "section")
	private String section;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "display_order")
	private Integer displayOrder;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Boolean getIsShownHome() {
		return isShownHome;
	}
	public void setIsShownHome(Boolean isShownHome) {
		this.isShownHome = isShownHome;
	}
	@Override
	public String toString() {
		return "PageText [page=" + page + ", section=" + section + ", title=" + title + ", content=" + content + "]";
	}
}
