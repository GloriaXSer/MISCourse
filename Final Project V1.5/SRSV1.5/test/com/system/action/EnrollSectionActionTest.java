package com.system.action;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.system.dao.SectionDao;
import com.system.dao.StudentDao;
import com.system.dao.TranscriptEntryDao;
import com.system.dao.impl.SectionDaoImpl;
import com.system.dao.impl.StudentDaoImpl;
import com.system.dao.impl.TranscriptEntryDaoImpl;
import com.system.model.Course;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.TranscriptEntry;
import com.system.service.SectionService;
import com.system.service.StudentService;

public class EnrollSectionActionTest {

	@Test
	public void test() throws Exception {
		Student student = new Student("01140001");
		Section section = new Section("S001");
		StudentService studentService = new StudentService();
		SectionService sectionService = new SectionService();
		studentService.initializeStudent(student);
		sectionService.initializeTheSection(section);
		for (Section s2: student.getAttends()){
			Course c2 = s2.getRepresentedCourse();
			System.out.println(s2.getGrade(student));
		}
		System.out.println(student.isCurrentEnrolledSimilar(section));
		for(TranscriptEntry te: student.getTranscript().getTranscriptEntries()){
			Section s = te.getSection();
			System.out.println(s.isSectionOf(section.getRepresentedCourse()));
		}
		System.out.println(student.getTranscript().verifyCompletion(section.getRepresentedCourse()));
		section.enroll(student);
		System.out.println(section.enroll(student).toString());
	}

}
