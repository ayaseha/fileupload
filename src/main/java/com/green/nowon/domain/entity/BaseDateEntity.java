package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseDateEntity {
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	LocalDateTime updatedDate;

}
