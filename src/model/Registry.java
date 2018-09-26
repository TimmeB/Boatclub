package model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Registry {
	
	private ArrayList<Member> memberList;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, String pNum) {
		Member member = new Member(name, pNum);
		memberList.add(member);
	}
	
<<<<<<< HEAD
	public String toString() {
		String list = "";
		for (int i = 0; i < memberList.size(); i++) {
			list += memberList.get(i).getMemberName() + " " + memberList.get(i).getMemberpNum() + " ID: " + memberList.get(i).getMemberID() + "\n";
		}
		//return memberList.get(0).getMemberName() + " " + memberList.get(0).getMemberpNum() + " ID: " + memberList.get(0).getMemberID();
		return list;
=======
	public String toString() {		// denna metod kommer ersätttas av hämtmedtod från fil
		return memberList.get(0).getMemberName() + " " + memberList.get(0).getMemberpNum();
>>>>>>> erik
	}
	 
	
	public void writeToMemberList(ArrayList listToBeWrote) throws JsonMappingException {
		//objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.updateValue("C:\\Users\\erikm\\Desktop\\Uppgifter\\OOAD UML 1DV607\\Boatclub", memberList);
	}
	
	public void readFromMemberList() {
		
	}
}
