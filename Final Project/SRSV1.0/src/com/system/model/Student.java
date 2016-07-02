package com.system.model;

import java.util.ArrayList;
import java.util.List;

import com.system.model.Course;
import com.system.model.Person;
import com.system.model.Section;
import com.system.model.Transcript;


public class Student extends Person {
	private String major;
	private String degree;
	private String password;
	private Transcript transcript;
	private List<Section> attends;
	//构造方法
	public Student(String name, String id, String major, String degree){
		super(name,id);
		this.setMajor(major);
		this.setDegree(degree);
		this.setTranscript(new Transcript(this));
		attends = new ArrayList<Section>();
	}
	public Student() {
		
	}
	public Student(String id) {
		super(id);
	}
	//属性设置
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public List<Section> getAttends() {
		return attends;
	}

	public void setAttends(List<Section> attends) {
		this.attends = attends;
	}
	
	//主要业务逻辑
	public void addSection(Section section){
		attends.add(section);
	}
	public void dropSection(Section section){
		attends.remove(section);
	}
	public boolean isEnrolledIn(Section section){
		if (attends.contains(section)) return true;
		else return false;
	}
	
	public boolean isCurrentEnrolledSimilar(Section s1){
		boolean foundMatch = false;
		
		Course c1= s1.getRepresentedCourse();
		for (Section s2: attends){
			Course c2 = s2.getRepresentedCourse();
			if(c1.getCourseNo().equals(c2.getCourseNo())){
				if(s2.getGrade(this) == null ){
					foundMatch = true;
					break;
				}
			}
		}
		return foundMatch;
	}
	public List<Section> getEnrolledSections(){
		return attends;
	}
}
