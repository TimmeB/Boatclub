package model;

import java.util.ArrayList;

public class Member {
	private String name, pNum;
	private int memberID;
	private static int nextID = 0;
	private ArrayList<Boat> boatList;
	
	public Member() {								//Contructor without arguments is used by ObjectMapper

	}
	
	public Member(String n, String pn) {
		nextID++;
		name = n;
		pNum = pn;
		memberID = nextID;	
		boatList = new ArrayList<>();
		
	}
	
	public static void setNextID(int updatedFromMemberListID) {
		nextID = updatedFromMemberListID++;
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
	public int getBoatListSize() {
		if (boatList == null) {
			return 0;
		}
		else {
			return boatList.size();
		}	
	}
	public void setName(String newName) {
		name = newName;
	}
	public void setpNum(String newpNum) {
		pNum = newpNum;
	}
	
	
	
}
