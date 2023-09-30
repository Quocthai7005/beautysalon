package com.majestic.nails.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -3263201935418509795L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "created_date", nullable = false, length = 23)
	protected LocalDateTime createdDate;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "updated_date", nullable = true, length = 23)
	protected LocalDateTime updatedDate;

	@Column(name = "is_deleted")
	protected boolean deleted = false;

	/**
	 * Sets createdDate before insert
	 */
	@PrePersist
	public void setCreationDate() {
		this.createdDate = LocalDateTime.now();
		this.updatedDate = this.createdDate;
	}

	/**
	 * Sets updatedDate before update
	 */
	@PreUpdate
	public void setChangeDate() {
		this.updatedDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
