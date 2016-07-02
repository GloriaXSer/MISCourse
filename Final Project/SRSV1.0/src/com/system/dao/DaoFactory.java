package com.system.dao;

public class DaoFactory {
	private static String daoName = "daoImpl.sqlite";
//	private static String daoName = "daoImpl.mock";
	
	public static ScheduleOfClassesDao createScheduleOfClassesDao() {
		ScheduleOfClassesDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ScheduleOfClassesDao" + "Impl").newInstance();
			result = (ScheduleOfClassesDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static CourseDao createCourseDao() {
		CourseDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "CourseDao" + "Impl").newInstance();
			result = (CourseDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static SectionDao createSectioneDao() {
		SectionDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "SectionDao" + "Impl").newInstance();
			result = (SectionDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	public static ProfessorDao createProfessorDao() {
		ProfessorDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ProfessorDao" + "Impl").newInstance();
			result = (ProfessorDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static StudentDao createStudentDao(){
		StudentDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "StudentDao" + "Impl").newInstance();
			result = (StudentDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static TranscriptEntryDao createTranscriptEntryDao(){
		TranscriptEntryDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "TranscriptEntryDao" + "Impl").newInstance();
			result = (TranscriptEntryDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
