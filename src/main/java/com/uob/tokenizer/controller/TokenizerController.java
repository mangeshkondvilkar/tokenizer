package com.uob.tokenizer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uob.tokenizer.dto.ErrorResponseDto;
import com.uob.tokenizer.dto.QueryResponseDto;
import com.uob.tokenizer.service.ITokenizerService;
import com.uob.tokenizer.utils.ResponseStatus;

/**
 * Represents a Resource class to handle running number encryption requests.
 *
 * @author Mangesh K
 * @since March 2018
 */
@RestController
@RequestMapping("/tokenizer")
@Validated
public class TokenizerController {

	public TokenizerController() {
		super();
	}

	@Autowired
	ITokenizerService tokenizerService;

	private static final Logger LOGGER = Logger.getLogger(TokenizerController.class);

	/**
	 * Handler method to process UOB Tokenizer /query request. Returns a JSON
	 * with status and mask-text if the text is found in DB, else returns an
	 * error code.
	 *
	 * @param requestToken
	 * @return QueryResponseDto containing status and mask-text
	 * @throws Exception
	 */
	@GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QueryResponseDto> handleQueryRequest(
			@Valid @NotNull(message = "Invalid text value") @NotBlank(message = "Invalid text value") @RequestParam("text") String requestToken)
			throws Exception {
		LOGGER.info("UOB Tokenizer /query request received for token => " + requestToken.trim());

		QueryResponseDto responseDto = tokenizerService.processQueryRequestText(requestToken.trim());

		if (ResponseStatus.SUCCESS == responseDto.getStatus()) {
			return new ResponseEntity<QueryResponseDto>(responseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<QueryResponseDto>(responseDto, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Handle ConstraintViolationException
	 *
	 * @param ex
	 * @return ErrorResponseDto
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponseDto> handleConstraintViolation(ConstraintViolationException ex) {
		ex.printStackTrace();

		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}

		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST.toString());
		errorResponseDto.setErrorMessage("Bad request: " + errors);

		return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Generic exceptionHandler method to handle all Exceptions.
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
		exception.printStackTrace();
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponseDto.setErrorMessage("Application error: " + exception.getMessage());
		return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
