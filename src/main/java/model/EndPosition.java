package model;

import java.math.BigDecimal;

public class EndPosition {
	
	private int dealCounterpart;
	private String instrumentName;
	private BigDecimal bought;
	private BigDecimal sold;
	private BigDecimal total;
	
	public EndPosition(int dealCounterpart, String instrumentName) {
		this.dealCounterpart = dealCounterpart;
		this.instrumentName = instrumentName;
	}

	public int getDealCounterpart() {
		return dealCounterpart;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public BigDecimal getTotal() {
		return total;
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

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
	

}
