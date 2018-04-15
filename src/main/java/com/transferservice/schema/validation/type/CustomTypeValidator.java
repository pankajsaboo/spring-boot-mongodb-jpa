package com.transferservice.schema.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.templateproject.schema.validation.Parser;
import com.templateproject.schema.validation.ValidationErrorDetail;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class CustomTypeValidator extends TypeValidator {

	public CustomTypeValidator(Parser parser) {
		super(parser);
	}

	@Override
	public List<ValidationErrorDetail> validate(Entry<String, JsonNode> instanceNode, JsonNode schemaNode) {
		return Collections.emptyList();
	}

}
