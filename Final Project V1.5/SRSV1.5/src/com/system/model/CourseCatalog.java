package com.system.model;

import java.util.HashMap;

public class CourseCatalog {
	
	private HashMap<String, Course> courses;

	public HashMap<String, Course> getCourses() {
		return courses;
	}

	public void setCourses(HashMap<String, Course> courses) {
		this.courses = courses;
	}
	
	public CourseCatalog(){
		courses= new HashMap<String, Course>();
	}
	
	public void addCourse(Course c){
		String key = c.getCourseNo();
		courses.put(key, c);
	}
	
	public Course findCourse(String courseNo){
		return courses.get(courseNo);
	}
	
	public boolean isEmpty(){
		if(courses.size() == 0) return true;
		else return false;
	}
}
