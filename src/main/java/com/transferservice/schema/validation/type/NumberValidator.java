package com.transferservice.schema.validation.type;

import com.fasterxml.jackson.databind.JsonNode;
import com.templateproject.schema.validation.Parser;
import com.templateproject.schema.validation.ValidationConstants;
import com.templateproject.schema.validation.ValidationErrorDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class NumberValidator extends TypeValidator {

	public NumberValidator(Parser parser) {
		super(parser);
	}

	@Override
	public List<ValidationErrorDetail> validate(Entry<String, JsonNode> instanceNode, JsonNode schemaNode) {
		final List<ValidationErrorDetail> validationErrorDetails = new ArrayList<>();
		JsonNode minimumNode = schemaNode.findValue(ValidationConstants.MINIMUM);
		if (minimumNode != null && instanceNode.getValue().asInt() < minimumNode.asInt()) {
			validationErrorDetails.add(new ValidationErrorDetail(instanceNode.getKey(), ValidationConstants.MINIMUM));
		}
		JsonNode maximumNode = schemaNode.findValue(ValidationConstants.MAXIMUM);
		if (maximumNode != null && instanceNode.getValue().asInt() > maximumNode.asInt()) {
			validationErrorDetails.add(new ValidationErrorDetail(instanceNode.getKey(), ValidationConstants.MAXIMUM));
		}
		return validationErrorDetails;
	}

}