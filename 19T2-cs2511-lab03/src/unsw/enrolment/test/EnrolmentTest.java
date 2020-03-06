package unsw.enrolment.test;

import unsw.enrolment.Course;

import unsw.enrolment.CourseOffering;
import unsw.enrolment.Enrolment;
import unsw.enrolment.EnrolmentSystem;
import unsw.enrolment.Lab;
import unsw.enrolment.Lecture;
import unsw.enrolment.Session;
import unsw.enrolment.Student;
import unsw.enrolment.Tutorial;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EnrolmentTest {

    public static void main(String[] args) {

        // Create courses
        Course comp1511 = new Course("COMP1511", "Programming Fundamentals");
        Course comp1531 = new Course("COMP1531", "Software Engineering Fundamentals");
        comp1531.addPrereq(comp1511);
        Course comp2521 = new Course("COMP2521", "Data Structures and Algorithms");
        comp2521.addPrereq(comp1511);

        CourseOffering comp1511Offering = new CourseOffering(comp1511, "19T1");
        CourseOffering comp1531Offering = new CourseOffering(comp1531, "19T1");
        CourseOffering comp2521Offering = new CourseOffering(comp2521, "19T2");

        // TODO Create some sessions for the courses
        Session comp1511Lec = new Lecture("UNSW", DayOfWeek.TUESDAY, 
        		LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"), "Lecturer1");
        comp1511Offering.addSession(comp1511Lec);
        
        Session comp1531Lab = new Lab("UNSW", DayOfWeek.MONDAY, 
        		LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"), "Tuotr1", "LabAssistant1");
        comp1531Offering.addSession(comp1531Lab);
        
        Session comp2521Tut = new Tutorial("UNSW", DayOfWeek.TUESDAY, 
        		LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"), "Tutor1");
        comp2521Offering.addSession(comp2521Tut);
        
        // TODO Create a student
        Student student = new Student("Student1");
        
        EnrolmentSystem enrolmentSystem = new  EnrolmentSystem();

        // TODO Enrol the student in COMP1511 for T1 (this should succeed)
        enrolmentSystem.addEnrolment(comp1511Offering,student, comp1511Lec);

        // TODO Enrol the student in COMP1531 for T1 (this should fail as they
        // have not met the prereq)
        enrolmentSystem.addEnrolment(comp1531Offering,student, comp1531Lab);

        // TODO Give the student a passing grade for COMP1511
        enrolmentSystem.setGrade(student, 50, "PS");

        // TODO Enrol the student in 2521 (this should succeed as they have met
        // the prereqs)
        enrolmentSystem.addEnrolment(comp2521Offering,student, comp2521Tut);

    }
}
