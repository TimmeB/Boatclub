package model;

public class Boat {
	private BoatType type;
	private int length;
		
	public enum BoatType{
		Sailboat,
		Motorsailer,
		Canoe,
		Other;
	}
	
	public Boat () {								//Contructor without arguments is used by ObjectMapper
		
	}
	
	public Boat (BoatType type, int length) {
		this.type = type;
		this.length = length;
	}
	
	public void setType(BoatType newType) {
		type = newType;
	}
	
	public void setLength(int newLength) {
		length = newLength;
	}
	
	public BoatType getType() {					
		return type;
	}
	
	public int getLength() {					
		return length;
	} 
	
}
