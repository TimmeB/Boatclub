package model;

public class Member {
	private String name, pNum;
	private int memberID;
	private static int nextID = 0;
	
	public Member() {								//Contructor without arguments is used by ObjectMapper
		nextID++;
	}
	
	public Member(String n, String pn) {
		nextID++;
		
		name = n;
		pNum = pn;
		memberID = nextID;
	}
	
	public String getName() {
		return name;
	}
	public String getpNum() {
		return pNum;
	}
	public int getMemberID() {
		return memberID;
	}
	
	
	
}
