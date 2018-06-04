package com.uob.tokenizer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a root element of DA XML request
 * 
 * @author Mangesh K
 *
 */
@XmlRootElement(name = "dataAnonymization")
@XmlAccessorType(XmlAccessType.FIELD)
public class UobTokenizerXmlRootDto implements Serializable {

	private static final long serialVersionUID = 5079853583515501674L;

	@XmlElement(name = "requestHeader")
	private RequestHeader requestHeader;

	@XmlElement(name = "requestDetails")
	private RequestDetails requestDetails;

	
	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RequestDetails getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(RequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}

	@Override
	public String toString() {
		return "DataAnonymization [requestHeader=" + requestHeader + ", requestDetails=" + requestDetails + "]";
	}

}
