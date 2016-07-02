package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.CourseDao;
import com.system.dao.ProfessorDao;
import com.system.dao.ScheduleOfClassesDao;
import com.system.dao.SectionDao;
import com.system.model.Course;
import com.system.model.Section;
import com.system.utils.JDBCsqlite;

public class SectionDaoImpl implements SectionDao {
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	public CourseDao courseDao = new CourseDaoImpl();
	public ProfessorDao professerDao = new ProfessorDaoImpl();
	@Override
	public void addSection(Section section) throws Exception{
		String sql="insert into Section values(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, section.getSectionNo());
		ps.setString(2, section.getDayOfWeek());
		ps.setString(3, section.getTimeOfDay());
		ps.setString(4, section.getRoom());
		ps.setInt(5, section.getSeatingCapacity());
		ps.setString(6, courseDao.getCourseByCourseName(section.getCourseName()).getCourseNo());
		ps.setString(7, professerDao.getProfessorByName(section.getProfessorName()).getId());
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void deleteSection(String sectionNo) throws Exception {
		String sql="delete from Section where sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, sectionNo);
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void updateSection(Section section, String oldNo) throws Exception {
		String sql="update Section set sectionNo=?, dayOfWeek=?, timeOfDay=?, room=?, seatingCapacity=?, courseNo=?, professorId=? where sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, section.getSectionNo());
		ps.setString(2, section.getDayOfWeek());
		ps.setString(3, section.getTimeOfDay());
		ps.setString(4, section.getRoom());
		ps.setInt(5, section.getSeatingCapacity());
		ps.setString(6, courseDao.getCourseByCourseName(section.getCourseName()).getCourseNo());
		ps.setString(7, professerDao.getProfessorByName(section.getProfessorName()).getId());
		ps.setString(8, oldNo);
		ps.executeUpdate();
		ps.close();		
	}
	
	@Override
	public Section getSectionBySectionNo(String sectionNo) throws Exception {
		Section section = new Section();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		ScheduleOfClassesDao scheduleOfClassesDao = new ScheduleOfClassesDaoImpl();
		String sql = "select * from Section where sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, sectionNo);
		ResultSet rs = ps.executeQuery();
		Course course = new Course();
		while (rs.next()) {
			section.setSectionNo(sectionNo);
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			course = courseDao.getCourseByCourseNo(rs.getString("courseNo"));
			course.setPrerequisites(courseDao.getPrerequisites(course.getCourseNo()));
			section.setRepresentedCourse(course);			
			section.setOfferedIn(scheduleOfClassesDao.getSectionBySectionNo(rs.getString("sectionNo")));
			section.setInstructor(professorDao.getProfessorById(rs.getString("professorId")));
		}
		return section;
	}	
	
	@Override
	public List<Section> getOfferedAsSection(String courseNo) throws Exception {
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		String sql = "select * from Section where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseNo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setRepresentedCourse(courseDao.getCourseByCourseNo(rs.getString("courseNo")));
			section.setInstructor(professorDao.getProfessorById(rs.getString("professorId")));
			sections.add(section);
			
		}
		return sections;
	}
	
	@Override
	public List<Section> getTeaches(String professorId) throws Exception {
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		String sql = "select * from Section where professorId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, professorId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setRepresentedCourse(courseDao.getCourseByCourseNo(rs.getString("courseNo")));
			sections.add(section);			
		}
		return sections;
	}
	
	@Override
	public List<Section> getAll() throws Exception{
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		String sql="select * from Section";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setRepresentedCourse(courseDao.getCourseByCourseNo(rs.getString("courseNo")));
			section.setInstructor(professorDao.getProfessorById(rs.getString("professorId")));
			sections.add(section);
		}
		return sections;
	}

	@Override
	public List<Section> getAllSection() throws Exception {
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		String sql="select * from Section";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setCourseName(courseDao.getCourseByCourseNo(rs.getString("courseNo")).getCourseName());
			section.setProfessorName(professorDao.getProfessorById(rs.getString("professorId")).getName());
			sections.add(section);
		}
		return sections;
	}

	@Override
	public List<Section> getSectionByCr(String courseNo) throws Exception {
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		String sql="select * from Section where courseNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, courseNo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setCourseName(courseDao.getCourseByCourseNo(courseNo).getCourseName());
			section.setProfessorName(professorDao.getProfessorById(rs.getString("professorId")).getName());
			sections.add(section);
		}
		return sections;
	}

	@Override
	public List<Section> getSectionByPr(String professorId) throws Exception {
		List<Section> sections = new ArrayList<Section>();
		CourseDao courseDao = new CourseDaoImpl();
		ProfessorDao professorDao = new ProfessorDaoImpl();
		String sql="select * from Section where professorId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, professorId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = new Section();
			section.setSectionNo(rs.getString("sectionNo"));
			section.setDayOfWeek(rs.getString("dayOfWeek"));
			section.setTimeOfDay(rs.getString("timeOfDay"));
			section.setRoom(rs.getString("room"));
			section.setSeatingCapacity(rs.getInt("seatingCapacity"));
			section.setCourseName(courseDao.getCourseByCourseNo(rs.getString("courseNo")).getCourseName());
			section.setProfessorName(professorDao.getProfessorById(professorId).getName());
			sections.add(section);
		}
		return sections;
	}

	@Override
	public List<Section> getMySections(String studentId) throws Exception {
		List<Section> sections = new ArrayList<Section>();
		String sql="select * from Transcript where (grade is null) and studentId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, studentId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Section section = getSectionBySectionNo(rs.getString("sectionNo"));
			section.setCourseName(section.getRepresentedCourse().getCourseName());
			section.setProfessorName(section.getInstructor().getName());
			sections.add(section);
		}
		return sections;
	}


}
