package com.transferservice.schema.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.templateproject.schema.validation.Parser;
import com.templateproject.schema.validation.ValidationErrorDetail;

import java.util.List;
import java.util.Map.Entry;

public abstract class TypeValidator {
	protected Parser parser;

	@SuppressWarnings("unused")
	private TypeValidator() {
	}

	public TypeValidator(Parser parser) {
		this.parser = parser;
	}

	public abstract List<ValidationErrorDetail> validate(Entry<String, JsonNode> instanceNode, JsonNode schemaNode);

}
