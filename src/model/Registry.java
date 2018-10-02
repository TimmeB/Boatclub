package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Registry {
	
	private ArrayList<Member> memberList;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Member member;
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, String pNum) throws JsonGenerationException, IOException {
		Member member = new Member(name, pNum);
		memberList.add(member);
		writeToMemberList();
	}
	

	public String toCompactListString() {		// denna metod kommer ers�tttas av h�mtmedtod fr�n fil
		String listStr = "";
		for (int i = 0; i < memberList.size(); i++) {
			listStr += "Name: " + memberList.get(i).getName() + "\t\t\tID: " + memberList.get(i).getMemberID() + "\t\t\tNumber of Boats: " 
					+ memberList.get(i).getBoatListSize() + "\n" + "-------------------------------------------------------------------------------\n";
		}
		return listStr;
	}
	public String toVerboseListString() {
		String listStr = "";
		for (int i = 0; i < memberList.size(); i++) {
			listStr += "Name: " + memberList.get(i).getName() + "\t\t\tID: " + memberList.get(i).getMemberID() + "\t\t\tPNR: " + memberList.get(i).getpNum()
					+ "\n\nBoats: \n"
					+ "" //use boatList.toString for print here
					+ "\n" + "-------------------------------------------------------------------------------\n";
			/*
			listStr += memberList.get(i).getName() + " PNR: " + memberList.get(i).getpNum() + " ID: " + memberList.get(i).getMemberID() + "\n"
					+ "\nBoats:\n" + memberList.get(i).boatToString(); */

		}
		return listStr;
	}
	 
	
	public void writeToMemberList() throws JsonGenerationException, IOException {
		objectMapper.writeValue(new File("Memberlist.txt"), memberList);
	}
	
	public void readFromMemberList () throws IOException {
		memberList = objectMapper.readValue(new File("Memberlist.txt"), new TypeReference<ArrayList<Member>>(){});
		if (!memberList.isEmpty())
			Member.setNextID(memberList.get(memberList.size()-1).getMemberID());
	}

	public boolean idExist(int inputID) {
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID)
				return true;
		}
		return false;
	}
	
	public void deleteMember(int inputID) {
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID) {
				memberList.remove(i);
				break;
			}
		}
		try {
			writeToMemberList();
		}
		catch (Exception e) {
			
		}
		
	}
	
	public void editName(String newName, int inputID) {
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID) {
				Member edited = memberList.get(i);
				edited.setName(newName);
				memberList.set(i, edited);
				break;
			}
		}
		
		try {
			writeToMemberList();
		}
		catch (Exception e) {
			
		}
	}
	public void editpNum(String newpNum, int inputID) {
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID) {
				Member edited = memberList.get(i);
				edited.setpNum(newpNum);
				memberList.set(i, edited);
				break;
			}
		}
		
		try {
			writeToMemberList();
		}
		catch (Exception e) {
			
		}
	}

	public Member findMemberByID(int inputID) {						//Reuse for editpNum & editName & maybe idExist
		for (int i= 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID) {
				Member m = memberList.get(i);
				return m;
			}
		}
		return null;
	}

	
	// Register boat chapter
	public void addBoat(String type, int size, int inputID) {
		Boat newBoat = new Boat(type, size);
		
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID) {
				Member m = memberList.get(i);
				m.addBoat(newBoat);
				memberList.set(i, m);
			}
		}
		try {
		writeToMemberList();
		}
		catch (Exception e) {

		}
	}
	
}
