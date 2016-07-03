package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.system.dao.StudentDao;
import com.system.model.Student;
import com.system.utils.JDBCsqlite;

public class StudentDaoImpl implements StudentDao{
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	
	@Override
	public String getLogined(String id, String password) throws Exception{
		Student student = new Student();
		String sql = "select * from Person";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			if(id.equals(rs.getString("id")) && password.equals(rs.getString("password"))){
				student.setId(id);
				student.setPassword(rs.getString("password"));
				student.setName(rs.getString("name"));
				student.setMajor(rs.getString("major"));
				student.setDegree(rs.getString("degree"));
				return "success";
			}
		}
		return "error";
	}

	@Override
	public Student getOneStudent(String id) throws Exception {
		Student student = new Student();
		String sql="select * from Person where id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			student.setId(id);
			student.setName(rs.getString("name"));
			student.setMajor(rs.getString("major")); 
			student.setDegree(rs.getString("degree"));		
		}
		return student;
	}

	@Override
	public void addStudent(Student student) throws Exception {
		String sql = "insert into Person(id,name,major,password,degree,type) value(?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, student.getId());
		ps.setString(2, student.getName());
		ps.setString(3, student.getMajor());
		ps.setString(4, student.getPassword());
		ps.setString(5, student.getDegree());
		ps.setString(6, "student");
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteStudent(String id) throws Exception {
		String sql="delete from Person where id=? and type='student'";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void updateStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getAll() throws Exception {
		List<Student> students = new ArrayList<Student>();
		String sql="select * from Person where type='student'";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Student student = new Student();
			student.setId(rs.getString("id"));
			student.setName(rs.getString("name"));
			student.setMajor(rs.getString("major"));
			student.setDegree(rs.getString("degree"));
			students.add(student);
		}
		return students;
	}
}
