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
public class RequestDetails implements Serializable {

	private static final long serialVersionUID = 8170490926624434013L;

	@XmlElement(name = "portfolioRequestDetails")
	private PortfolioRequestDetails portfolioRequestDetails;

	public PortfolioRequestDetails getPortfolioRequestDetails() {
		return portfolioRequestDetails;
	}

	public void setPortfolioRequestDetails(PortfolioRequestDetails portfolioRequestDetails) {
		this.portfolioRequestDetails = portfolioRequestDetails;
	}

	@Override
	public String toString() {
		return "RequestDetails [portfolioRequestDetails=" + portfolioRequestDetails + "]";
	}

}
