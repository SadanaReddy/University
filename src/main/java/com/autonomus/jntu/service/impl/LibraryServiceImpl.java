package com.autonomus.jntu.service.impl;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonomus.jntu.controllers.LibraryController;
import com.autonomus.jntu.model.Library;
import com.autonomus.jntu.repository.LibraryRepository;
import com.autonomus.jntu.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);


	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public List<Library> getAllBookDetails() {
		LOGGER.info("The Request came into getAllBookDetails method ");
		try {
			List<Library> libraryList = libraryRepository.findAll();
			LOGGER.info("save the reteive the values to libraryList");
			if(libraryList.size()==0) {
				LOGGER.error("The LibraryList is Empty");
				throw new EmptyStackException();
			}
			return libraryList;
		}catch(Exception e) {
			throw(e);
		}
	}

	@Override
	public Library retriveBookDetailsId(int id) {
		LOGGER.info("The Request came into retriveBookDetailsId method ");
		try {
			LOGGER.info("try to match the given id and database id ");
			Library library = libraryRepository.findById(id);
			if(library==null) {
				return null;
			}
		}catch(Exception e) {
			throw(e);
		}
		return null;

	}

	@Override
	public void deleteAllBooksDetails() {
		LOGGER.info("The request came into deleteAllBooksDetails method");
		try {
			int deleteRows = libraryRepository.deleteAll();
			LOGGER.info("The List is clear");
			if(deleteRows==0) {
				LOGGER.info("There is no data to delete");
				throw new EmptyStackException();
			}
			else if(deleteRows>0){
				LOGGER.info("Delete {} number of records", + deleteRows);
			}
		}catch(Exception e) {
			throw(e);
		}
	}

	@Override
	public void createbookDetails(List<Library> libraryList) {
		LOGGER.info("The request came into createbookDetails method");
		try {
			libraryRepository.save(libraryList);

		}catch(ArrayIndexOutOfBoundsException e) {
			throw(e);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Library> deleteBookDetailsById(int id) {
		LOGGER.info("The request came into deleteBookDetailsById method");
		try {
			int numRows = libraryRepository.deleteById(id);
			if(numRows==id) {
				LOGGER.info("the id is:{}",+id);
			}else if(numRows>0) {
				LOGGER.info("number of {} rows deleted", +numRows);}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Library> updateLibraryBooksDetails(List<Library> libraryList ) {
		LOGGER.info("The request came into updateLibraryBooksDetails method");
		try {
			for(int i =0;i<libraryList.size();i++) {
				Library library = libraryList.get(i);
				Library dataFromDB = libraryRepository.findById(library.getBookId());
				if(dataFromDB!=null) {
					List<Library> newLibraryList = new ArrayList<>();
					newLibraryList.add(library);
					libraryRepository.update(newLibraryList);
					LOGGER.info("The LibraryList is updated");

				}else if(dataFromDB==null){
					List<Library> newLibraryList = new ArrayList<>();
					newLibraryList.add(library);
					libraryRepository.save(newLibraryList);;
					LOGGER.info("The LibraryList is inserted");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return libraryList;
	}

	@Override
	public List<Library> findByBookName(String bookName) {
		LOGGER.info("The request came into findByBookName method");
		try {
			LOGGER.info("Retrieving the book details for {} book name ", bookName);
			List<Library> findBookNames = libraryRepository.findByMatchingBookName(bookName);
			LOGGER.info("Number of books retrieved for {} book is: {}",bookName,findBookNames);

			return findBookNames;

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Library> findMatchLibraryBookDetails(String matchstring) {
		LOGGER.info("The request came into findMatchLibraryBookDetails method");
		try {
		List<Library>	findMatchString =  libraryRepository.findByPartialBookDetails(matchstring);
		if(findMatchString!=null) {
			for(int i=0;i<findMatchString.size();i++) {
				libraryRepository.deleteById(findMatchString.get(i).getBookId());
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Library> patchFacultyDetail(int id, String fieldName, String updateField) throws Exception {
		LOGGER.info("The request came into patchFacultyDetail method");
		try {
			libraryRepository.updateSpecificFieldById(id, fieldName, updateField);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}




}











