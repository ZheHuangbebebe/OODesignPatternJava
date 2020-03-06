package unsw.enrolment;

import java.util.ArrayList;

public class Enrolment {

    private CourseOffering offering;
    private Grade grade;
    private Student student;
    private ArrayList<Session> sessions;

    public Enrolment(CourseOffering offering, Student student, Session s) {
		this.offering = offering;
		this.student = student;
		this.grade = new Grade(0, "");
		this.sessions = new ArrayList<Session>();
		sessions.add(s);
    }
    
    public void addSession(Session s) {
    	sessions.add(s);
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }
    
    public Grade getGrade() {
    	return grade;
    }
    
    public Student getStudent() {
    	return student;
    }
    
    public void setGrade(int num, String g) {
    	grade.setGrade(g);
    	grade.setMark(num);
    	System.out.println("Grade recorded");
    }

}
