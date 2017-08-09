package model;

import java.util.Date;

public class Counterparty {
	private int id;
	private String name;
	private char status;
	private Date dateRegistered;
	 
	public Counterparty(int id, String name, char status, Date dateRegistered) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.dateRegistered = dateRegistered;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public char getStatus() {
		return status;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	@Override
	public String toString() {
		return "Counterparty [id=" + id + ", name=" + name + ", status=" + status + ", dateRegistered=" + dateRegistered
				+ "]";
	} 
	
	
	 
	 
}
