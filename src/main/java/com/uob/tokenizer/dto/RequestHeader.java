package com.uob.tokenizer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Mangesh K
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class RequestHeader implements Serializable{

	private static final long serialVersionUID = -5666244087937389897L;

	@XmlElement(name = "requesterContext")
	private RequesterContext requesterContext;

	public RequesterContext getRequesterContext() {
		return requesterContext;
	}

	public void setRequesterContext(RequesterContext requesterContext) {
		this.requesterContext = requesterContext;
	}

	@Override
	public String toString() {
		return "RequestHeader [requesterContext=" + requesterContext + "]";
	}
	
}
