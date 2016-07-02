package com.system.dao;

import java.util.List;

import com.system.model.Professor;
import com.system.model.Section;

public interface ProfessorDao {
	
	public Professor getProfessorById(String id) throws Exception;
	
	public Professor getProfessorByName(String name) throws Exception;
	
	public List<Professor> getProfessor(String id) throws Exception;
	
	public List<Professor> searchProfessorByDp(String department) throws Exception;
	
	public void addProfessor(Professor professor) throws Exception;
	
	public void deleteProfessor(String id) throws Exception;
	
	public void updateProfessor(Professor professor, String oldid) throws Exception;
	
	public List<Professor> getAll() throws Exception;
}
