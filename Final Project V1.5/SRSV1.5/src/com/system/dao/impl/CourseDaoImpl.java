package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.system.dao.CourseDao;
import com.system.model.Course;
import com.system.model.CourseCatalog;
import com.system.model.Section;
import com.system.utils.JDBCsqlite;

public class CourseDaoImpl implements CourseDao {
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	
	@Override
	public Course getCourseByCourseNo(String courseNo) throws Exception{
		Course course = new Course();
		String sql = "select * from Course where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseNo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			course.setCourseNo(courseNo);
			course.setCourseName(rs.getString("courseName"));
			course.setCredits(rs.getDouble("credits"));
		}		
		return course;
	}

	@Override
	public Course getCourseByCourseName(String courseName) throws Exception{
		Course course = new Course();
		String sql = "select * from Course where courseName=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseName);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			course.setCourseNo(rs.getString("courseNo"));
			course.setCourseName(courseName);
			course.setCredits(rs.getDouble("credits"));
		}		
		return course;
	}
	
	@Override
	public List<Course> getPrerequisites(String courseNo) throws Exception{
		List<Course> prerequisites = new ArrayList<Course>();
		String sql="select * from Prerequisites where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseNo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			prerequisites.add(getCourseByCourseNo(rs.getString("precourseNo")));
		}
		return prerequisites;
	}

	@Override
	public Section scheduleSection(Course course, String day, String time, String room, int capacity) {
		Section section = new Section(String.valueOf(course.getOfferedAsSection().size() + 1), day, time, course, room, capacity);
		return section;
	}

	@Override
	public void addPrerequisite(String precourseNo, String courseNo) throws Exception{
		String sql="insert into Prerequisites values(?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, precourseNo);
		ps.setString(2, courseNo);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteCourse(String courseNo) throws Exception{
		String sql="delete from Course where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseNo);
		ps.executeUpdate();
		ps.close();	
	}

	@Override
	public void addCourse(Course course) throws Exception {
		String sql="insert into Course values(?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, course.getCourseNo());
		ps.setString(2, course.getCourseName());
		ps.setDouble(3, course.getCredits());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void updateCourse(Course course, String precourseNo) throws Exception {
		String sql="update Course set courseNo=?, courseName=?, credits=? where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, course.getCourseNo());
		ps.setString(2, course.getCourseName());
		ps.setDouble(3, course.getCredits());
		ps.setString(4, precourseNo);
		ps.executeUpdate();
		ps.close();
	}
 
	@Override
	public List<Course> getAll() throws Exception {
		List<Course> courses = new ArrayList<Course>();
		String sql="select * from Course";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Course course = new Course();
			course.setCourseName(rs.getString("courseName"));
			course.setCourseNo(rs.getString("courseNo"));
			course.setCredits(rs.getDouble("credits"));
			courses.add(course);
		}
		
		return null;
	}

	@Override
	public CourseCatalog initializeCourses() throws Exception {
		CourseCatalog courseCatelog = new CourseCatalog();
		HashMap<String, Course> courses = new HashMap<String, Course>();
		String sql="select * from Course";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Course course = new Course();
			course.setCourseName(rs.getString("courseName"));
			course.setCourseNo(rs.getString("courseNo"));
			course.setCredits(rs.getDouble("credits"));
			courses.put(rs.getString("courseNo"), course);
		}
		courseCatelog.getCourses();
		return courseCatelog;
	}


}
