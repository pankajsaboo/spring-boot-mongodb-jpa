package com.templateproject.repository;


public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws Exception;
	
}
