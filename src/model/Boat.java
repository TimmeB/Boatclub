package model;

public class Boat {
	private Type type;
	private int length;
		
	public enum Type{
		Sailboat,
		Motorsailer,
		Canoe,
		Other;
	}
	
	public Boat () {								//Contructor without arguments is used by ObjectMapper
		
	}
	
	public Boat (Type type, int length) {
		this.type = type;
		this.length = length;
	}
	
	public void setType(Type newType) {
		type = newType;
	}
	
	public void setLength(int newLength) {
		length = newLength;
	}
	
	public Type getType() {					
		return type;
	}
	
	public int getLength() {					
		return length;
	} 
	
}
