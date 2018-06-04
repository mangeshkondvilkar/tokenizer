package com.uob.tokenizer.dto;

import java.io.Serializable;

/**
 * Represents an errorResponse to be sent in case of any exceptions while
 * processing UOB query requests.
 *
 * @author Mangesh K
 * @since March 2018
 *
 */
public class ErrorResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMessage;

	public ErrorResponseDto() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
