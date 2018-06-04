package com.uob.tokenizer.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Mangesh K
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListPortfolioHoldinginfo implements Serializable {
	private static final long serialVersionUID = 2440641366689069075L;

	@XmlElement(name = "importPosition")
	private ArrayList<PortfolioHoldinginfo> portfolioHoldinginfoList;

	public ArrayList<PortfolioHoldinginfo> getListPortfolioHoldinginfo() {
		return portfolioHoldinginfoList;
	}

	public void setListPortfolioHoldinginfo(ArrayList<PortfolioHoldinginfo> listPortfolioHoldinginfo) {
		this.portfolioHoldinginfoList = listPortfolioHoldinginfo;
	}

	@Override
	public String toString() {
		return "ListPortfolioHoldinginfo [listPortfolioHoldinginfo=" + portfolioHoldinginfoList + "]";
	}
	
}
