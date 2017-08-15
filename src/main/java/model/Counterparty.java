package model;

import java.sql.Timestamp;
import java.util.Date;

public class Counterparty {
	
	private String name;
	private String status;
	private Timestamp dateRegistered;
	 
	public Counterparty(String name, String string, Timestamp dateRegistered) {
		super();
		this.name = name;
		this.status = string;
		this.dateRegistered = dateRegistered;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getDateRegistered() {
		return dateRegistered;
	}

	@Override
	public String toString() {
		return "Counterparty [name=" + name + ", status=" + status + ", dateRegistered=" + dateRegistered
				+ "]";
	} 
	
	
	 
	 
}
