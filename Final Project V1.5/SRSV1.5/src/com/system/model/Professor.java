package com.system.model;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Person;
import com.system.model.Section;

public class Professor extends Person {
	private String title;
	private String department;
	private List<Section> teaches;
	
	//构造方法
	public Professor(String name, String id, String title, String department){
		super(name, id);
		this.setTitle(title);
		this.setDepartment(department);	
		teaches = new ArrayList<Section>();
	}	
	public Professor(){
		
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
	public List<Section> getTeaches() {
		return teaches;
	}
	public void setTeaches(List<Section> list) {
		this.teaches = list;
	}
	
	//Professor方法
	public void agreeToTeach(Section section){
		teaches.add(section);
		section.setInstructor(this);
	}
}
