package com.system.dao.impl.test;

import org.junit.Test;

import com.system.dao.CourseDao;
import com.system.dao.SectionDao;
import com.system.dao.impl.CourseDaoImpl;
import com.system.dao.impl.SectionDaoImpl;
import com.system.model.Course;

public class CourseDaoTest {
	CourseDao courseDao = new CourseDaoImpl();
	SectionDao sectionDao = new SectionDaoImpl();
	@Test
	public void test() throws Exception{
		System.out.println(courseDao.getPrerequisites("C002").size());
		Course course = sectionDao.getSectionBySectionNo("S002").getRepresentedCourse();
		System.out.println(course.hasPrerequisites());
	}
}
