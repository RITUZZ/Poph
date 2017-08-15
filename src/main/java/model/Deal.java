	package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Deal {
	private String instrumentName;
	private int id;
	private Timestamp time;
	private int counterpartyId;
	private int instrumentId;
	private String type;
	private float amount;
	private int quantity;
	
	
	public Deal(String instrumentName, int id, Timestamp time, int counterpartyId, int instrumentId, String type, float amount, int quantity) {
		super();
		this.instrumentName = instrumentName;
		this.id = id;
		this.time = time;
		this.counterpartyId = counterpartyId;
		this.instrumentId = instrumentId;
		this.type = type;
		this.amount = amount;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public Timestamp getTime() {
		return time;
	}
	public int getCounterpartyId() {
		return counterpartyId;
	}
	public int getInstrumentId() {
		return instrumentId;
	}
	public String getType() {
		return type;
	}
	public float getAmount() {
		return amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getInstrumentName() {
		return instrumentName;
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + ", time=" + time + ", counterpartyId=" + counterpartyId + ", instrumentId="
				+ instrumentId + ", type=" + type + ", amount=" + amount + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
