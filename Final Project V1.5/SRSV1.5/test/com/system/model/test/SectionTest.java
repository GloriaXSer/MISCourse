package com.system.model.test;

import java.util.HashMap;

import com.system.model.Course;
import com.system.model.EnrollmentStatus;
import com.system.model.test.ProfessorTest;
import com.system.model.test.ScheduleOfClassesTest;
import com.system.model.test.StudentTest;
import com.system.model.test.TranscriptEntryTest;
import com.system.model.test.TranscriptTest;

public class SectionTest {
	private String sectionNo;
	private String dayOfWeek; 
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private CourseTest representedCourse;
	private ScheduleOfClassesTest offeredIn;
	private ProfessorTest instructor;
	private HashMap<String, StudentTest> enrolledStudents;
	private HashMap<StudentTest, TranscriptEntryTest> assignedGrades;
	
	public SectionTest(String sectionNo, String day, String time, CourseTest courseTest, String room, int capacity) {
		this.sectionNo = sectionNo;
		this.dayOfWeek = day;
		this.timeOfDay = time;
		this.representedCourse = courseTest;
		this.room = room;
		this.seatingCapacity = capacity;
		// A Professor has not yet been identified.
		setInstructor(null);
		
		enrolledStudents = new HashMap<String, StudentTest>();
		assignedGrades = new HashMap<StudentTest, TranscriptEntryTest>();
	}
	public SectionTest(){
		//空构造方法
	}
	
	//属性初始化
	public String getSectionNo() {
		return sectionNo;
	}

	public void setSectionNo(String sectionNo) {
		this.sectionNo = sectionNo;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public CourseTest getRepresentedCourse(){
		return representedCourse;
	}
	
	public void setRepresentedCourse(CourseTest representedCourse) {
		this.representedCourse = representedCourse;
	}

	public ScheduleOfClassesTest getOfferedIn() {
		return offeredIn;
	}

	public void setOfferedIn(ScheduleOfClassesTest offeredIn) {
		this.offeredIn = offeredIn;
	}

	public ProfessorTest getInstructor() {
		return instructor;
	}

	public void setInstructor(ProfessorTest instructor) {
		this.instructor = instructor;
	}
	
	
	public EnrollmentStatusTest enroll(StudentTest s){
		
		TranscriptTest transcript = s.getTranscript();
		
		if(s.isCurrentEnrolledSimilar(this) ||
		   transcript.verifyCompletion(this.getRepresentedCourse())){
		   return EnrollmentStatusTest.prevEnroll;
		}
		
		CourseTest c = this.getRepresentedCourse();
		if(c.hasPrerequisites()){
			for(CourseTest pre:c.getPrerequisites()){
				if(!transcript.verifyCompletion(pre)){
					return EnrollmentStatusTest.prereq;
				}
			}
		}
		if(!this.confirmSeatAvailability()){
			return EnrollmentStatusTest.secFull;
		}
		
		enrolledStudents.put(s.getId(), s);
		s.addSection(this);
		
		return EnrollmentStatusTest.success;
	}
	
	public boolean drop(StudentTest s){
		if(!s.isEnrolledIn(this)) return false;
		else{
			enrolledStudents.remove(s.getId());
			s.dropSection(this);
			return true;
		}
	}
	
	public int getTotalEnrollment() {
		return enrolledStudents.size();
	}
	
	public boolean confirmSeatAvailability() {
		if(enrolledStudents.size() < this.getSeatingCapacity()) return true;
		else return false;
	}

	public String getGrade(StudentTest student) {
		String grade = null;
		TranscriptEntryTest te = assignedGrades.get(student);
		if(te != null){
			grade = te.getGrade();
		}
		return grade;
	}
	
	public boolean postGrade(StudentTest s, String grade){
		if(!TranscriptEntryTest.validateGrade(grade)) return false;
		if(assignedGrades.get(s) != null) return false;
		TranscriptEntryTest te = new TranscriptEntryTest(s,grade,this);
		
		assignedGrades.put(s, te);
		return true;
	}
	
	public boolean isSectionOf(CourseTest course) {
		if (course == representedCourse) return true;
		else return false;
	}
	
	
	public String toString() {
		return getRepresentedCourse().getCourseNo() + " - " +
		       getSectionNo() + " - " +
		       getDayOfWeek() + " - " +
		       getTimeOfDay();
	}
	
	public String getFullSectionNo() {
		return getRepresentedCourse().getCourseNo() + 
		       " - " + getSectionNo();
	}
	
	public void displayStudentRoster() {
		System.out.print("\tTotal of " + getTotalEnrollment() + 
				   " students enrolled");

		if (getTotalEnrollment() == 0) System.out.println(".");
		else System.out.println(", as follows:");

		for (StudentTest s : enrolledStudents.values()) {
			System.out.println("\t\t" + s.getName());
		}
	}
	
	public void display(){
		System.out.println("Section Information:");
		System.out.println("\tSemester:  " + getOfferedIn().getSemester());
		System.out.println("\tCourse No.:  " + 
				   getRepresentedCourse().getCourseNo());
		System.out.println("\tSection No:  " + getSectionNo());
		System.out.println("\tOffered:  " + getDayOfWeek() + 
				   " at " + getTimeOfDay());
		System.out.println("\tIn Room:  " + getRoom());
		if (getInstructor() != null) 
			System.out.println("\tProfessor:  " + 
				   getInstructor().getName());
		displayStudentRoster();
	}

}
