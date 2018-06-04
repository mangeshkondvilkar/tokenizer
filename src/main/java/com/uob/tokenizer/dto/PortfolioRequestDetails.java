package com.uob.tokenizer.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Mangesh K
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PortfolioRequestDetails implements Serializable {

	private static final long serialVersionUID = -3203872220837422298L;

	@XmlElement(name = "portfolioRequestType")
	private String portfolioRequestType;

	@XmlElement(name = "portfolioRequestLevel")
	private String portfolioRequestLevel;

	@XmlElement(name = "portfolioRequestNumber")
	private String portfolioRequestNumber;

	@XmlElement(name = "portfolioRequestDate")
	private String portfolioRequestDate;

	@XmlElement(name = "requestorID")
	private String requestorID;

	@XmlElement(name = "cifNumber")
	private String cifNumber;

	@XmlElement(name = "customerEntityNumber")
	private String customerEntityNumber;

	@XmlElement(name = "customerRiskProfile")
	private String customerRiskProfile;

	@XmlElement(name = "custoemrRiskProfileDate")
	private String custoemrRiskProfileDate;

	@XmlElement(name = "listPortfolioHoldinginfo")
	private ListPortfolioHoldinginfo listPortfolioHoldinginfo;

	public String getPortfolioRequestType() {
		return portfolioRequestType;
	}

	public void setPortfolioRequestType(String portfolioRequestType) {
		this.portfolioRequestType = portfolioRequestType;
	}

	public String getPortfolioRequestLevel() {
		return portfolioRequestLevel;
	}

	public void setPortfolioRequestLevel(String portfolioRequestLevel) {
		this.portfolioRequestLevel = portfolioRequestLevel;
	}

	public String getPortfolioRequestNumber() {
		return portfolioRequestNumber;
	}

	public void setPortfolioRequestNumber(String portfolioRequestNumber) {
		this.portfolioRequestNumber = portfolioRequestNumber;
	}

	public String getPortfolioRequestDate() {
		return portfolioRequestDate;
	}

	public void setPortfolioRequestDate(String portfolioRequestDate) {
		this.portfolioRequestDate = portfolioRequestDate;
	}

	public String getRequestorID() {
		return requestorID;
	}

	public void setRequestorID(String requestorID) {
		this.requestorID = requestorID;
	}

	public String getCifNumber() {
		return cifNumber;
	}

	public void setCifNumber(String cifNumber) {
		this.cifNumber = cifNumber;
	}

	public String getCustomerEntityNumber() {
		return customerEntityNumber;
	}

	public void setCustomerEntityNumber(String customerEntityNumber) {
		this.customerEntityNumber = customerEntityNumber;
	}

	public String getCustomerRiskProfile() {
		return customerRiskProfile;
	}

	public void setCustomerRiskProfile(String customerRiskProfile) {
		this.customerRiskProfile = customerRiskProfile;
	}

	public String getCustoemrRiskProfileDate() {
		return custoemrRiskProfileDate;
	}

	public void setCustoemrRiskProfileDate(String custoemrRiskProfileDate) {
		this.custoemrRiskProfileDate = custoemrRiskProfileDate;
	}

	public ListPortfolioHoldinginfo getListPortfolioHoldinginfo() {
		return listPortfolioHoldinginfo;
	}

	public void setListPortfolioHoldinginfo(ListPortfolioHoldinginfo listPortfolioHoldinginfo) {
		this.listPortfolioHoldinginfo = listPortfolioHoldinginfo;
	}

	@Override
	public String toString() {
		return "PortfolioRequestDetails [portfolioRequestType=" + portfolioRequestType + ", portfolioRequestLevel="
				+ portfolioRequestLevel + ", portfolioRequestNumber=" + portfolioRequestNumber
				+ ", portfolioRequestDate=" + portfolioRequestDate + ", requestorID=" + requestorID + ", cifNumber="
				+ cifNumber + ", customerEntityNumber=" + customerEntityNumber + ", customerRiskProfile="
				+ customerRiskProfile + ", custoemrRiskProfileDate=" + custoemrRiskProfileDate
				+ ", listPortfolioHoldinginfo=" + listPortfolioHoldinginfo + "]";
	}

}
