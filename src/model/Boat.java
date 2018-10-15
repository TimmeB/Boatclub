package model;

public class Boat {
	private String type;
	private int length;
	
	public Boat () {								//Contructor without arguments is used by ObjectMapper
		
	}
	
	public Boat (String type, int length) {
		this.type = type;
		this.length = length;
	}
	
	public void setType(String newType) {
		type = newType;
	}
	
	public void setLength(int newLength) {
		length = newLength;
	}
	
	public String getType() {					
		return type;
	}
	public int getLength() {					
		return length;
	} 
	
}
