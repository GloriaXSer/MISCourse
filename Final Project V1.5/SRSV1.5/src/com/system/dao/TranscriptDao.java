package com.system.dao;

import com.system.model.Transcript;
import com.system.model.TranscriptEntry;

public interface TranscriptDao {

	public Transcript getTranscriptById(String studentId) throws Exception;
}
