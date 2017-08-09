	package model;

import java.util.Date;

public class Deal {
	private int id;
	private Date time;
	private int counterpartyId;
	private int instrumentId;
	private char type;
	private float amount;
	private int quantity;
	
	
	public Deal(int id, Date time, int counterpartyId, int instrumentId, char type, float amount, int quantity) {
		super();
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
	public Date getTime() {
		return time;
	}
	public int getCounterpartyId() {
		return counterpartyId;
	}
	public int getInstrumentId() {
		return instrumentId;
	}
	public char getType() {
		return type;
	}
	public float getAmount() {
		return amount;
	}
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Deal [id=" + id + ", time=" + time + ", counterpartyId=" + counterpartyId + ", instrumentId="
				+ instrumentId + ", type=" + type + ", amount=" + amount + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
