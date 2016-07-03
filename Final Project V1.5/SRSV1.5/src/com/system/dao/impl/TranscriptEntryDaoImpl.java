package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.system.dao.SectionDao;
import com.system.dao.StudentDao;
import com.system.dao.TranscriptDao;
import com.system.dao.TranscriptEntryDao;
import com.system.model.Section;
import com.system.model.Student;
import com.system.model.TranscriptEntry;
import com.system.utils.JDBCsqlite;


public class TranscriptEntryDaoImpl implements TranscriptEntryDao {
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	TranscriptEntry transcriptEntry = new TranscriptEntry();
	TranscriptDao transcriptDao = new TranscriptDaoImpl();
	StudentDao studentDao = new StudentDaoImpl();
	SectionDao sectionDao = new SectionDaoImpl();
	/**
	 * 单条成绩记录
	 */
	@Override
	public TranscriptEntry getTranscriptEntryById(String transcriptEntryId) throws Exception {
		String sql="select * from Transcript where transcriptEntryId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, transcriptEntryId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			transcriptEntry.setGrade(rs.getString("grade"));
			transcriptEntry.setStudent(studentDao.getOneStudent(rs.getString("studentId")));
			transcriptEntry.setSection(sectionDao.getSectionBySectionNo(rs.getString("sectionNo")));
			transcriptEntry.setTranscript(transcriptDao.getTranscriptById(rs.getString("transcriptId")));
		}		
		return transcriptEntry;
	}

	@Override
	public List<TranscriptEntry> getTranscriptEntryBystudentId(String studentId) throws Exception {
		List<TranscriptEntry> transcriptEntries = new ArrayList<TranscriptEntry>();
		String sql="select * from Transcript where studentId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, studentId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TranscriptEntry transcriptEntry = new TranscriptEntry();
			transcriptEntry.setGrade(rs.getString("grade"));
			transcriptEntry.setStudent(studentDao.getOneStudent(rs.getString("studentId")));
			transcriptEntry.setSection(sectionDao.getSectionBySectionNo(rs.getString("sectionNo")));
			HashMap<String, TranscriptEntry> assignedGrades = new HashMap<String, TranscriptEntry>();
			List<TranscriptEntry> tes = getTranscriptEntryBysectionNo(rs.getString("sectionNo"));
			for(TranscriptEntry te:tes){
				assignedGrades.put(te.getStudent().getId(), te);	
			}
			transcriptEntry.getSection().setAssignedGrades(assignedGrades);
			transcriptEntry.setTranscript(transcriptDao.getTranscriptById(rs.getString("transcriptId")));
			transcriptEntries.add(transcriptEntry);
		}		
		return transcriptEntries;
	}

	@Override
	public List<TranscriptEntry> getTranscriptEntryBysectionNo(String sectionNo) throws Exception {
		List<TranscriptEntry> transcriptEntries = new ArrayList<TranscriptEntry>();
		String sql="select * from Transcript where sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, sectionNo);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			TranscriptEntry transcriptEntry = new TranscriptEntry();
			transcriptEntry.setGrade(rs.getString("grade"));
			transcriptEntry.setStudent(studentDao.getOneStudent(rs.getString("studentId")));
			transcriptEntry.setSection(sectionDao.getSectionBySectionNo(rs.getString("sectionNo")));
			transcriptEntry.setTranscript(transcriptDao.getTranscriptById(rs.getString("transcriptId")));
			transcriptEntries.add(transcriptEntry);
		}		
		return transcriptEntries;
	}

	@Override
	public void addTranscriptEntry(String id, String sectionNo) throws Exception {
		String sql="insert into Transcript(transcriptId, sectionNo, studentId) values(?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, "TG"+id);
		ps.setString(2, sectionNo);
		ps.setString(3, id);
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteTranscriptEntry(String id, String sectionNo) throws Exception {
		String sql="delete from Transcript where grade is null and studentId=? and sectionNo=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, sectionNo);
		ps.executeUpdate();
		ps.close();
	}


}
