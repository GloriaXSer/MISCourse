package com.system.model.test;

import java.util.ArrayList;

import com.system.model.Person;
import com.system.model.Section;

public class ProfessorTest extends PersonTest {
	private String title;
	private String department;
	private ArrayList<SectionTest> teaches;
	
	//构造方法
	public ProfessorTest(String name, String id, String title, String department){
		super(name, id);
		this.setTitle(title);
		this.setDepartment(department);	
		teaches = new ArrayList<SectionTest>();
	}	
	public ProfessorTest(){
		
	}
	
	//属性初始化
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ArrayList<SectionTest> getTeaches() {
		return teaches;
	}
	public void setTeaches(ArrayList<SectionTest> teaches) {
		this.teaches = teaches;
	}
	
	//Professor方法
	public void agreeToTeach(SectionTest sec2){
		teaches.add(sec2);
		sec2.setInstructor(this);
	}
	public void displayTeachingAssignments() {
		System.out.println("Teaching Assignments for " + getName() + ":");
		
		if (teaches.size() == 0) {
			System.out.println("\t(none)");
		}
		else for (SectionTest s : teaches) {
			System.out.println("\tCourse No.:  " +
				s.getRepresentedCourse().getCourseNo());
			System.out.println("\tSection No.:  " + 
				s.getSectionNo());
			System.out.println("\tCourse Name:  " +
				s.getRepresentedCourse().getCourseName());
			System.out.println("\tDay and Time:  " +
				s.getDayOfWeek() + " - " + 
				s.getTimeOfDay());
			System.out.println("\t-----");
		}
	}
	
	public void display(){
		super.display();
		
		System.out.println("Professor-Specific Information:");
		System.out.println("\tTitle:  " + getTitle());
		System.out.println("\tTeaches for Dept.:  " + getDepartment());
		displayTeachingAssignments();
		
		System.out.println();
	}
	@Override
	public String toString() {
		return this.getName() + "(" + this.getId() + ")["  + this.getTitle() + "-" + this.getDepartment() + "]";
	}
	
}
