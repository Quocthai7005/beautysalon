package com.doctor.spa.entity;

import java.time.LocalDateTime;

public class Subscription {

	private String email;
	private String id;
	private LocalDateTime updated_time;
	private Boolean confirm;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(LocalDateTime updated_time) {
		this.updated_time = updated_time;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}
}
