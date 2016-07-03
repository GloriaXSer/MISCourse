package com.system.service;

import java.util.List;

import com.system.dao.CourseDao;
import com.system.dao.SectionDao;
import com.system.dao.impl.CourseDaoImpl;
import com.system.dao.impl.SectionDaoImpl;
import com.system.model.Course;
import com.system.model.Section;

public class CourseService {
	public CourseDao courseDao = new CourseDaoImpl();
	public SectionDao sectionDao = new SectionDaoImpl();  
	
	public void initailizeCourse(Course course) throws Exception{
		List<Section> offeredAsSection = sectionDao.getOfferedAsSection(course.getCourseNo());
		List<Course> prerequisites = courseDao.getPrerequisites(course.getCourseNo());
		course.setOfferedAsSection(offeredAsSection);
		course.setPrerequisites(prerequisites);
	}	
}
