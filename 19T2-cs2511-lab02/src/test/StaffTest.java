package test;

import staff.Lecturer;
import staff.StaffMember;

public class StaffTest {

	public static void printStaffDetails(StaffMember s) {
		System.out.println(s.toString());
	}
	
	public static void main(String[] args) {
		StaffMember S = new StaffMember("A", 1000);
		StaffMember L = new Lecturer("A", 2000, "CSE", "A");
		printStaffDetails(S);
		printStaffDetails(L);
		if(S.equals(L)) System.out.println("They are equal!");
		else System.out.println("They are not equal!");
	}
}
