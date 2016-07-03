package com.system.model;

import java.util.HashMap;

public class Faculty {
	
	private HashMap<String, Professor> professors;

	public HashMap<String, Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(HashMap<String, Professor> professors) {
		this.professors = professors; 
	}
	
	public Faculty(){
		professors = new HashMap<String, Professor>();
	}
	
	public void addProfessor(Professor p){
		professors.put(p.getId(), p);
	}
	
	public Professor findProfessor(String id){
		return professors.get(id);
	}
	
	public boolean isEmpty(){
		if(professors.size() == 0) return true;
		else return false;
	}
}
