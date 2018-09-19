package model;

import java.util.ArrayList;

public class Registry {
	private ArrayList<Member> memberList;
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, int pNum) {
		Member member = new Member(name, pNum);
		memberList.add(member);
		System.out.println("hej");
	}
	
	public String toString() {
		return memberList.get(0).getMemberName() + " " + memberList.get(0).getMemberpNum();
	}
}
