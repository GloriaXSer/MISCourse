package com.system.dao;

import java.util.List;

import com.system.model.Student;

public interface StudentDao {
	
	public String getLogined(String id, String password) throws Exception;
	
	public Student getOneStudent(String id) throws Exception;
	
	public void addStudent(Student student) throws Exception;
	
	public void deleteStudent(String id) throws Exception;
	
	public void updateStudent(Student student) throws Exception;
	
	public List<Student> getAll() throws Exception;

}
