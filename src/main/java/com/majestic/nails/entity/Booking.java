package com.majestic.nails.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity
@Table(name = "booking")
public class Booking {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "question")
	private String question;

	@Column(name = "status")
	private String status;

	@Column(name = "hour")
	private String hour;

	@Column(name = "minute")
	private String minute;

	@Column(name = "services")
	private String services;
	
	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "consult_date", nullable = true, length = 23)
	private LocalDateTime consultDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		// NEW, CONFIRMED, DONE, CANCELLED
		this.status = status;
	}

	public LocalDateTime getConsultDate() {
		return consultDate;
	}

	public void setConsultDate(LocalDateTime consultDate) {
		this.consultDate = consultDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}
}
