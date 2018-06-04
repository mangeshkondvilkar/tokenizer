package com.uob.tokenizer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a <i>ticker</i> tag in request XML grouped under parent tag
 * <i>importPosition</i>.
 * 
 * @author Mangesh K
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticker implements Serializable {

	private static final long serialVersionUID = 6236542742047380572L;

	@XmlElement(name = "type")
	private String productType;

	@XmlElement(name = "assetClass")
	private String assetClass;

	@XmlElement(name = "contractNumber")
	private String uniqueReferenceNumber;

	@XmlElement(name = "isin")
	private String productUnqiueIdentifier;

	@XmlElement(name = "fundCode")
	private String investmentCode;

	@XmlElement(name = "name")
	private String productName;

	@XmlElement(name = "securitySymbol")
	private String subProductName;

	@XmlElement(name = "counterPartyId")
	private String counterPartyID;

	@XmlElement(name = "issuerName")
	private String issuerName;

	@XmlElement(name = "exchange")
	private String exchange;

	@XmlElement(name = "interestRate")
	private String interestYeildRate;

	@XmlElement(name = "maturityDate")
	private String maturityDate;

	@XmlElement(name = "maturityInstruction")
	private String maturityInstruction;

	@XmlElement(name = "contractSize")
	private String contractSize;

	@XmlElement(name = "underlyingType")
	private String underlyingType;

	@XmlElement(name = "currency")
	private String productCurrency;

	@XmlElement(name = "aleternateCurrency")
	private String aleternateCurrency;

	@XmlElement(name = "strike")
	private String strikePrice;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAssetClass() {
		return assetClass;
	}

	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}

	public String getUniqueReferenceNumber() {
		return uniqueReferenceNumber;
	}

	public void setUniqueReferenceNumber(String uniqueReferenceNumber) {
		this.uniqueReferenceNumber = uniqueReferenceNumber;
	}

	public String getProductUnqiueIdentifier() {
		return productUnqiueIdentifier;
	}

	public void setProductUnqiueIdentifier(String productUnqiueIdentifier) {
		this.productUnqiueIdentifier = productUnqiueIdentifier;
	}

	public String getInvestmentCode() {
		return investmentCode;
	}

	public void setInvestmentCode(String investmentCode) {
		this.investmentCode = investmentCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSubProductName() {
		return subProductName;
	}

	public void setSubProductName(String subProductName) {
		this.subProductName = subProductName;
	}

	public String getCounterPartyID() {
		return counterPartyID;
	}

	public void setCounterPartyID(String counterPartyID) {
		this.counterPartyID = counterPartyID;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getInterestYeildRate() {
		return interestYeildRate;
	}

	public void setInterestYeildRate(String interestYeildRate) {
		this.interestYeildRate = interestYeildRate;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getMaturityInstruction() {
		return maturityInstruction;
	}

	public void setMaturityInstruction(String maturityInstruction) {
		this.maturityInstruction = maturityInstruction;
	}

	public String getContractSize() {
		return contractSize;
	}

	public void setContractSize(String contractSize) {
		this.contractSize = contractSize;
	}

	public String getUnderlyingType() {
		return underlyingType;
	}

	public void setUnderlyingType(String underlyingType) {
		this.underlyingType = underlyingType;
	}

	public String getProductCurrency() {
		return productCurrency;
	}

	public void setProductCurrency(String productCurrency) {
		this.productCurrency = productCurrency;
	}

	public String getAleternateCurrency() {
		return aleternateCurrency;
	}

	public void setAleternateCurrency(String aleternateCurrency) {
		this.aleternateCurrency = aleternateCurrency;
	}

	public String getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(String strikePrice) {
		this.strikePrice = strikePrice;
	}

	@Override
	public String toString() {
		return "Ticker [productType=" + productType + ", assetClass=" + assetClass + ", uniqueReferenceNumber="
				+ uniqueReferenceNumber + ", productUnqiueIdentifier=" + productUnqiueIdentifier + ", investmentCode="
				+ investmentCode + ", productName=" + productName + ", subProductName=" + subProductName
				+ ", counterPartyID=" + counterPartyID + ", issuerName=" + issuerName + ", exchange=" + exchange
				+ ", interestYeildRate=" + interestYeildRate + ", maturityDate=" + maturityDate
				+ ", maturityInstruction=" + maturityInstruction + ", contractSize=" + contractSize
				+ ", underlyingType=" + underlyingType + ", productCurrency=" + productCurrency
				+ ", aleternateCurrency=" + aleternateCurrency + ", strikePrice=" + strikePrice + "]";
	}

}
