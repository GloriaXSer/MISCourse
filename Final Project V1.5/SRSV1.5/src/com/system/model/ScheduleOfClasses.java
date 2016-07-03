package com.system.model;

import java.util.HashMap;

import com.system.model.Section;

public class ScheduleOfClasses {
	private String semester;
	private HashMap<String, Section> sectionsOffered;
	
	public ScheduleOfClasses(String semester) {
		this.semester = semester;
		sectionsOffered = new HashMap<String, Section>();
	}
	
	public ScheduleOfClasses(){}

	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}
	
	public void addSection(Section section){
//		String key = section.getRepresentedCourse().getCourseNo() + "-" + section.getSectionNo();
		sectionsOffered.put(section.getSectionNo(), section);
		section.setOfferedIn(this);
	}
	
	public Section findSection(String fullSectionNo){
		return sectionsOffered.get(fullSectionNo);
	}
	
//	public boolean isEmpty(){
//		if(sectionsOffered.size() == 0)return true;
//		else return false;
//	}

}
