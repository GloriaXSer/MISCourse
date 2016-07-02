package com.system.dao;

import java.util.List;

import com.system.model.ScheduleOfClasses;
import com.system.model.Section;

public interface ScheduleOfClassesDao {
	
	public List<Section> getSectionBySemester(String semester) throws Exception;
	
	public ScheduleOfClasses getSectionBySectionNo(String sectionNo) throws Exception;
	
}
