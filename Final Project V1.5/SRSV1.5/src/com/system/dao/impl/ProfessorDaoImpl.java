package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.ProfessorDao;
import com.system.dao.SectionDao;
import com.system.model.Professor;
import com.system.utils.JDBCsqlite;

public class ProfessorDaoImpl implements ProfessorDao {
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	
	@Override
	public Professor getProfessorById(String id) throws Exception{
		Professor professor = new Professor();
		SectionDao sectionDao = new SectionDaoImpl();
		String sql = "select * from Person where id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);  
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			professor.setId(id);
			professor.setName(rs.getString("name"));
			professor.setTitle(rs.getString("title"));
			professor.setDepartment(rs.getString("department"));
			professor.setTeaches(sectionDao.getTeaches(id));
		}
		return professor;
	}
	
	@Override
	public Professor getProfessorByName(String name) throws Exception{
		Professor professor = new Professor();
		String sql = "select * from Person where type='professor' and name=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, name);  
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			professor.setId(rs.getString("id"));
			professor.setName(rs.getString("name"));
			professor.setTitle(rs.getString("title"));
			professor.setDepartment(rs.getString("department"));
		}
		return professor;
	}
	
	@Override
	public void addProfessor(Professor professor) throws Exception {
		String sql = "insert into Person(id,name,title,department,type) values(?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, professor.getId());
		ps.setString(2, professor.getName());
		ps.setString(3, professor.getTitle());
		ps.setString(4, professor.getDepartment());
		ps.setString(5, "professor");
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void updateProfessor(Professor professor, String oldid) throws Exception {
		String sql = "update Person set id=?, name=?, title=?, department=? where type='professor' and id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, professor.getId());
		ps.setString(2, professor.getName());
		ps.setString(3, professor.getTitle());
		ps.setString(4, professor.getDepartment());
		ps.setString(5, oldid);
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void deleteProfessor(String id) throws Exception {
		String sql="delete from Person where id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public List<Professor> getAll() throws Exception {
		List<Professor> professors = new ArrayList<Professor>();
		String sql = "select * from Person where type='professor'";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Professor professor = new Professor();
			professor.setId(rs.getString("id"));
			professor.setName(rs.getString("name"));
			professor.setTitle(rs.getString("title"));
			professor.setDepartment(rs.getString("department"));
			professors.add(professor);
		}
		return professors;
	}

	@Override
	public List<Professor> searchProfessorByDp(String department) throws Exception {
		List<Professor> professors = new ArrayList<Professor>();
		String sql="select * from Person where type='professor' and department=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, department);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Professor professor = new Professor(rs.getString("name"), 
												rs.getString("id"), 
												rs.getString("title"), 
												rs.getString("department"));
			professors.add(professor);
		}		
		return professors;
	}

	@Override
	public List<Professor> getProfessor(String id) throws Exception {
		List<Professor> professors = new ArrayList<Professor>();
		String sql = "select * from Person where type='professor' and id=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);  
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Professor professor = new Professor();
			professor.setId(id);
			professor.setName(rs.getString("name"));
			professor.setTitle(rs.getString("title"));
			professor.setDepartment(rs.getString("department"));
			professors.add(professor);
		}
		return professors;
	}

}
