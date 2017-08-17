package model;

import java.math.BigDecimal;

public class RealisedProfitLoss {
	
	private String instrumentName;
	private String counterparty;
	private int boughtQuantity, soldQuantity;
	private BigDecimal boughtSum, soldSum, totalBought, totalSold;
	private int totalQuantity;
	private BigDecimal realisedProfit;
	private BigDecimal lastTradePrice;
	private BigDecimal effectiveProfit;

	public RealisedProfitLoss(String instrumentName, String counterparty, int boughtQuantity, int soldQuantity, BigDecimal boughtSum, BigDecimal soldSum, BigDecimal totalBought, BigDecimal totalSold, BigDecimal lastTradePrice, BigDecimal effectiveProfit) {
		this.instrumentName = instrumentName;
		this.counterparty = counterparty;
		this.boughtQuantity = boughtQuantity;
		this.soldQuantity = soldQuantity;
		this.boughtSum = boughtSum;
		this.soldSum = soldSum;
		this.totalBought = totalBought;
		this.totalSold = totalSold;
		this.lastTradePrice = lastTradePrice;
		this.effectiveProfit = effectiveProfit;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public int getBoughtQuantity() {
		return boughtQuantity;
	}

	public void setBoughtQuantity(int boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public BigDecimal getBoughtSum() {
		return boughtSum;
	}

	public void setBoughtSum(BigDecimal boughtSum) {
		this.boughtSum = boughtSum;
	}

	public BigDecimal getSoldSum() {
		return soldSum;
	}

	public void setSoldSum(BigDecimal soldSum) {
		this.soldSum = soldSum;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalBought() {
		return totalBought;
	}

	public void setTotalBought(BigDecimal totalBought) {
		this.totalBought = totalBought;
	}

	public BigDecimal getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(BigDecimal totalSold) {
		this.totalSold = totalSold;
	}

	public BigDecimal getRealisedProfit() {
		return realisedProfit;
	}

	public void setRealisedProfit(BigDecimal realisedProfit) {
		this.realisedProfit = realisedProfit;
	}

	public BigDecimal getLastTradePrice() {
		return lastTradePrice;
	}

	public void setLastTradePrice(BigDecimal lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}

	public BigDecimal getEffectiveProfit() {
		return effectiveProfit;
	}

	public void setEffectiveProfit(BigDecimal effectiveProfit) {
		this.effectiveProfit = effectiveProfit;
	}
	
	
	
}
