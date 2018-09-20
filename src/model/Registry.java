package model;

import java.util.ArrayList;

public class Registry {
	private ArrayList<Member> memberList;
	
	public Registry() {
		memberList = new ArrayList<>();
	}
	
	public void createMember(String name, String pNum) {
		Member member = new Member(name, pNum);
		memberList.add(member);
	}
	
	public String toString() {
		String list = "";
		for (int i = 0; i < memberList.size(); i++) {
			list += memberList.get(i).getMemberName() + " " + memberList.get(i).getMemberpNum() + " ID: " + memberList.get(i).getMemberID() + "\n";
		}
		//return memberList.get(0).getMemberName() + " " + memberList.get(0).getMemberpNum() + " ID: " + memberList.get(0).getMemberID();
		return list;
	}
}
