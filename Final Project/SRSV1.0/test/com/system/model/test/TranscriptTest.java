package com.system.model.test;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.ScheduleOfClasses;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.test.TranscriptEntryTest;

public class TranscriptTest {
	private String transcriptId;
	private StudentTest studentOwner;
	private List<TranscriptEntryTest> transcriptEntries;

	public String getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(String transcriptId) {
		this.transcriptId = transcriptId;
	}

	public StudentTest getStudentOwner() {
		return studentOwner;
	}

	public void setStudentOwner(StudentTest studentOwner) {
		this.studentOwner = studentOwner;
	}

	public List<TranscriptEntryTest> getTranscriptEntries() { 
		return transcriptEntries;
	}

	public void setTranscriptEntries(List<TranscriptEntryTest> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}
	//构造方法
	public TranscriptTest(StudentTest studentTest) {
		this.studentOwner = studentTest;
		transcriptEntries = new ArrayList<TranscriptEntryTest>();
	}
	public TranscriptTest(){
		
	}
	
	public boolean verifyCompletion(CourseTest representedCourse) {
		boolean outcome = false;
		
		for(TranscriptEntryTest te: transcriptEntries){
			SectionTest s = te.getSection();
			if(s.isSectionOf(representedCourse)){
				if(TranscriptEntryTest.passingGrade(te.getGrade())){
					outcome = true;
					break;
				}
			}
		}
		return outcome;
	}

	public void addTranscriptEntry(TranscriptEntryTest transcriptEntry) {
		transcriptEntries.add(transcriptEntry);
	}
	
	public void display() {
		System.out.println("Transcript for:  " +
				   getStudentOwner().toString());

		if (transcriptEntries.size() == 0) {
			System.out.println("\t(no entries)");
		}
		else for (TranscriptEntryTest te : transcriptEntries) {
			SectionTest sec = te.getSection();

			CourseTest c = sec.getRepresentedCourse();

			ScheduleOfClassesTest soc = sec.getOfferedIn();

			System.out.println("\tSemester:        " +
					   soc.getSemester());
			System.out.println("\tCourse No.:      " +
					   c.getCourseNo());
			System.out.println("\tCredits:         " +
					   c.getCredits());
			System.out.println("\tGrade Received:  " +
					   te.getGrade());
			System.out.println("\t-----");
		}
	}
}
