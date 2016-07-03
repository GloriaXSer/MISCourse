package com.system.model;

import com.system.model.Section;
import com.system.model.Student;
import com.system.model.Transcript;

public class TranscriptEntry {
	private String grade;
	private Student student;
	private Section section;
	private Transcript transcript;
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Transcript getTranscript() {
		return transcript;
	}
	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	
	public TranscriptEntry(Student student, String grade, Section section) {
		this.setStudent(student);
		this.setSection(section);
		this.setGrade(grade);
		Transcript transcript = student.getTranscript();
		this.setTranscript(transcript);
		transcript.addTranscriptEntry(this);
	}

	public TranscriptEntry() {

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
