package com.uob.tokenizer.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a <i>PortfolioHoldinginfo</i> tag in request XML.
 * 
 * @author Mangesh K
 * @since March 2018
 */
@XmlRootElement(name = "importPosition")
@XmlAccessorType(XmlAccessType.FIELD)
public class PortfolioHoldinginfo implements Serializable {

	private static final long serialVersionUID = 5669058660424640287L;

	@XmlElement(name = "ticker")
	private Ticker ticker;

	@XmlElement(name = "sourceData")
	private String sourceData;

	@XmlElement(name = "bookCostInProductCurrency")
	private String averageCostPriceProdCcy;

	@XmlElement(name = "bookCostInBaseCurrency")
	private String averageCostPriceBaseCcy;

	@XmlElement(name = "units")
	private String currentQuantityNominalAmount;

	@XmlElement(name = "initialQuantity")
	private String initialQuantity;

	@XmlElement(name = "principleNetSettlementAmountInBaseCurrency")
	private String principalNetSettlementAmountBaseCcy;

	@XmlElement(name = "outstandingPrincipalAvailableBalanceInLCYE")
	private String outstandingPrincipalAvailableBalanceLCYE;

	@XmlElement(name = "outstandingPrincipalEarmarkedAmountInBaseCurrency")
	private String outstandingPrincipalEarmarkedAmountBaseCcy;

	@XmlElement(name = "floatAmountInBaseCurrency")
	private String floatAmountBaseCcy;

	@XmlElement(name = "odLimitInBaseCurrency")
	private String odLimitBaseCcy;

	@XmlElement(name = "ledgerBalanceInBaseCurrency")
	private String ledgerBalanceBaseCcy;

	@XmlElement(name = "tickerPriceInTickerCurrency")
	private String marketPriceProdCcy;

	@XmlElement(name = "tickerPriceInBaseCurrency")
	private String marketPriceBaseCcy;

	@XmlElement(name = "tickerPriceDate")
	private String marketPriceEffectiveDate;

	@XmlElement(name = "accruedDividendReinvestUnits")
	private String accDividendReinvestUnits;

	@XmlElement(name = "accruedInterestInBaseCurrency")
	private String accCouponDividendAmountBaseCcy;

	@XmlElement(name = "fxFromTickerCurrencyToBase")
	private String marketExchangeRate;

	@XmlElement(name = "positionValueInTickerCurrency")
	private String marketValueProdCcy;

	@XmlElement(name = "positionValueInBaseCurrency")
	private String marketValueBaseCcy;

	@XmlElement(name = "pnlAmountInBaseCurrency")
	private String pnLAmountBaseCcy;

	@XmlElement(name = "pnlAmountInLCECurrency")
	private String pnLAmountLCECcy;

	@XmlElement(name = "pnlPercentageInBaseCurrency")
	private String pnLPercentageBaseCcy;

	@XmlElement(name = "pnlPercentageInLCECurrency")
	private String pnLPercentageLCECcy;

	@XmlElement(name = "amountAlternativeCurrency")
	private String amountAlternativeCcy;

	@XmlElement(name = "totalReturnsInBaseCurrency")
	private String totalReturnsBaseCcy;

	@XmlElement(name = "valueDate")
	private String valueDate;

	@XmlElement(name = "fixingDate")
	private String fixingDate;

	@XmlElement(name = "tenor")
	private String tenor;

	public Ticker getTicker() {
		return ticker;
	}

	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getAverageCostPriceProdCcy() {
		return averageCostPriceProdCcy;
	}

	public void setAverageCostPriceProdCcy(String averageCostPriceProdCcy) {
		this.averageCostPriceProdCcy = averageCostPriceProdCcy;
	}

	public String getAverageCostPriceBaseCcy() {
		return averageCostPriceBaseCcy;
	}

	public void setAverageCostPriceBaseCcy(String averageCostPriceBaseCcy) {
		this.averageCostPriceBaseCcy = averageCostPriceBaseCcy;
	}

	public String getCurrentQuantityNominalAmount() {
		return currentQuantityNominalAmount;
	}

