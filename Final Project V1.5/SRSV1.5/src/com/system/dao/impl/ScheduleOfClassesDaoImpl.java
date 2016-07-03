package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.ScheduleOfClassesDao;
import com.system.dao.SectionDao;
import com.system.model.ScheduleOfClasses;
import com.system.model.Section;
import com.system.utils.JDBCsqlite;

public class ScheduleOfClassesDaoImpl implements ScheduleOfClassesDao {
	
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	
	@Override
	public List<Section> getSectionBySemester(String semester) throws Exception {
		SectionDao sectiondao = new SectionDaoImpl();
		List<Section> sections = new ArrayList<Section>();
		String sql="select * from ScheduleOfClasses where semester=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, semester);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Section section = new Section();
			section = sectiondao.getSectionBySectionNo(rs.getString("sectionNo"));
			sections.add(section);
		}
		return sections;
	}

	@Override
	public ScheduleOfClasses getSectionBySectionNo(String sectionNo) throws Exception {
		ScheduleOfClasses soc = new ScheduleOfClasses();
		String sql="select * from ScheduleOfClasses where sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, sectionNo);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			soc.setSemester(rs.getString("semester"));;
		}
		return soc;
	}

}
