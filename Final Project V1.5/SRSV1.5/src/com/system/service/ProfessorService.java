package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.system.dao.ProfessorDao;
import com.system.dao.SectionDao;
import com.system.dao.impl.ProfessorDaoImpl;
import com.system.dao.impl.SectionDaoImpl;
import com.system.model.Faculty;
import com.system.model.Professor;
import com.system.model.Section;

public class ProfessorService {	
	
	ProfessorDao professorDao = new ProfessorDaoImpl();
	
	public void initializeProfessors(Faculty faculty) throws Exception{
		List<Professor> professors = professorDao.getAll();
		for(Professor p:professors){			
			faculty.addProfessor(p);
		}
	}
	
	public void deleteProfessor(String id) throws Exception{
		SectionDao sectionDao = new SectionDaoImpl();
		for(Section section:professorDao.getProfessorById(id).getTeaches()){
			sectionDao.deleteSection(section.getSectionNo());
		}
		professorDao.deleteProfessor(id);	
	}
	
	public void addProfessor(Professor p) throws Exception{
		professorDao.addProfessor(p);
	}
	
	public void updateProfessor(Professor p, String oldid) throws Exception{
		professorDao.updateProfessor(p, oldid);
	}
	
	public List<Professor> search(String id, String department) throws Exception{
		List<Professor> professors = new ArrayList<Professor>();
		if(id.equals("") && department.equals("")){
			professors = professorDao.getAll();
		}
		else if(id.equals("") && (!department.equals(""))){
			professors = professorDao.searchProfessorByDp(department);
		}
		else if(!id.equals(null)){
			professors = professorDao.getProfessor(id);
		}
		return professors;
	}
	
	public List<Professor> getAll() throws Exception{
		return professorDao.getAll();
	}
	
}
