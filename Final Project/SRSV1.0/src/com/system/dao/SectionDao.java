package com.system.dao;

import java.util.List;

import com.system.model.Section;

public interface SectionDao {

	public void addSection(Section section) throws Exception;
	
	public void deleteSection(String sectionNo) throws Exception;
	
	public void updateSection(Section section, String oldNo) throws Exception;
	
	public Section getSectionBySectionNo(String sectionNo) throws Exception;
	
	public List<Section> getOfferedAsSection(String courseNo) throws Exception;
	
	public List<Section> getTeaches(String professorId) throws Exception;
	
	public List<Section> getSectionByCr(String courseNo) throws Exception;
	
	public List<Section> getSectionByPr(String professorId) throws Exception;
	
	public List<Section> getMySections(String studentId) throws Exception;
	
	List<Section> getAllSection() throws Exception;
	
	List<Section> getAll() throws Exception;
}
