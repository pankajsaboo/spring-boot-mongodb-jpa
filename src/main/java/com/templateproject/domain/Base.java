package com.templateproject.domain;

import java.io.Serializable;
import java.time.LocalTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedBy
	private String createdBy;

	@CreatedDate
	private LocalTime createdOn;

	@LastModifiedBy
	private String modifiedBy;

	@LastModifiedDate
	private LocalTime modifiedOn;
}
