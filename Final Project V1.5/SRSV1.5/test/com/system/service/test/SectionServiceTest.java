package com.system.service.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.system.dao.SectionDao;
import com.system.dao.StudentDao;
import com.system.dao.impl.SectionDaoImpl;
import com.system.dao.impl.StudentDaoImpl;
import com.system.model.Section;
import com.system.model.Student;
import com.system.service.SectionService;
import com.system.service.StudentService;

public class SectionServiceTest {

	@Test
	public void test() throws Exception{
		Student student = new Student("01140001");
		Section section = new Section("S002");
		StudentDao studentDao = new StudentDaoImpl();
		SectionDao sectionDao = new SectionDaoImpl();
		SectionService sectionService = new SectionService();
		sectionService.initializeTheSection(section);
		System.out.println(section.getEnrolledStudents().size());
		System.out.println(section.getRepresentedCourse().getCourseName());
	}

}
