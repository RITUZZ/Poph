package model;

import java.math.BigDecimal;

public class AverageInstrumentPrice {
	
	private String name;
	private BigDecimal averageBuy, averageSell;
	
	public AverageInstrumentPrice(String name, BigDecimal averageBuy, BigDecimal averageSell) {
		this.name = name;
		this.averageBuy = averageBuy;
		this.averageSell = averageSell;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getAverageBuy() {
		return averageBuy;
	}

	public BigDecimal getAverageSell() {
		return averageSell;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAverageBuy(BigDecimal averageBuy) {
		this.averageBuy = averageBuy;
	}

	public void setAverageSell(BigDecimal averageSell) {
		this.averageSell = averageSell;
	}

}
