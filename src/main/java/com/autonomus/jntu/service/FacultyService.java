package com.autonomus.jntu.service;

import java.util.List;

import com.autonomus.jntu.model.Faculty;

public interface FacultyService {

	List<Faculty> getAllFacultyDetails() throws Exception;

	Faculty getFacultyDetailsById(int id);

	void insertFacultyDetail(List<Faculty> facultyList) throws Exception;

	List<Faculty> updateFacultyDetail(List<Faculty> facultyList);

	List<Faculty> patchFacultyDetail(int id, String fieldName, String updateField) throws Exception;
	
	int deleteFacultyDetailById(int id) throws Exception;
	
	void deleteAllFacultyDetails() throws Exception;
	
	void deleteMatchFaculty(String matchstring);
	
	List<Faculty> findByFirstNameFaculty(String FirstName);


}
