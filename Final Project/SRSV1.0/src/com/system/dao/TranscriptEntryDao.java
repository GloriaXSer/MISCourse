package com.system.dao;

import java.util.List;

import com.system.model.Section;
import com.system.model.Student;
import com.system.model.TranscriptEntry;

public interface TranscriptEntryDao {

	public TranscriptEntry getTranscriptEntryById(String transcriptEntryId) throws Exception;
	
	public List<TranscriptEntry> getTranscriptEntryBystudentId(String studentId) throws Exception;
	
	public List<TranscriptEntry> getTranscriptEntryBysectionNo(String sectionNo) throws Exception;
	
	public void addTranscriptEntry(String id, String sectionNo) throws Exception;
}
