package com.transferservice.schema.validation.type;

import com.templateproject.schema.validation.Parser;

public final class ValidatorFactory {

	private ValidatorFactory() {
	}

	public static TypeValidator getValidator(Parser parser, String typeName) {
		TypeValidator validator;
		//damn you 1.6 with the if and else
		if ("string".equalsIgnoreCase(typeName)) {
			validator = new StringValidator(parser);
		} else if ("number".equalsIgnoreCase(typeName)) {
			validator = new NumberValidator(parser);
		} else {
			validator = new CustomTypeValidator(parser);
		}
		return validator;
	}

}
