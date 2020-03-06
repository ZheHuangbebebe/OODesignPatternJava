package unsw.enrolment;

import java.util.ArrayList;

public class EnrolmentSystem {

	private ArrayList <Enrolment> enrolments;
	
	public EnrolmentSystem() {
		enrolments = new ArrayList<Enrolment>();
	}
	
	private boolean reachPrereq(Course c, Student s) {
		if(c.getPrereqs().isEmpty()) return true;
		ArrayList<Course> enrolledCourses = new ArrayList<Course>();
		for (Enrolment e : enrolments) {
			if(e.getStudent().equals(s) && e.getGrade().getMark() >= 50) {
    			enrolledCourses.add(e.getCourse());
    		}
		}
		if(enrolledCourses.containsAll(c.getPrereqs())) return true;
		return false;
	}
	
   public void addEnrolment(CourseOffering c, Student student, Session s) {
    	if(reachPrereq(c.getCourse(), student)) {
    		enrolments.add(new Enrolment (c, student, s));
    		System.out.println("You have enrolled " +c.getCourse().getCourseCode());
    	}
    	else
    		System.out.println("You can't enrol "+c.getCourse().getCourseCode());
    }
   
   public void setGrade(Student student, int num, String grade) {
   	for(Enrolment e : enrolments) {
   		if(e.getStudent().equals(student)) {
   			e.setGrade(num,  grade);
   		}
   	}
   }
}
