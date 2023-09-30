package com.majestic.nails.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class S3ObjectDto {

	private String key;
	private String uri;
	private long size;
	private String objectClass;
	private Date lastModified;
	private String uploader;
	private List<Pair<Long, String>> usedIn;
	private List<PostDto> usedInNews;
	private List<ProductDto> usedInProduct;

	public List<PostDto> getUsedInNews() {
		return usedInNews;
	}
	public void setUsedInNews(List<PostDto> usedInNews) {
		this.usedInNews = usedInNews;
	}
	public List<ProductDto> getUsedInProduct() {
		return usedInProduct;
	}
	public void setUsedInProduct(List<ProductDto> usedInProduct) {
		this.usedInProduct = usedInProduct;
	}
	public List<SubProductDto> getUsedInSubProduct() {
		return usedInSubProduct;
	}
	public void setUsedInSubProduct(List<SubProductDto> usedInSubProduct) {
		this.usedInSubProduct = usedInSubProduct;
	}
	private List<SubProductDto> usedInSubProduct;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getObjectClass() {
		return objectClass;
	}
	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public List<Pair<Long, String>> getUsedIn() {
		return usedIn;
	}
	public void setUsedIn(List<Pair<Long, String>> usedIn) {
		this.usedIn = usedIn;
	}
	public List<PostDto> getUsedIn2() {
		return usedInNews;
	}
	public void setUsedIn2(List<PostDto> usedIn2) {
		this.usedInNews = usedIn2;
	}
}
