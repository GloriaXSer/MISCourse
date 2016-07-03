package com.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.system.dao.StudentDao;
import com.system.dao.TranscriptDao;
import com.system.dao.TranscriptEntryDao;
import com.system.model.Transcript;
import com.system.utils.JDBCsqlite;

public class TranscriptDaoImpl implements TranscriptDao {
	public Connection conn = JDBCsqlite.createConn();
	public PreparedStatement ps = null;
	
	@Override
	public Transcript getTranscriptById(String studentId) throws Exception {
		//TODO
		Transcript transcript = new Transcript();
		StudentDao studentDao = new StudentDaoImpl();
		TranscriptEntryDao transcriptEntryDao = new TranscriptEntryDaoImpl();
		String sql="select * from Transcript where studentId=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, studentId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			transcript.setTranscriptId(rs.getString("transcriptId"));
		}
		transcript.setStudentOwner(studentDao.getOneStudent(studentId));
		transcript.setTranscriptEntries(transcriptEntryDao.getTranscriptEntryBystudentId(studentId));;
		return transcript;
	}

}
