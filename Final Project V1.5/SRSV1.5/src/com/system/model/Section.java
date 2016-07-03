package com.system.model;

import java.util.HashMap;

import com.system.model.Course;
import com.system.model.EnrollmentStatus;
import com.system.model.Professor;
import com.system.model.ScheduleOfClasses;
import com.system.model.Student;
import com.system.model.TranscriptEntry;
import com.system.model.Transcript;

public class Section {
	private String sectionNo;
	private String dayOfWeek; 
	private String timeOfDay;
	private String room;
	private int seatingCapacity;
	private Course representedCourse;
	private ScheduleOfClasses offeredIn;
	private Professor instructor;
	private HashMap<String, Student> enrolledStudents;
	private HashMap<String, TranscriptEntry> assignedGrades;
	
	public HashMap<String, Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(HashMap<String, Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	public HashMap<String, TranscriptEntry> getAssignedGrades() {
		return assignedGrades;
	}
	public void setAssignedGrades(HashMap<String, TranscriptEntry> assignedGrades) {
		this.assignedGrades = assignedGrades;
	}

	private String courseName;
	private String professorName;
	
	public Section(String sectionNo, String day, String time, Course course, String room, int capacity) {
		this.sectionNo = sectionNo;
		this.dayOfWeek = day; 
		this.timeOfDay = time;
		this.representedCourse = course;
		this.room = room;
		this.seatingCapacity = capacity;
		// A Professor has not yet been identified.
		setInstructor(null);		
		enrolledStudents = new HashMap<String, Student>();
		assignedGrades = new HashMap<String, TranscriptEntry>();
	}
	public Section(String sectionNo, String day, String time, String room, int capacity, String courseName, String professorName){
		this.sectionNo = sectionNo;
		this.dayOfWeek = day;
		this.timeOfDay = time;
		this.room = room;
		this.seatingCapacity = capacity;
		this.courseName = courseName;
		this.professorName = professorName;
	}
	public Section(String sectionNo){
		this.sectionNo = sectionNo;
	}
	public Section(){
		//空构造方法
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
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

	public Course getRepresentedCourse(){
		return representedCourse;
	}
	
	public void setRepresentedCourse(Course representedCourse) {
		this.representedCourse = representedCourse;
	}

	public ScheduleOfClasses getOfferedIn() {
		return offeredIn;
	}

	public void setOfferedIn(ScheduleOfClasses offeredIn) {
		this.offeredIn = offeredIn;
	}

	public Professor getInstructor() {
		return instructor;
	}

	public void setInstructor(Professor instructor) {
		this.instructor = instructor;
	}
	
	public EnrollmentStatus enroll(Student s){
		
		Transcript transcript = s.getTranscript();
		
		if(s.isCurrentEnrolledSimilar(this) ||
		   transcript.verifyCompletion(this.getRepresentedCourse())){
		   return EnrollmentStatus.prevEnroll;
		}
		
		Course c = this.getRepresentedCourse();
		if(c.hasPrerequisites()){
			for(Course pre:c.getPrerequisites()){
				if(!transcript.verifyCompletion(pre)){
					return EnrollmentStatus.prereq;
				}
			}
		}
		if(!this.confirmSeatAvailability()){
			return EnrollmentStatus.secFull;
		}
		
		enrolledStudents.put(s.getId(), s);
		s.addSection(this);
		
		return EnrollmentStatus.success;
	}
	
	public boolean drop(Student s){
		if(!s.isEnrolledIn(this)) return false;
		else{
			enrolledStudents.remove(s.getId());
			s.dropSection(this);
			return true;
		}
	}
	
//	public int getTotalEnrollment() {
//		return enrolledStudents.size();
//	}
	
	public boolean confirmSeatAvailability() {
		if(enrolledStudents.size() < this.getSeatingCapacity()) return true;
		else return false;
	}

	public String getGrade(Student student) {
		String grade = null;
		TranscriptEntry te = assignedGrades.get(student.getId());
		if(te != null){
			grade = te.getGrade();
		}
		return grade;
	}
	
	public boolean postGrade(Student s, String grade){
		if(!TranscriptEntry.validateGrade(grade)) return false;
		if(assignedGrades.get(s) != null) return false;
		TranscriptEntry te = new TranscriptEntry(s,grade,this);
		
		assignedGrades.put(s.getId(), te);
		return true;
	}
	
	public boolean isSectionOf(Course course) {
		if (course.getCourseNo().equals(representedCourse.getCourseNo())) return true;
		else return false;
	}
	
}
