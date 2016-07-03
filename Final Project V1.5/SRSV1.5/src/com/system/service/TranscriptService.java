package com.system.service;

import java.util.ArrayList;
import java.util.List;

import com.system.dao.TranscriptEntryDao;
import com.system.dao.impl.TranscriptEntryDaoImpl;
import com.system.model.TranscriptEntry;

public class TranscriptService {

	public List<TranscriptEntry> getStudTranscripts(String studentId, String semester, String courseNo) throws Exception{
		TranscriptEntryDao transcriptEntryDao = new TranscriptEntryDaoImpl();
		List<TranscriptEntry> transcriptEntries = new ArrayList<TranscriptEntry>();
		List<TranscriptEntry> semTranscriptEntries = new ArrayList<TranscriptEntry>();
		
		if(semester.equals("") && courseNo.equals("")){
			transcriptEntries = transcriptEntryDao.getTranscriptEntryBystudentId(studentId);
		}
		else if((!semester.equals("")) && courseNo.equals("")){
			semTranscriptEntries = transcriptEntryDao.getTranscriptEntryBystudentId(studentId);
			for(TranscriptEntry te: semTranscriptEntries){
				if(semester.equals(te.getSection().getOfferedIn().getSemester())){
					transcriptEntries.add(te);
				}
			}			
		}
		else if(semester.equals("") && (!courseNo.equals(""))){
			semTranscriptEntries = transcriptEntryDao.getTranscriptEntryBystudentId(studentId);
			for(TranscriptEntry te: semTranscriptEntries){
				if(courseNo.equals(te.getSection().getRepresentedCourse().getCourseNo())){
					transcriptEntries.add(te);
				}
			}	
		}
		else if((!semester.equals("")) && (!courseNo.equals(""))){
			semTranscriptEntries = transcriptEntryDao.getTranscriptEntryBystudentId(studentId);
			for(TranscriptEntry te: semTranscriptEntries){
				if(semester.equals(te.getSection().getOfferedIn().getSemester())&&
				   courseNo.equals(te.getSection().getRepresentedCourse().getCourseNo())){
					transcriptEntries.add(te);
				}
			}
		}		
		return transcriptEntries;
	}
}
