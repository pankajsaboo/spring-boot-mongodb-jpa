package com.templateproject.schema.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ValidationErrorDetail implements Serializable {

	private static final long serialVersionUID = -8817982162466776416L;

	private String node;

	private String errorType;

}
