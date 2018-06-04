package com.uob.tokenizer.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uob.tokenizer.utils.ResponseStatus;

/**
 * Represents the data to be sent in a response for <i>query</i> endpoint.
 *
 * @author Mangesh K
 * @since March 2018
 */
@JsonInclude(Include.NON_NULL)
public class QueryResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private ResponseStatus status;

	@JsonProperty(value = "mask-text")
	private String maskText;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMaskText() {
		return maskText;
	}

	public void setMaskText(String maskText) {
		this.maskText = maskText;
	}

}
