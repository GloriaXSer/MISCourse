package com.system.test;


import java.util.ArrayList;

import com.system.model.test.CourseTest;
import com.system.model.test.EnrollmentStatusTest;
import com.system.model.test.ProfessorTest;
import com.system.model.test.ScheduleOfClassesTest;
import com.system.model.test.SectionTest;
import com.system.model.test.StudentTest;

public class SRSTest {
	public static ArrayList<ProfessorTest> faculty;
	public static ArrayList<StudentTest> studentBody;
	public static ArrayList<CourseTest> courseCatalog;
	
	public static ScheduleOfClassesTest scheduleOfClasses = new ScheduleOfClassesTest("SP2005");
	

	public static void main(String[] args) {
		ProfessorTest p1, p2, p3;
		StudentTest s1, s2, s3;
		CourseTest c1, c2, c3, c4, c5;
		SectionTest sec1, sec2, sec3, sec4, sec5, sec6, sec7;
		/**
		 * Professors
		 */
		p1 = new ProfessorTest("Jacquie Barker", "123-45-6789", "Adjunct Professor", "Information Technology");
		p2 = new ProfessorTest("Claudio Cioffi", "567-81-2345", "Full Professor", "Computational Social Sciences");
		p3 = new ProfessorTest("Snidely Whiplash", "987-65-4321", "Full Professor", "Physical Education");
		
		faculty = new ArrayList<ProfessorTest>();
		faculty.add(p1);
		faculty.add(p2);
		faculty.add(p3);
		
		/**
		 * Students
		 */
		s1 = new StudentTest("Joe Blow", "111-11-1111", "Math", "M.S.");
		s2 = new StudentTest("Fred Schnurd", "222-22-2222", "Information Technology", "Ph.D.");
		s3 = new StudentTest("Mary Smith", "333-33-3333", "Physics", "B.S.");
		
		studentBody = new ArrayList<StudentTest>();
		studentBody.add(s1);
		studentBody.add(s2);
		studentBody.add(s3);
		
		/**
		 * Courses
		 */
		c1 = new CourseTest("CMP101", "Beginning Computer Technology", 3.0);
		c2 = new CourseTest("OBJ101", "Object Methods for Software Development", 3.0);
		c3 = new CourseTest("CMP283", "Higher Level Languages (Java)", 3.0);
		c4 = new CourseTest("CMP999", "Living Brain Computers", 3.0);
		c5 = new CourseTest("ART101", "Beginning Basketweaving", 3.0);
		
		courseCatalog = new ArrayList<CourseTest>();
		courseCatalog.add(c1);
		courseCatalog.add(c2);
		courseCatalog.add(c3);
		courseCatalog.add(c4);
		courseCatalog.add(c5);
		//Establish some prerequisites(c1 => c2 => c3 => c4).
		c2.addPrerequisite(c1);
		c3.addPrerequisite(c2);
		c4.addPrerequisite(c3);
		
		/**
		 * Sections
		 */
		sec1 = c1.scheduleSection("Monday", "8:10 - 10:00 PM", "GOVT101", 30);
		sec2 = c1.scheduleSection("Wednesday", "6:10 - 8:00 PM", "GOVT202", 30);
		sec3 = c2.scheduleSection("Thursday", "4:10 - 6:00 PM", "GOVT105", 25);
		sec4 = c2.scheduleSection("Tuesday", "6:10 - 8:00 PM", "SCI330", 25);
		sec5 = c3.scheduleSection("Monday", "6:10 - 8:00 PM", "GOVT101", 20);
		sec6 = c4.scheduleSection("Thursday", "4:10 - 6:00 PM", "SCI241", 15);
		sec7 = c5.scheduleSection("Monday", "4:10 - 6:00 PM", "ARTS25", 40);
		
		scheduleOfClasses.addSection(sec1);
		scheduleOfClasses.addSection(sec2);
		scheduleOfClasses.addSection(sec3);
		scheduleOfClasses.addSection(sec4);
		scheduleOfClasses.addSection(sec5);
		scheduleOfClasses.addSection(sec6);
		scheduleOfClasses.addSection(sec7);
		
		p3.agreeToTeach(sec1);
		p2.agreeToTeach(sec2);
		p1.agreeToTeach(sec3);
		p3.agreeToTeach(sec4);
		p1.agreeToTeach(sec5);
		p2.agreeToTeach(sec6);
		p3.agreeToTeach(sec7);
		
		System.out.println("===============================");
		System.out.println("Student registration has begun!");
		System.out.println("===============================");
		System.out.println();
		
		System.out.println("Student" + s1.getName() + " is attempting to enroll in " + sec1.toString());
		
		EnrollmentStatusTest status = sec1.enroll(s1);
		SRSTest.reportStatus(status);
		
		attemptToEnroll(s1, sec2);
		attemptToEnroll(s2, sec2);
		attemptToEnroll(s2, sec3);
		attemptToEnroll(s2, sec7);
		attemptToEnroll(s3, sec1);
		
		sec1.postGrade(s1, "C+");
		sec1.postGrade(s3, "A");
		sec2.postGrade(s2, "B+");
		sec7.postGrade(s2, "A-");
		
		System.out.println("====================");
		System.out.println("Schedule of Classes:");
		System.out.println("====================");
		System.out.println();
		scheduleOfClasses.display();
		
		System.out.println("======================");
		System.out.println("Professor Information:");
		System.out.println("======================");
		System.out.println();
		p1.display();
		p2.display();
		p3.display();
		
		System.out.println("====================");
		System.out.println("Student Information:");
		System.out.println("====================");
		System.out.println();
		s1.display();
		s2.display();
		s3.display();
	}


	private static void reportStatus(EnrollmentStatusTest status) {
		System.out.println("Status:" + status.value());
		System.out.println();
	}

	private static void attemptToEnroll(StudentTest s, SectionTest sec){
		System.out.print("Student " + s.getName() + 
						" is attempting to enroll in " + 
						sec.toString());
		
		reportStatus(sec.enroll(s));
	}

}
