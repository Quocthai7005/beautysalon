package com.doctor.spa.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class Invoice {
	
	private String id;
	private String name;
	private String email;
	private String telNo;
	private String invoiceNo;
	private LocalDateTime date;
	private Map<Product, Double> details;
	private double total;

	public Invoice(String id, String name, String email,
			String telNo, String invoiceNo, LocalDateTime date,
			Map<Product, Double> details, double total) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.telNo = telNo;
		this.invoiceNo = invoiceNo;
		this.date = date;
		this.details = details;
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Map<Product, Double> getDetails() {
		return details;
	}
	public void setDetails(Map<Product, Double> details) {
		this.details = details;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
