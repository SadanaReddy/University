package com.autonomus.jntu.repository.impl;

import java.sql.Types;
import java.util.EmptyStackException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Repository;

import com.autonomus.jntu.controllers.LibraryController;
import com.autonomus.jntu.model.Library;
import com.autonomus.jntu.repository.LibraryRepository;

@Repository

public class LibraryRepositoryImpl implements LibraryRepository{
	final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public List<Library> retriveAllBooksDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Library> findAll() {
		LOGGER.info("The request came into findAll method ");
		try {
			LOGGER.info("take the Library details from the database");
			return jdbcTemplate.query("SELECT * from LIBRARY_TABLE", BeanPropertyRowMapper.newInstance(Library.class));
		}catch(EmptyStackException e) {
			LOGGER.info("no data to take from database");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Library findById(int id) {
		LOGGER.info("The request came into findById method ");
		//List<Library>  libraryList = new ArrayList<>();
		Library library = null;
		try {
			LOGGER.info("the given id is taken from the databse ");
			library = jdbcTemplate.queryForObject("SELECT * FROM LIBRARY_TABLE WHERE BOOK_ID=?",
					BeanPropertyRowMapper.newInstance(Library.class), id);

		}catch (EmptyResultDataAccessException e) {
			return null;
		}
		return library;

	}


	@Override
	public int deleteAll() {
		LOGGER.info("The request came into deleteAll method ");
		try {
			LOGGER.info("Recieve the all details from database to delete ");
			return jdbcTemplate.update("DELETE from LIBRARY_TABLE");
		}catch(EmptyStackException e) {
			LOGGER.info("The list is already empty ");
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public void save(List<Library> libraryList) {
		LOGGER.info("The request came into save method ");
		try {
			for(int i=0; i<libraryList.size(); i++) {
				Library library = libraryList.get(i);
				LOGGER.info("insert the book details to database ");
				//jdbcTemplate.update("INSERT INTO LIBRARY_TABLE (BOOK_ID, BOOK_NAME, BOOK_AUTHOR,DEPT_BOOK,AVALIABLE) VALUES(?,?,?,?,?)",
				//		new Object[] { library.getBookId(), library.getBookName(), library.getBookAuthor(),library.getDeptBook(),library.isAvaliable() });
				final String INSERT_QUERY = "insert into LIBRARY_TABLE (BOOK_ID, BOOK_NAME,BOOK_AUTHOR,DEPT_BOOK,AVALIABLE) values (?, ?,?,?,?)";
				LOGGER.info("insert a new faculty details");
				LOGGER.info("updateSpecificFieldById query: {}", INSERT_QUERY);

				jdbcTemplate.update(INSERT_QUERY, library.getBookId(), library.getBookName(), library.getBookAuthor(), library.getDeptBook(), library.isAvaliable());	
				//LOGGER.info("The insert Query is:" INSERT_QUERY);

			}
		} catch(InsufficientAuthenticationException e) {
			LOGGER.info("The arraySize exception");
			e.printStackTrace();
		}
	}


	@Override
	public int deleteById(int bookId) {
		LOGGER.info("The request came into deleteById method ");
		try {
			LOGGER.info("The Given id is take from database ");
			LOGGER.info("The Given bookId is: {}", +bookId);
			return jdbcTemplate.update("DELETE FROM LIBRARY_TABLE WHERE BOOK_ID=?", bookId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bookId;
	}


	@Override
	public List<Library> update(List<Library> libraryList ) {
		LOGGER.info("The request came into update method ");
		try {
			for(int i =0;i<libraryList.size();i++) {
				Library library =libraryList.get(i);
			LOGGER.info("The update Query is {}: + Library");
			 jdbcTemplate.update("UPDATE LIBRARY_TABLE SET BOOK_NAME =?, BOOK_AUTHOR=?, DEPT_BOOK=?, AVALIABLE=? WHERE BOOK_ID = ?",
					new Object[] {library.getBookName(), library.getBookAuthor(), library.getDeptBook(), library.isAvaliable(),library.getBookId()});
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return libraryList;
	}

	@Override
	public int updateBookNamebasedOnID(int bookId, String bookName) {
		LOGGER.info("The request came into updateBookNamebasedOnID method ");
		try {
			String updateSql = "UPDATE LIBRARY_TABLE SET bookName = ? WHERE bookId = ?";
			Object[] params = { bookName, bookId};
			int[] types = {Types.VARCHAR, Types.BIGINT};
			int library =  jdbcTemplate.update(updateSql, params, types);
			return library;
		}catch(Exception e) {
			e.printStackTrace();
	
	}
		return bookId;
	}

	@Override
	public List<Library> findByMatchingBookName(String bookName) {
		LOGGER.info("The request came into findByBOOKNameContaining");
		try {
			String q = "SELECT * from LIBRARY_TABLE WHERE Book_Name LIKE '%" + bookName + "%'";
			LOGGER.info("the bookName contains is{}:",q);
			return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Library.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Library> findByPartialBookDetails(String matchString) {
		LOGGER.info("The request came into findByPartialBookDetails");
		try {
			String q = "SELECT * from LIBRARY_TABLE WHERE BOOK_AUTHOR LIKE '%" + matchString + "%'";
			LOGGER.info("The gieven matching book list is: {}",q);
			return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Library.class));
		}catch(Exception e) {
			e.printStackTrace();
		}		return null;
	}

	@Override
	public int updateSpecificFieldById(int id, String fieldName, String updateField) {
		LOGGER.info("The request came into updateSpecificFieldById");
		try {
			String updateSql = "UPDATE LIBRARY_TABLE SET "+ fieldName+ "='"+updateField+"' WHERE BOOK_ID =?";
			LOGGER.info("updateSpecificFieldById query: {}", updateSql);
			int[] types = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
			Object[] params = { fieldName, updateField,id};
			return jdbcTemplate.update(updateSql, params, types);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;	
	}
}
	