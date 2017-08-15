package model;

import java.math.BigDecimal;

public class AverageInstrumentPrice {
	
	private int id;
	private BigDecimal averageBuy, averageSell;
	
	public AverageInstrumentPrice(int id, BigDecimal averageBuy, BigDecimal averageSell) {
		this.id = id;
		this.averageBuy = averageBuy;
		this.averageSell = averageSell;
	}

	public int getId() {
		return id;
	}

	public BigDecimal getAverageBuy() {
		return averageBuy;
	}

	public BigDecimal getAverageSell() {
		return averageSell;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAverageBuy(BigDecimal averageBuy) {
		this.averageBuy = averageBuy;
	}

	public void setAverageSell(BigDecimal averageSell) {
		this.averageSell = averageSell;
	}

}
