	package model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Deal {
	private String instrumentName;
	private Timestamp time;
	private String type;
	private BigDecimal amount;
	private int quantity;
	private String coutnerpartyName;
	
	
	public Deal(String instrumentName, String coutnerpartyName, Timestamp time, String type, BigDecimal amount, int quantity) {
		super();
		this.instrumentName = instrumentName;
		this.coutnerpartyName = coutnerpartyName;
		this.time = time;
		this.type = type;
		this.amount = amount;
		this.quantity = quantity;
	}
	
	public Timestamp getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public String getCoutnerpartyName() {
		return coutnerpartyName;
	}

	@Override
	public String toString() {
		return "Deal [time=" + time + ", type=" + type + ", amount=" + amount + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
