package com.uob.tokenizer.utils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum to define the status of Response
 *
 * @author Mangesh K
 * @since March 2018
 */
public enum ResponseStatus {

	SUCCESS("success"), NOT_FOUND("not-found");

	private final String displayText;

	ResponseStatus(String displayText) {
		this.displayText = displayText;
	}

	@JsonValue
	public String getDisplayText() {
		return displayText;
	}

}
