package com.autonomus.jntu.service;

import java.util.List;

import com.autonomus.jntu.model.Library;

public interface LibraryService {
	List<Library> getAllBookDetails() ;
	Library retriveBookDetailsId(int id);
	void deleteAllBooksDetails() ;
	void createbookDetails(List<Library> libraryList);
	List<Library> deleteBookDetailsById(int id);
	List<Library> updateLibraryBooksDetails(List<Library> libraryList);
	List<Library> findMatchLibraryBookDetails(String matchstring);
	List<Library> patchFacultyDetail(int id, String fieldName, String updateField) throws Exception;
	List<Library> findByBookName(String bookName);















}
