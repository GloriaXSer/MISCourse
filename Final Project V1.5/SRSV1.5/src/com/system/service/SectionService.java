package com.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.system.dao.CourseDao;
import com.system.dao.ProfessorDao;
import com.system.dao.ScheduleOfClassesDao;
import com.system.dao.SectionDao;
import com.system.dao.StudentDao;
import com.system.dao.TranscriptEntryDao;
import com.system.dao.impl.CourseDaoImpl;
import com.system.dao.impl.ProfessorDaoImpl;
import com.system.dao.impl.ScheduleOfClassesDaoImpl;
import com.system.dao.impl.SectionDaoImpl;
import com.system.dao.impl.StudentDaoImpl;
import com.system.dao.impl.TranscriptEntryDaoImpl;
import com.system.model.ScheduleOfClasses;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.TranscriptEntry;

public class SectionService {
	SectionDao sectionDao = new SectionDaoImpl();
	StudentService studentService = new StudentService();
	ScheduleOfClassesDao scheduleOfClassesDao = new ScheduleOfClassesDaoImpl();
	ProfessorDao professorDao = new ProfessorDaoImpl();
	TranscriptEntryDao transcriptEntryDao = new TranscriptEntryDaoImpl();
	CourseDao courseDao = new CourseDaoImpl();
	ScheduleOfClasses scheduleOfClasses = null;
	
	public Section getSectionBySectionNo(String sectionNo) throws Exception{
		Section certainSection = sectionDao.getSectionBySectionNo(sectionNo);
		return certainSection;
	}
	
	public void initializeSection(ScheduleOfClasses scheduleOfClasses) throws Exception{
		List<Section> sections = sectionDao.getAll();
		for(Section s:sections){
			scheduleOfClasses.addSection(s);
		}
	}
	
	public void deleteSection(String sectionNo) throws Exception{
		sectionDao.deleteSection(sectionNo);
	}
	
	public List<Section> getAll() throws Exception{
		return sectionDao.getAllSection();
	}

	public void addSection(Section section) throws Exception{
		if(courseDao.getCourseByCourseName(section.getCourseName()) != null 
		   && professorDao.getProfessorByName(section.getProfessorName()) != null){
			sectionDao.addSection(section);
		}
	}
	
	public void updateSection(Section section, String oldNo) throws Exception{
		sectionDao.updateSection(section, oldNo);
	}
	
	public List<Section> search(String semester, String id, String courseNo) throws Exception{
		List<Section> sections = new ArrayList<Section>();
		if(semester.equals("") && id.equals("") && courseNo.equals("")){
			sections = sectionDao.getAllSection();
		}
		else if(id.equals("") && (!courseNo.equals(""))){
			sections = sectionDao.getSectionByCr(courseNo);
		}
		else if((!id.equals("")) && (courseNo.equals(""))){
			sections = sectionDao.getSectionByPr(id);
		}
		return sections;
	}
	
	public void initializeTheSection(Section section)throws Exception{
		Section sec = sectionDao.getSectionBySectionNo(section.getSectionNo());
		section.setDayOfWeek(sec.getDayOfWeek());
		section.setTimeOfDay(sec.getTimeOfDay());
		section.setRoom(sec.getRoom());
		section.setSeatingCapacity(sec.getSeatingCapacity());
		section.setRepresentedCourse(sec.getRepresentedCourse());
		section.setOfferedIn(sec.getOfferedIn());
		section.setInstructor(sec.getInstructor());
		List<Student> students = studentService.getEnrolledStudentBySc(section);
		List<TranscriptEntry> transcriptEntries = transcriptEntryDao.getTranscriptEntryBysectionNo(section.getSectionNo());
		HashMap<String, Student> enrolledStudents = new HashMap<String, Student>();
		for(Student st:students){
			enrolledStudents.put(st.getId(), st);
		}
		section.setEnrolledStudents(enrolledStudents);
		HashMap<String, TranscriptEntry> assignedGrades = new HashMap<String, TranscriptEntry>();
		for(TranscriptEntry te:transcriptEntries){
			assignedGrades.put(te.getStudent().getId(), te);	
		}	
		section.setAssignedGrades(assignedGrades);
	}
	
	public List<Section> getMySections(String id) throws Exception{
		return sectionDao.getMySections(id);
	}
}
