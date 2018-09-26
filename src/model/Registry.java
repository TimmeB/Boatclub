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
	
	public void createMember(String name, int pNum) {
		Member member = new Member(name, pNum);
		memberList.add(member);
		System.out.println("hej");
	}
	
	public String toString() {		// denna metod kommer ersätttas av hämtmedtod från fil
		return memberList.get(0).getMemberName() + " " + memberList.get(0).getMemberpNum();
	}
	 
	
	public void writeToMemberList(ArrayList listToBeWrote) throws JsonMappingException {
		//objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.updateValue("C:\\Users\\erikm\\Desktop\\Uppgifter\\OOAD UML 1DV607\\Boatclub", memberList);
	}
	
	public void readFromMemberList() {
		
	}
}
