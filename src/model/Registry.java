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
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, String pNum) throws JsonGenerationException, IOException {
		Member member = new Member(name, pNum);
		memberList.add(member);
		writeToMemberList();
	}
	

	public String toString() {		// denna metod kommer ersätttas av hämtmedtod från fil
		return memberList.get(0).getName() + " " + memberList.get(0).getpNum();

	}
	 
	
	public void writeToMemberList() throws JsonGenerationException, IOException {
		objectMapper.writeValue(new File("Memberlist.txt"), memberList);
	}
	
	public void readFromMemberList () throws IOException {
		memberList = objectMapper.readValue(new File("Memberlist.txt"), new TypeReference<ArrayList<Member>>(){});
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
