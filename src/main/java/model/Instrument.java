package model;

public class Instrument {
	
	private String name;
	
	public Instrument(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "Instrument [name=" + name + "]";
	}
	
	
}
