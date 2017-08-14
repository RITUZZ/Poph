package model;

import java.math.BigDecimal;

public class EndPosition {
	
	private int dealCounterpart;
	private String instrumentName;
	private BigDecimal bought;
	private BigDecimal sold;
	private BigDecimal total;
	
	public EndPosition(int dealCounterpart, String instrumentName, BigDecimal bought, BigDecimal sold, BigDecimal total) {
		this.dealCounterpart = dealCounterpart;
		this.instrumentName = instrumentName;
		this.bought = bought;
		this.sold = sold;
		this.total = total;
	}

	public int getDealCounterpart() {
		return dealCounterpart;
	}

	public void setDealCounterpart(int dealCounterpart) {
		this.dealCounterpart = dealCounterpart;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public BigDecimal getBought() {
		return bought;
	}

	public void setBought(BigDecimal bought) {
		this.bought = bought;
	}

	public BigDecimal getSold() {
		return sold;
	}

	public void setSold(BigDecimal sold) {
		this.sold = sold;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}
