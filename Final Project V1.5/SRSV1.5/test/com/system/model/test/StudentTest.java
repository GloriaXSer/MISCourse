package com.system.model.test;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.Person;
import com.system.model.Section;
import com.system.model.test.TranscriptTest;


public class StudentTest extends PersonTest {
	private String major;
	private String degree;
	private String password;
	private TranscriptTest transcript;
	private List<SectionTest> attends;
	//构造方法
	public StudentTest(String name, String id, String major, String degree){
		super(name,id);
		this.setMajor(major);
		this.setDegree(degree);
		this.setTranscript(new TranscriptTest(this));
		attends = new ArrayList<SectionTest>();
	}
	public StudentTest(String id){
		this("TBD",id,"TBD","TBD");
	}
	public StudentTest() {
		
	}
	//属性设置
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public TranscriptTest getTranscript() {
		return transcript;
	}

	public void setTranscript(TranscriptTest transcript) {
		this.transcript = transcript;
	}

	public List<SectionTest> getAttends() {
		return attends;
	}

	public void setAttends(List<SectionTest> attends) {
		this.attends = attends;
	}
	
	//主要业务逻辑
	public void addSection(SectionTest sectionTest){
		attends.add(sectionTest);
	}
	public void dropSection(SectionTest sectionTest){
		attends.remove(sectionTest);
	}
	public boolean isEnrolledIn(SectionTest sectionTest){
		if (attends.contains(sectionTest)) return true;
		else return false;
	}
	
	public boolean isCurrentEnrolledSimilar(SectionTest sectionTest){
		boolean foundMatch = false;
		
		CourseTest c1= sectionTest.getRepresentedCourse();
		for (SectionTest s2: attends){
			CourseTest c2 = s2.getRepresentedCourse();
			if(c1 == c2){
				if(s2.getGrade(this) == null ){
					foundMatch = true;
					break;
				}
			}
		}
		return foundMatch;
	}
	public List<SectionTest> getEnrolledSections(){
		return attends;
	}
	
	
	public String toString(){
		return this.getName() + "(" + this.getId() + ")[" + this.getDegree() + " - " + this.getMajor() + "]";
	}
	
	public void printTranscript() {
		TranscriptTest transcript = this.getTranscript();
		transcript.display();
	}
	
	public void display(){
		super.display();		
		System.out.println("Student-Specific Information");
		System.out.println("\tMajor: " + this.getMajor());
		System.out.println("\tDegree: " + this.getDegree());
		this.displayCourseSchedule();
		this.printTranscript();		
		System.out.println();
	}
	
	public void displayCourseSchedule() {
		System.out.println("Course Schedule for" + this.getName());
		for(SectionTest s: attends){
			if(s.getGrade(this) == null){
				System.out.println("\tCourse No.:" + s.getRepresentedCourse().getCourseNo());
				System.out.println("\tSection No.:" + s.getSectionNo());
				System.out.println("\tCourse Name:" + s.getRepresentedCourse().getCourseName());
				System.out.println("\tMeeting Day and Time Held: " + s.getDayOfWeek() + "-" + s.getTimeOfDay());
				System.out.println("\tRoom Location: " + s.getRoom());
				System.out.println("\tProfessor's Name: " + s.getInstructor().getName());
				System.out.println("\t---------");
			}			
		}
	}
}