	public void setCurrentQuantityNominalAmount(String currentQuantityNominalAmount) {
		this.currentQuantityNominalAmount = currentQuantityNominalAmount;
	}

	public String getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(String initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public String getPrincipalNetSettlementAmountBaseCcy() {
		return principalNetSettlementAmountBaseCcy;
	}

	public void setPrincipalNetSettlementAmountBaseCcy(String principalNetSettlementAmountBaseCcy) {
		this.principalNetSettlementAmountBaseCcy = principalNetSettlementAmountBaseCcy;
	}

	public String getOutstandingPrincipalAvailableBalanceLCYE() {
		return outstandingPrincipalAvailableBalanceLCYE;
	}

	public void setOutstandingPrincipalAvailableBalanceLCYE(String outstandingPrincipalAvailableBalanceLCYE) {
		this.outstandingPrincipalAvailableBalanceLCYE = outstandingPrincipalAvailableBalanceLCYE;
	}

	public String getOutstandingPrincipalEarmarkedAmountBaseCcy() {
		return outstandingPrincipalEarmarkedAmountBaseCcy;
	}

	public void setOutstandingPrincipalEarmarkedAmountBaseCcy(String outstandingPrincipalEarmarkedAmountBaseCcy) {
		this.outstandingPrincipalEarmarkedAmountBaseCcy = outstandingPrincipalEarmarkedAmountBaseCcy;
	}

	public String getFloatAmountBaseCcy() {
		return floatAmountBaseCcy;
	}

	public void setFloatAmountBaseCcy(String floatAmountBaseCcy) {
		this.floatAmountBaseCcy = floatAmountBaseCcy;
	}

	public String getOdLimitBaseCcy() {
		return odLimitBaseCcy;
	}

	public void setOdLimitBaseCcy(String odLimitBaseCcy) {
		this.odLimitBaseCcy = odLimitBaseCcy;
	}

	public String getLedgerBalanceBaseCcy() {
		return ledgerBalanceBaseCcy;
	}

	public void setLedgerBalanceBaseCcy(String ledgerBalanceBaseCcy) {
		this.ledgerBalanceBaseCcy = ledgerBalanceBaseCcy;
	}

	public String getMarketPriceProdCcy() {
		return marketPriceProdCcy;
	}

	public void setMarketPriceProdCcy(String marketPriceProdCcy) {
		this.marketPriceProdCcy = marketPriceProdCcy;
	}

	public String getMarketPriceBaseCcy() {
		return marketPriceBaseCcy;
	}

	public void setMarketPriceBaseCcy(String marketPriceBaseCcy) {
		this.marketPriceBaseCcy = marketPriceBaseCcy;
	}

	public String getMarketPriceEffectiveDate() {
		return marketPriceEffectiveDate;
	}

	public void setMarketPriceEffectiveDate(String marketPriceEffectiveDate) {
		this.marketPriceEffectiveDate = marketPriceEffectiveDate;
	}

	public String getAccDividendReinvestUnits() {
		return accDividendReinvestUnits;
	}

	public void setAccDividendReinvestUnits(String accDividendReinvestUnits) {
		this.accDividendReinvestUnits = accDividendReinvestUnits;
	}

	public String getAccCouponDividendAmountBaseCcy() {
		return accCouponDividendAmountBaseCcy;
	}

	public void setAccCouponDividendAmountBaseCcy(String accCouponDividendAmountBaseCcy) {
		this.accCouponDividendAmountBaseCcy = accCouponDividendAmountBaseCcy;
	}

	public String getMarketExchangeRate() {
		return marketExchangeRate;
	}

	public void setMarketExchangeRate(String marketExchangeRate) {
		this.marketExchangeRate = marketExchangeRate;
	}

	public String getMarketValueProdCcy() {
		return marketValueProdCcy;
	}

	public void setMarketValueProdCcy(String marketValueProdCcy) {
		this.marketValueProdCcy = marketValueProdCcy;
	}

	public String getMarketValueBaseCcy() {
		return marketValueBaseCcy;
	}

	public void setMarketValueBaseCcy(String marketValueBaseCcy) {
		this.marketValueBaseCcy = marketValueBaseCcy;
	}

	public String getPnLAmountBaseCcy() {
		return pnLAmountBaseCcy;
	}

	public void setPnLAmountBaseCcy(String pnLAmountBaseCcy) {
		this.pnLAmountBaseCcy = pnLAmountBaseCcy;
	}

	public String getPnLAmountLCECcy() {
		return pnLAmountLCECcy;
	}

	public void setPnLAmountLCECcy(String pnLAmountLCECcy) {
		this.pnLAmountLCECcy = pnLAmountLCECcy;
	}

	public String getPnLPercentageBaseCcy() {
		return pnLPercentageBaseCcy;
	}

	public void setPnLPercentageBaseCcy(String pnLPercentageBaseCcy) {
		this.pnLPercentageBaseCcy = pnLPercentageBaseCcy;
	}

	public String getPnLPercentageLCECcy() {
		return pnLPercentageLCECcy;
	}

	public void setPnLPercentageLCECcy(String pnLPercentageLCECcy) {
		this.pnLPercentageLCECcy = pnLPercentageLCECcy;
	}

	public String getAmountAlternativeCcy() {
		return amountAlternativeCcy;
	}

	public void setAmountAlternativeCcy(String amountAlternativeCcy) {
		this.amountAlternativeCcy = amountAlternativeCcy;
	}

	public String getTotalReturnsBaseCcy() {
		return totalReturnsBaseCcy;
	}

	public void setTotalReturnsBaseCcy(String totalReturnsBaseCcy) {
		this.totalReturnsBaseCcy = totalReturnsBaseCcy;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getFixingDate() {
		return fixingDate;
	}

	public void setFixingDate(String fixingDate) {
		this.fixingDate = fixingDate;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	@Override
	public String toString() {
		return "PortfolioHoldinginfo [ticker=" + ticker + ", sourceData=" + sourceData + ", averageCostPriceProdCcy="
				+ averageCostPriceProdCcy + ", averageCostPriceBaseCcy=" + averageCostPriceBaseCcy
				+ ", currentQuantityNominalAmount=" + currentQuantityNominalAmount + ", initialQuantity="
				+ initialQuantity + ", principalNetSettlementAmountBaseCcy=" + principalNetSettlementAmountBaseCcy
				+ ", outstandingPrincipalAvailableBalanceLCYE=" + outstandingPrincipalAvailableBalanceLCYE
				+ ", outstandingPrincipalEarmarkedAmountBaseCcy=" + outstandingPrincipalEarmarkedAmountBaseCcy
				+ ", floatAmountBaseCcy=" + floatAmountBaseCcy + ", odLimitBaseCcy=" + odLimitBaseCcy
				+ ", ledgerBalanceBaseCcy=" + ledgerBalanceBaseCcy + ", marketPriceProdCcy=" + marketPriceProdCcy
				+ ", marketPriceBaseCcy=" + marketPriceBaseCcy + ", marketPriceEffectiveDate="
				+ marketPriceEffectiveDate + ", accDividendReinvestUnits=" + accDividendReinvestUnits
				+ ", accCouponDividendAmountBaseCcy=" + accCouponDividendAmountBaseCcy + ", marketExchangeRate="
				+ marketExchangeRate + ", marketValueProdCcy=" + marketValueProdCcy + ", marketValueBaseCcy="
				+ marketValueBaseCcy + ", pnLAmountBaseCcy=" + pnLAmountBaseCcy + ", pnLAmountLCECcy=" + pnLAmountLCECcy
				+ ", pnLPercentageBaseCcy=" + pnLPercentageBaseCcy + ", pnLPercentageLCECcy=" + pnLPercentageLCECcy
				+ ", amountAlternativeCcy=" + amountAlternativeCcy + ", totalReturnsBaseCcy=" + totalReturnsBaseCcy
				+ ", valueDate=" + valueDate + ", fixingDate=" + fixingDate + ", tenor=" + tenor + "]";
	}

}
