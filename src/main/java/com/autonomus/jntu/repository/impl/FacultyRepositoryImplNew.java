
package com.autonomus.jntu.repository.impl;

import java.sql.Types;
import java.util.InputMismatchException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ranges.RangeException;

import com.autonomus.jntu.model.Faculty;
import com.autonomus.jntu.repository.FacultyRepository;
import com.autonomus.jntu.service.FacultyService;

@Repository
public class FacultyRepositoryImplNew implements FacultyRepository {

	final Logger LOG  = LoggerFactory.getLogger(FacultyService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Faculty> retriveAllDetails() {
		LOG.info("The request came into retriveAllDetails");
		return null;
	}


	@Override
	public List<Faculty> findAll() {
		LOG.info("The request came into findAll method");
		try {
			List<Faculty> facultyList = jdbcTemplate.query("SELECT * from faculty_table ", 
					BeanPropertyRowMapper.newInstance(Faculty.class));
			return facultyList;
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Faculty retriveAllFacultyDetailsById(int facultyId) {
		LOG.info("The request came into retriveAllFacultyDetailsById");
		Faculty faculty = null;
		try {
			LOG.info("The search the id faculty details from the database");

			faculty = jdbcTemplate.queryForObject("SELECT * FROM faculty_table where id = ? ",
					new Object[] { facultyId }, new BeanPropertyRowMapper<Faculty>(Faculty.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return faculty;
	}
		//List<Faculty> facultyList = new ArrayList<>();
		/*try {
			Faculty faculty = jdbcTemplate.queryForObject("SELECT * FROM faculty_table where id= ?",
					BeanPropertyRowMapper.newInstance(Faculty.class), facultyId);
			LOG.info("retrive the request faculty id Faculty List");
			//facultyList.add(faculty);
			//return (Faculty) facultyList;
			//return faculty;
			if(faculty==null) {
				LOG.info("the faculty id is not in the list");
			}
			return faculty;
		} catch (IncorrectResultSizeDataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}*/
		


	@Override
	public void save(List<Faculty> facultyList) {
		LOG.info("The request came into save");
		try {
			for(int i=0; i<facultyList.size(); i++) {
				Faculty faculty = facultyList.get(i);
				final String INSERT_QUERY = "insert into faculty_table (id, firstName,lastName,dob,teaching,mobileNo) values (?, ?,?,?,?,?)";
				LOG.info("insert a new faculty details");
				jdbcTemplate.update(INSERT_QUERY, faculty.getId(), faculty.getFirstName(),faculty.getLastName(),faculty.getDob(),faculty.isTeaching(),faculty.getMobileNo());	
			}
		} catch(InsufficientAuthenticationException e) {
			LOG.info("The arraySize exception");
			e.printStackTrace();
		}
	}

	@Override
	public List<Faculty> update(List<Faculty> facultyList) {
		LOG.info("The request came into update");
		try {
			for(int i=0;i<facultyList.size();i++) {
				Faculty faculty = facultyList.get(i);
				jdbcTemplate.update("UPDATE faculty_table SET firstName=?, lastName=?,dob=?,teaching=?,mobileNo=? WHERE id=?",
						new Object[] {faculty.getFirstName(), faculty.getLastName(), faculty.getDob(), faculty.isTeaching(), faculty.getMobileNo(),faculty.getId()});
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return facultyList;
	}


	@Override
	public int deleteById(int id) {
		LOG.info("The request came into deleteById");
		try {
			LOG.info("The Given id is match to the list");
			return jdbcTemplate.update("DELETE FROM faculty_table WHERE id=?", id);
		}catch(InputMismatchException e) {
			LOG.error("The given input is mismatch");
			e.printStackTrace();
		}
		return id;
	}
	

	@Override
	public int deleteAll() {
		LOG.info("The request came into deleteAll");
		try {
			LOG.info("The given table data is deleted");
			return jdbcTemplate.update("DELETE from faculty_table");
		}catch(RangeException e) {
			LOG.info("Still the data in the table");

			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<Faculty> findByFirstNameContaining(String firstName) {
		LOG.info("The request came into findByFirstNameContaining");
		try {
			String q = "SELECT * from faculty_table WHERE firstName LIKE '%" + firstName + "%'";
			return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Faculty.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Faculty> deleteByPartialFirstName(String matchString) {
		LOG.info("The request came into deleteByPartialFirstName");
		try {
			String q = "SELECT * from faculty_table WHERE firstName LIKE '%" + matchString + "%'";
			return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Faculty.class));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int updateSpecificFieldById(int id, String fieldName, String updateField) {
		LOG.info("The request came into updateSpecificFieldById");
		//List<Faculty> facultyList = new ArrayList<>();
		try {
			//String updateSql = "UPDATE faculty_table SET fieldName = ?,updateField=? WHERE id = ?";
			String updateSql = "UPDATE faculty_table SET "+ fieldName+ "='"+updateField+"' WHERE id ="+id;
			LOG.info("updateSpecificFieldById query: {}", updateSql);
			//int[] types = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
			//Object[] params = { fieldName, updateField,id};
		//return jdbcTemplate.update(updateSql, params, types);
			return jdbcTemplate.update(updateSql);


		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}



