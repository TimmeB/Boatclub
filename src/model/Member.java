package model;

import java.util.ArrayList;
import model.Boat;

public class Member {
	private String name, pNum;
	private int memberID;
	private ArrayList<Boat> boatList = new ArrayList<>();
	
	public Member() {								//Contructor without arguments is used by ObjectMapper

	}
	
	public Member(String n, String pn, int nextID) {
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

	public int boatListSize() {				
		try {
			return boatList.size();
		}
		catch (Exception e) {
			return 0;
		}
	}
	
	public ArrayList<Boat> getBoatList() {
		return new ArrayList<>(boatList);
	}
	
	public void setName(String newName) {
		name = newName;
	}
	public void setpNum(String newpNum) {
		pNum = newpNum;
	}
	public void setBoatList(ArrayList<Boat> newList) {
		boatList = newList;
	}
	public void addBoat(Boat newBoat) {	
		boatList.add(newBoat);
	}
	
	
	
	
}
