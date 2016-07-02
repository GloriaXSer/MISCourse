package com.system.dao;

import java.util.List;

import com.system.model.Course;
import com.system.model.CourseCatalog;
import com.system.model.Section;

public interface CourseDao {
	public Course getCourseByCourseNo(String courseNo) throws Exception;
	
	public Course getCourseByCourseName(String courseName) throws Exception;
	
	public List<Course> getPrerequisites(String courseNo) throws Exception;
	
	public Section scheduleSection(Course course, String day, String time, String room, int capacity) throws Exception;
	
	public void addPrerequisite(String precourseNo, String courseNo) throws Exception;
	
	public void addCourse(Course course) throws Exception;
	
	public void deleteCourse(String courseNo) throws Exception;
	
	public void updateCourse(Course course, String precourseNo) throws Exception;
	
	public List<Course> getAll() throws Exception;
	
	public CourseCatalog initializeCourses() throws Exception;
}
