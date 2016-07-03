package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.system.dao.ScheduleOfClassesDao;
import com.system.dao.StudentDao;
import com.system.dao.TranscriptEntryDao;
import com.system.dao.impl.ScheduleOfClassesDaoImpl;
import com.system.dao.impl.StudentDaoImpl;
import com.system.dao.impl.TranscriptEntryDaoImpl;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.Transcript;
import com.system.model.TranscriptEntry;

public class StudentService {
	ScheduleOfClassesDao scheduleOfClassesDao = new ScheduleOfClassesDaoImpl();
	TranscriptEntryDao transcriptEntryDao = new TranscriptEntryDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	
	public List<Student> getEnrolledStudents(String semester, String courseNo) throws Exception{
		List<Student> students = new ArrayList<Student>();
		List<Section> sections = scheduleOfClassesDao.getSectionBySemester(semester);
		List<TranscriptEntry> tes = new ArrayList<TranscriptEntry>();
		for(Section se:sections){
			if(courseNo.equals(se.getRepresentedCourse().getCourseNo())){
				tes = transcriptEntryDao.getTranscriptEntryBysectionNo(se.getSectionNo());
			}
		}
		for(TranscriptEntry te:tes){
			students.add(te.getStudent());
		}		
		return students;
	}
	
	public List<Student> getEnrolledStudentBySc(Section section) throws Exception{
		List<Student> students = new ArrayList<Student>();
		List<TranscriptEntry> tes = new ArrayList<TranscriptEntry>();
		tes = transcriptEntryDao.getTranscriptEntryBysectionNo(section.getSectionNo());
		for(TranscriptEntry te:tes){
			students.add(te.getStudent());
		}		
		return students;
	}
	
	
	
	public void initializeStudent(Student student) throws Exception{
		Student s = studentDao.getOneStudent(student.getId());
		student.setName(s.getName());
		student.setMajor(s.getMajor());
		student.setDegree(s.getDegree());
		List<Section> attends = new ArrayList<Section>();
		for(TranscriptEntry tr:transcriptEntryDao.getTranscriptEntryBystudentId(student.getId())){
			attends.add(tr.getSection());
		}
		student.setAttends(attends);
		Transcript transcript = new Transcript(student);
		transcript.setTranscriptEntries(transcriptEntryDao.getTranscriptEntryBystudentId(student.getId()));
		student.setTranscript(transcript);
	}

	public void enrollSection(Student student, Section section) throws Exception{
		transcriptEntryDao.addTranscriptEntry(student.getId(), section.getSectionNo());		
	}

	public void dropSection(Student student, Section section) throws Exception{
		transcriptEntryDao.deleteTranscriptEntry(student.getId(), section.getSectionNo());
	}
}
 