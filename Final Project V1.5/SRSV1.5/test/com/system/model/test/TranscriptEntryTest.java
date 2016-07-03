package com.system.model.test;

import com.system.model.Section;
import com.system.model.Student;
import com.system.model.Transcript;

public class TranscriptEntryTest {
	private String grade;
	private StudentTest student;
	private SectionTest section;
	private TranscriptTest transcript;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public StudentTest getStudent() {
		return student;
	}
	public void setStudent(StudentTest s) {
		this.student = s;
	}
	public SectionTest getSection() {
		return section;
	}

	public void setSection(SectionTest sectionTest) {
		this.section = sectionTest;
	}

	public TranscriptTest getTranscript() {
		return transcript;
	}
	public void setTranscript(TranscriptTest transcript) {
		this.transcript = transcript;
	}
	
	public TranscriptEntryTest(StudentTest s, String grade, SectionTest sectionTest) {
		this.setStudent(s);
		this.setSection(sectionTest);
		this.setGrade(grade);
		TranscriptTest transcript = s.getTranscript();
		this.setTranscript(transcript);
		transcript.addTranscriptEntry(this);
	}

	public TranscriptEntryTest() {

	}
	public static boolean validateGrade(String grade) {
		boolean outcome = false;
		
		if(grade.equals("F")||grade.equals("I")){
			outcome = true;
		}
		
		if(grade.startsWith("A")||
		   grade.startsWith("B")||
		   grade.startsWith("C")||
		   grade.startsWith("D")){
			if(grade.length() == 1) outcome = true;
			else if(grade.length() == 2){
				if(grade.endsWith("+")||
				   grade.endsWith("-")){
				   outcome = true;
				}
			}
		}			
		return outcome;
	}
	public static boolean passingGrade(String grade){
		boolean outcome = false;
		if(validateGrade(grade)){
			if(grade.startsWith("A")||
			   grade.startsWith("B")||
			   grade.startsWith("C")||
			   grade.startsWith("D")){
			   outcome = true;
			}
		}
		return outcome;
	}

}
