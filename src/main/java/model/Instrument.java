package model;

public class Instrument {
	private int id;
	private String name;
	
	public Instrument(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Instrument [id=" + id + ", name=" + name + "]";
	}
	
	
}
