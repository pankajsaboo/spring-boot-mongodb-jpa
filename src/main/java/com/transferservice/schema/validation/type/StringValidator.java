package com.transferservice.schema.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.templateproject.schema.validation.Parser;
import com.templateproject.schema.validation.ValidationConstants;
import com.templateproject.schema.validation.ValidationErrorDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class StringValidator extends TypeValidator {

	public StringValidator(Parser parser) {
		super(parser);
	}

	@Override
	public List<ValidationErrorDetail> validate(Entry<String, JsonNode> instanceNode, JsonNode schemaNode) {
		final List<ValidationErrorDetail> validationErrorDetails = new ArrayList<>();
		JsonNode maxLengthNode = schemaNode.findValue(ValidationConstants.MAX_LENGTH);
		if (maxLengthNode != null && instanceNode.getValue().asText().length() > maxLengthNode.asInt()) {
			validationErrorDetails.add(new ValidationErrorDetail(instanceNode.getKey(), ValidationConstants.MAX_LENGTH));
		}

		return validationErrorDetails;
	}

}