
package com.autonomus.jntu.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.autonomus.jntu.model.Faculty;
import com.autonomus.jntu.repository.FacultyRepositoryOld;
import com.autonomus.jntu.service.FacultyService;


@Repository
public class FacultyRepositoryImpl implements FacultyRepositoryOld {

	final Logger LOG  = LoggerFactory.getLogger(FacultyService.class);

	List<Faculty> facultyList = new ArrayList<>();

	@Override
	public List<Faculty> retriveAllDetails() {

		LOG.info("The request came into retriveAllDetails");

		Faculty faculty1 = new Faculty(); faculty1.setId(1234);
		faculty1.setFirstName("Jay"); faculty1.setLastName("Peddi");
		faculty1.setDob("01/01/1990"); faculty1.setTeaching(true);
		faculty1.setMobileNo(1234567890);

		Faculty faculty2 = new Faculty(); faculty2.setId(1234);
		faculty2.setFirstName("Vijay"); faculty2.setLastName("Peddy");
		faculty2.setDob("02/02/1992"); faculty2.setTeaching(false);
		faculty2.setMobileNo(234567890);

		Faculty faculty3 = new Faculty(); faculty3.setId(3412);
		faculty3.setFirstName("Chinni"); faculty3.setLastName("Peddi");
		faculty3.setDob("03/03/1993"); faculty3.setTeaching(true);
		faculty3.setMobileNo(34567890);

		Faculty faculty4 = new Faculty(); faculty4.setId(98765);
		faculty4.setFirstName("Jay"); faculty4.setLastName("Peddy");
		faculty4.setDob("03/03/1993"); faculty4.setTeaching(true);
		faculty4.setMobileNo(34567890);

		boolean isFirstFacultyPresent = false;
		boolean isSecondFacultyPresent = false;
		boolean isThirdFacultyPresent = false;
		boolean isFourthFacultyPresent = false;

		for (Faculty faculty : facultyList) { 
			if(faculty.getId()==faculty1.getId()) {
				LOG.info("The FirstFaculty id is match");
				isFirstFacultyPresent = true; 
			} else if(faculty.getId()==faculty2.getId()) {
				isSecondFacultyPresent = true;
			} else if(faculty.getId()==faculty3.getId()) {
				isThirdFacultyPresent = true; 
			} else if(faculty.getId()==faculty4.getId()) {
				isFourthFacultyPresent = true; 
			}
		}

		if(isFirstFacultyPresent == false) { 
			if(!isFirstFacultyPresent) {
				LOG.info("New Faculty Id is added"); facultyList.add(faculty1); }
			if(!isSecondFacultyPresent) {
				facultyList.add(faculty2); 
			}
			if(!isThirdFacultyPresent) { 
				facultyList.add(faculty3); 
			}
			if(!isFourthFacultyPresent) {
				facultyList.add(faculty4); 
			}
		} 
		return facultyList;
	}
}
