package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Registry {
	
	private ArrayList<Member> memberList;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, String pNum) throws JsonGenerationException, IOException {
		Member member = new Member(name, pNum);
		memberList.add(member);
		writeToMemberList();
	}
	

	public String toString() {		// denna metod kommer ersätttas av hämtmedtod från fil
		String listStr = "";
		for (int i = 0; i < memberList.size(); i++) {
			listStr += memberList.get(i).getName() + " PNR: " + memberList.get(i).getpNum() + " ID: " + memberList.get(i).getMemberID() + "\n";
		}
		//return memberList.get(0).getName() + " " + memberList.get(0).getpNum();
		return listStr;
	}
	 
	
	public void writeToMemberList() throws JsonGenerationException, IOException {
		objectMapper.writeValue(new File("Memberlist.txt"), memberList);
	}
	
	public void readFromMemberList () throws IOException {
		memberList = objectMapper.readValue(new File("Memberlist.txt"), new TypeReference<ArrayList<Member>>(){});
	}
	public boolean idExist(int inputID) {
		for (int i = 0; i < memberList.size(); i++) {
			int membersID = memberList.get(i).getMemberID();
			if (inputID == membersID)
				return true;
		}
		return false;
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
	/*
	public static void main (String[] args) throws IOException {
		Registry registry = new Registry();
		//registry.createMember("Erik, ", "007");
		//registry.createMember("Timme, ", "666");
		//registry.writeToMemberList();
		
		registry.readFromMemberList();
		System.out.println(registry.toString());

		
	} */
}
