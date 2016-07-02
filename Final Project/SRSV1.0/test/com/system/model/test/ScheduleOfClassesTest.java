package com.system.model.test;

import java.util.HashMap;

import com.system.model.Section;

public class ScheduleOfClassesTest {
	private String semester;
	private HashMap<String, SectionTest> sectionsOffered;
	
	public ScheduleOfClassesTest(String semester) {
		this.semester = semester;
		sectionsOffered = new HashMap<String, SectionTest>();
	}

	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public HashMap<String, SectionTest> getSectionsOffered() {
		return sectionsOffered;
	}
	
	public void addSection(SectionTest sec1){
		String key = sec1.getRepresentedCourse().getCourseNo() + "-" + sec1.getSectionNo();
		sectionsOffered.put(key, sec1);
		sec1.setOfferedIn(this);
	}
	
	public SectionTest findSection(String fullSectionNo){
		return sectionsOffered.get(fullSectionNo);
	}
	
	public boolean isEmpty(){
		if(sectionsOffered.size() == 0)return true;
		else return false;
	}

	public void display() {
		System.out.println("Schedule of Classes for " + getSemester());
		System.out.println();
		
		for (SectionTest s : sectionsOffered.values()) {
			s.display();
			System.out.println();
		}
		
	}
}
