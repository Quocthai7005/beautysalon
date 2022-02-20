package com.doctor.spa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription {

	private static final long serialVersionUID = 1L;

	private String email;
	
	@Column(name = "firstName")
	private String fistName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "updated_time")
	private LocalDateTime updated_time;
	
	@Column(name = "confirm")
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
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
