package com.templateproject.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@Document(collection = "container")
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Container extends Base{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	private long id;
	
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	@JsonProperty(value = "name")
	private String containerName;
	
	@Size(max = 200)
	@JsonProperty(value = "description")
	private String description;
	
}
