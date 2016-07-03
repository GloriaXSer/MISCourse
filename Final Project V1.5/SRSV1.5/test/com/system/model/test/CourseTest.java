package com.system.model.test;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.test.SectionTest;

public class CourseTest {
	private String courseNo;
	private String courseName;
	private double credits;
	private List<SectionTest> offeredAsSection;
	private List<CourseTest> prerequisites;
	private List<CourseTest> prerequisiteOf;
	
	//Course构造器
	public CourseTest(String courseNo, String courseName, double credits){
		this.setCourseNo(courseNo);
		this.setCourseName(courseName);
		this.setCredits(credits);
		offeredAsSection = new ArrayList<SectionTest>();
		prerequisites = new ArrayList<CourseTest>();
	}
	public CourseTest(){
		
	}
	
	//属性基本方法初始化
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	public List<SectionTest> getOfferedAsSection() {
		return offeredAsSection;
	}
	public void setOfferedAsSection(List<SectionTest> offeredAsSection) {
		this.offeredAsSection = offeredAsSection;
	}
	public List<CourseTest> getPrerequisites(){
		return prerequisites;
	}
	public void setPrerequisites(List<CourseTest> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public List<CourseTest> getPrerequisiteOf() {
		return prerequisiteOf;
	}
	public void setPrerequisiteOf(List<CourseTest> prerequisiteOf) {
		this.prerequisiteOf = prerequisiteOf;
	}
	
	//Course方法
	public boolean hasPrerequisites(){
		if (prerequisites.size()>0) return true;
		else return false;
	}

	public SectionTest scheduleSection(String day, String time, String room, int capacity){
		String sectionNo = String.valueOf(offeredAsSection.size() + 1);
		SectionTest section = new SectionTest(sectionNo, day, time, this, room, capacity);
		this.addSection(section);
		return section;
	}
	
	public void addPrerequisite(CourseTest course){
		prerequisites.add(course);		
	}
	
	public void addSection(SectionTest section) {
		offeredAsSection.add(section);
	}
	
	public String toString(){
		return getCourseNo() + ":  " + getCourseName();
	}
	
	public void display(){
		System.out.println("Course Information:");
		System.out.println("\tCourse No.:  " + getCourseNo());
		System.out.println("\tCourse Name:  " + getCourseName());
		System.out.println("\tCredits:  " + getCredits());
		System.out.println("\tPrerequisite Courses:");
		
		for (CourseTest c : prerequisites) {
			System.out.println("\t\t" + c.toString());
		}	
		System.out.print("\tOffered As Section(s):  ");
		
		for (SectionTest s : offeredAsSection) {
			System.out.print(s.getSectionNo() + " ");
		}
		System.out.println();
	}
}
