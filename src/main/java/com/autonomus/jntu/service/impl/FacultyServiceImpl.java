package com.autonomus.jntu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autonomus.jntu.exceptions.ArrayIsEmpty;
import com.autonomus.jntu.exceptions.FacultyException;
import com.autonomus.jntu.model.Faculty;
import com.autonomus.jntu.repository.FacultyRepository;
import com.autonomus.jntu.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

	final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

	@Autowired
	private FacultyRepository facultyRepository;

	public Object getFacultyDetailsById;
	/* Why we mentioned public object getFacultyDetailsById; */

	@Override
	public List<Faculty> getAllFacultyDetails() throws Exception{
		LOG.info("The Request came into getAllFacultyDetails method");
		try {
			List<Faculty> facultyList = facultyRepository.findAll();
			if(facultyList.size()==0) {
				LOG.error("The List is Empty");
				throw new ArrayIsEmpty();
			}
			return facultyList;
		}catch(ArrayIsEmpty e) {
			throw(e);
		}catch(Exception e) {
			throw(e);
		}
	}

	@Override
	public Faculty getFacultyDetailsById(int id) {
		LOG.info("The Request came into getFacultyDetailsById method");

		//List<Faculty> newFacultyList = new ArrayList<>();
		try {
			LOG.info("Try to match the facultyList id");
			Faculty faculty = facultyRepository.retriveAllFacultyDetailsById(id);
			if(faculty==null) {
				return null;
			}

			/*for(int i=0;i<facultyList.size(); i++) {
				Faculty faculty = facultyList.get(i);
				if(faculty.getId() == id) {
					newFacultyList.add(facultyList.get(i));
					LOG.info("The given id List is displayed");
				} else if(faculty.getId()==0) {
					LOG.debug("The given id is wrong");
					throw new NumberFormatException("The given id is wrong");
				}
			}*/
			//return (Faculty) newFacultyList;
			//return facultyList.stream().filter(faculty -> faculty.getId()==id).findAny().get();
		}catch(NumberFormatException e) {
			throw(e);
		}catch(Exception e) {
			throw(e);
		}
		return null;

	}

	@Override
	public void insertFacultyDetail(List<Faculty> facultyList) throws Exception {
		LOG.info("The Request came into Post method");
		try {
			facultyRepository.save(facultyList);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw(e);
		} catch(Exception e) {
			throw(e);
		}
	}

	@Override
	public List<Faculty> updateFacultyDetail(List<Faculty> facultyList) {
		//List<Faculty> newFacultyList = new ArrayList<>();
		LOG.info("Try to set the new values or create the new record based on the database record");
		try {
			for(int i=0;i<facultyList.size(); i++) {
				Faculty faculty = facultyList.get(i);
				//Step1: Query DB to see if faculty id is present
				//Step2: If faculty is present, use update method
				//Step3: If faculty is not present, use insert method

				Faculty facultyFromDatabase = facultyRepository.retriveAllFacultyDetailsById(faculty.getId());
				if(facultyFromDatabase != null) {
					List<Faculty> newFacultyList = new ArrayList<>();
					newFacultyList.add(faculty);
					facultyRepository.update(newFacultyList);
				} else {
					List<Faculty> newFacultyList = new ArrayList<>();
					newFacultyList.add(faculty);
					facultyRepository.save(newFacultyList);
				}
			}
		}catch(ArrayStoreException e ) {
			throw(e);
		}catch(Exception e ) {
			throw(e);

		}
		return facultyList;
	}



	@Override
	public List<Faculty> patchFacultyDetail(int id, String fieldName, String updateField) throws Exception {
		LOG.info("The Request came into Patch method");
		try {
			facultyRepository.updateSpecificFieldById(id, fieldName, updateField);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*for (Faculty faculty : facultyList) {
				if(faculty.getId()==id) {

					if(fieldName.equalsIgnoreCase("firstName")) {
						faculty.setFirstName(updateField);
					} else if(fieldName.equals("lastName")) {
						faculty.setLastName(updateField);
					}else if(fieldName.equals("Dob")) {
						faculty.setDob(updateField);
						LOG.info("The filedName is updated");
						throw new Exception("");
					} else if(fieldName.isBlank())
						LOG.error("The fieldName is empty");
					throw new IOException("The input is not correct");
				}else if(facultyList.size()==0) {
					LOG.debug("The Fieldname is not updated");
					throw new FacultyException("fieldName","updateField is not updated");
				}
				return facultyList;
			}
			return null;
		}catch(IOException e) {
			throw(e);
		}catch(FacultyException e) {
			throw(e);
		}catch(Exception e) {
			throw(e);
		}
	}*/

	@Override
	public int deleteFacultyDetailById(int id) throws Exception {
		LOG.info("The Request came into deleteFacultyDetailById method");
		try {
			Thread.sleep(1000);
			int numRows = facultyRepository.deleteById(id);
			if(numRows ==0) {
				LOG.info("There is no data to delete");
			} else if(numRows>0){
				LOG.info("Delete {} number of records", + numRows);
			}
		}catch(FacultyException e) {
			throw(e);
		} catch(Exception e) {
			throw(e);
		}
		return id;
	}
	/*//int facultyList = facultyRepository.deleteById(id);
			for(int i=0; i<facultyList.size(); i++) {
				Faculty faculty = facultyList.get(i);
				if(faculty.getId()==id) {

					facultyList.remove(i);
					LOG.info("The given id List is deleted");
				}
				if(faculty.getId()!=id) {
					LOG.debug("The given id List is not deleted");
					throw new FacultyException("NULL ID"," Faculty Data id is not found");
				} else if(faculty.getId()==0) {
					LOG.error("The given is not matched");
					throw new NullPointerException("Received value 0 for Id");
				} else if(facultyList.size()<=0) {
					LOG.info("The list is empty");
					throw new LibraryException("NULL  LIST SIZE", "Received null faculty list");
				}

				//return facultyList;
			}
			//facultyList.removeIf(faculty -> faculty.getId()==id);
			return null;

		} catch(FacultyException e) {
			throw(e);
		} catch(Exception e) {
			throw(e);
		}*/


	@Override
	public void deleteAllFacultyDetails() throws Exception {
		LOG.info("The Request came into deleteAllFacultyDetails method");
		try {
			int numRows = facultyRepository.deleteAll();
			//facultyList.clear();
			LOG.info("The list is clear");
			/*if(facultyList.size()!=0) {
						LOG.error("Still the list have some records");
						throw new Exception("All Faculty list is not deleted");
					}*/
			if(numRows ==0) {
				LOG.info("There is no data to delete");
			} else if(numRows>0){
				LOG.info("Delete {} number of records", numRows);}
		} catch(Exception e) {
			throw(e);
		}
	}

	@Override
	public void deleteMatchFaculty(String matchstring) {
		LOG.info("The Request came into deleteMatchFaculty method");
		try {

			List<Faculty>	findMatchStringBooks = facultyRepository.deleteByPartialFirstName(matchstring);
			LOG.info("deleted books size is{}",findMatchStringBooks.size());

			if(findMatchStringBooks != null) {
				for(int i=0;i<findMatchStringBooks.size();i++) {
					facultyRepository.deleteById(findMatchStringBooks.get(i).getId());

				}
			}
			//List<Faculty> newFacultyList = new ArrayList<>();
			//newFacultyList.clear();
			//facultyRepository.update(newFacultyList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*for(int i=0; i<facultyList.size();i++) {
				Faculty faculty = facultyList.get(i);
				if(faculty.getFirstName().contains(matchstring)) {
					facultyList.remove(i);
					LOG.info("The given matching string List is deleted");
				}
				else if(facultyList.size()>10) {
					LOG.error("The List is too large");
					throw new FacultyException("Large Size", "Too many records are returned. This is not expected");
				}
				return facultyList;  
			}
			return null;
		} catch(FacultyException e) {
			throw (e);
		} catch(Exception e) {
			throw (e);
		}
	 */


	@Override
	public List<Faculty> findByFirstNameFaculty(String firstName) {
		LOG.info("The Request came into findByFirstNameFaculty method");
		try {
			return facultyRepository.findByFirstNameContaining(firstName);
		}catch(Exception e) {
			throw(e);
		}
	}
}


