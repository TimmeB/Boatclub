package model;

public class Member {
	private String name;
	private int pNum, memberID;
	
	public Member(String n, int pn) {
		name = n;
		pNum = pn;
	}
	
	public String getMemberName() {
		return name;
	}
	public int getMemberpNum() {
		return pNum;
	}
	
	
	
}
