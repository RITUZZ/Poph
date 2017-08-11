package model;

import java.util.Date;

public class Counterparty {
	private int id;
	private String name;
	private String status;
	private Date dateRegistered;
	 
	public Counterparty(int id, String name, String string, Date dateRegistered) {
		super();
		this.id = id;
		this.name = name;
		this.status = string;
		this.dateRegistered = dateRegistered;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
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
