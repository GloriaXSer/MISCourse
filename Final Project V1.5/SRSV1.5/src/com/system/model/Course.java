package com.system.model;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.Section;

public class Course {
	private String courseNo;
	private String courseName;
	private double credits;
	private List<Section> offeredAsSection;
	private List<Course> prerequisites;
	private List<Course> prerequisiteOf;
	
	//Course构造器
	public Course(String courseNo, String courseName, double credits){
		this.setCourseNo(courseNo);
		this.setCourseName(courseName);
		this.setCredits(credits);
		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}
	public Course(){}
	
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
	public List<Section> getOfferedAsSection() {
		return offeredAsSection;
	}
	public void setOfferedAsSection(List<Section> offeredAsSection) {
		this.offeredAsSection = offeredAsSection;
	}
	public List<Course> getPrerequisites(){
		return prerequisites;
	}
	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public List<Course> getPrerequisiteOf() {
		return prerequisiteOf;
	}
	public void setPrerequisiteOf(List<Course> prerequisiteOf) {
		this.prerequisiteOf = prerequisiteOf;
	}
	
	//Course方法
	public boolean hasPrerequisites(){
		if (prerequisites.size()>0) return true;
		else return false;
	}

	public Section scheduleSection(String day, String time, String room, int capacity){
		String sectionNo = String.valueOf(offeredAsSection.size() + 1);
		Section section = new Section(sectionNo, day, time, this, room, capacity);
		this.addSection(section);
		return section;
	}
	
	public void addPrerequisite(Course course){
		prerequisites.add(course);		
	}
	
	public void addSection(Section section) {
		offeredAsSection.add(section);
	}
}
