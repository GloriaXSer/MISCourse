package com.system.model;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.TranscriptEntry;

public class Transcript {
	private String transcriptId;
	private Student studentOwner;
	private List<TranscriptEntry> transcriptEntries;

	public String getTranscriptId() {
		return transcriptId;
	}

	public void setTranscriptId(String transcriptId) {
		this.transcriptId = transcriptId;
	}

	public Student getStudentOwner() {
		return studentOwner;
	}

	public void setStudentOwner(Student studentOwner) {
		this.studentOwner = studentOwner;
	}

	public List<TranscriptEntry> getTranscriptEntries() { 
		return transcriptEntries;
	}

	public void setTranscriptEntries(List<TranscriptEntry> transcriptEntries) {
		this.transcriptEntries = transcriptEntries;
	}
	//构造方法
	public Transcript(Student student) {
		this.studentOwner = student;
		transcriptEntries = new ArrayList<TranscriptEntry>();
	}
	public Transcript(){
		
	}
	
	public boolean verifyCompletion(Course representedCourse) {
		boolean outcome = false;
		
		for(TranscriptEntry te: transcriptEntries){
			Section s = te.getSection();
			if(s.isSectionOf(representedCourse)){
				if(TranscriptEntry.passingGrade(te.getGrade())){
					outcome = true;
					break;
				}
			}
		}
		return outcome;
	}

	public void addTranscriptEntry(TranscriptEntry transcriptEntry) {
		transcriptEntries.add(transcriptEntry);
	}
}
