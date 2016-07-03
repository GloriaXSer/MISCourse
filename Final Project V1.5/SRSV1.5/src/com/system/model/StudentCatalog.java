package com.system.model;

import java.util.HashMap;

public class StudentCatalog {
	
	private HashMap<String, Student> students;

	public HashMap<String, Student> getStudents() {
		return students;
	}

	public void setStudents(HashMap<String, Student> students) {
		this.students = students;
	}
	
	public StudentCatalog(){
		students = new HashMap<String, Student>();
	}
	
	public void addStudent(Student s){
		String key = s.getId();
		students.put(key, s);
	}
	
	public Student findStudent(String id){
		return students.get(id);
	}
	
	public boolean isEmpty(){
		if(students.size() == 0) return true;
		else return false;
	}

}
