package model;

import java.math.BigDecimal;

public class EndPosition {
	
	private String counterpartName;
	private String instrumentName;
	private BigDecimal bought;
	private BigDecimal sold;
	private BigDecimal total;
	
	public EndPosition(String counterpartName, String instrumentName, BigDecimal bought, BigDecimal sold, BigDecimal total) {
		this.counterpartName = counterpartName;
		this.instrumentName = instrumentName;
		this.bought = bought;
		this.sold = sold;
		this.total = total;
	}

	public String getDealCounterpart() {
		return counterpartName;
	}

	public void setDealCounterpart(String counterpartName) {
		this.counterpartName = counterpartName;
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
