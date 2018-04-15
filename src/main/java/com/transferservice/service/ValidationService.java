package com.transferservice.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.templateproject.exception.SchemaValidationException;
import com.templateproject.schema.validation.SchemaParser;
import com.templateproject.schema.validation.SchemaValidator;
import com.templateproject.schema.validation.ValidationErrorDetail;

/**
 * 
 * Validation service is for doing validations.
 *
 */
@Service
public class ValidationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);
	
	private SchemaValidator schemaValidator;
	private Map<Class<?>, SchemaParser> schemaParserMap = new HashMap<>();
	
//	@Autowired
//	private AccountRepository accountRepository;
	
	public ValidationService() throws IOException {
//		schemaParserMap.put(AccountDto.class, new SchemaParser(Resources.toString(Resources.getResource("schema/CreateAccount.json"), Charset.defaultCharset())));
//		schemaParserMap.put(TransferDto.class, new SchemaParser(Resources.toString(Resources.getResource("schema/TransferAmount.json"), Charset.defaultCharset())));
		schemaValidator = new SchemaValidator();
	}

	/**
	 * Validate create account details.
	 * @param accountDto
	 */
//	public void validateCreateAccount(final AccountDto accountDto) {
//		validateSchema(schemaParserMap.get(accountDto.getClass()), accountDto);
//		validateUniqueAccount(accountDto);
//	}
	
	/**
	 * This method checks if account already exists or not.
	 * @param accountDto
	 */
//	private void validateUniqueAccount(final AccountDto accountDto) {
//		if(null != accountRepository.findOne(accountDto.getAccountName())) {
//			throw new ValidationException("accountName", "Account already exists with same details", ErrorCodeConstants.BV_ACCOUNT_AlREADY_EXISTS);
//		}
//	}

	/**
	 * 
	 * @param schemaParser
	 * @param dto
	 * @exception SchemaValidationException On invalid input.
	 */
	private void validateSchema(SchemaParser schemaParser, Object dto) {
		LOGGER.debug("validateSchema called with DTO: " + dto);
		final List<ValidationErrorDetail> validationErrorDetails = schemaValidator.getErrorDetails(schemaParser, dto);
		if (!validationErrorDetails.isEmpty()) {
			LOGGER.error("The provided DTO did not match the schema, and returned errors: " + validationErrorDetails);
			throw new SchemaValidationException(validationErrorDetails);
		}
	}

	/**
	 * Validate 
	 * @param TransferDTO: Data transfer object used to get details of transfer.  
	 */
//	public void validateTransferAmount(TransferDto transferDto) {
//		validateSchema(schemaParserMap.get(transferDto.getClass()), transferDto);
//	}

}
