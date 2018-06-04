package com.uob.tokenizer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Mangesh K
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RequesterContext implements Serializable {

	private static final long serialVersionUID = -6221234242923914764L;

	@XmlElement(name = "regionalCode")
	private String regionalCode;
	
	@XmlElement(name = "serviceCode")
	private String serviceCode;
	
	@XmlElement(name = "loginGroup")
	private String loginGroup;
	
	@XmlElement(name = "loginID")
	private String loginID;
	
	@XmlElement(name = "requestAt")
	private String requestAt;
	
	@XmlElement(name = "sourceSystem")
	private String sourceSystem;
	
	@XmlElement(name = "targetSystem")
	private String targetSystem;
	
	@XmlElement(name = "requestID")
	private String requestID;
	
	public String getRegionalCode() {
		return regionalCode;
	}

	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getLoginGroup() {
		return loginGroup;
	}

	public void setLoginGroup(String loginGroup) {
		this.loginGroup = loginGroup;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getRequestAt() {
		return requestAt;
	}

	public void setRequestAt(String requestAt) {
		this.requestAt = requestAt;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	@Override
	public String toString() {
		return "RequesterContext [regionalCode=" + regionalCode + ", serviceCode=" + serviceCode + ", loginGroup="
				+ loginGroup + ", loginID=" + loginID + ", requestAt=" + requestAt + ", sourceSystem=" + sourceSystem
				+ ", targetSystem=" + targetSystem + ", requestID=" + requestID + "]";
	}
}
