package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.FacultyRepository;
@Service
public class FacultyManager {
	@Autowired
	FacultyRepository FR;
public String addfaculty(Faculty F)
{
	try
	{
		FR.save(F);//INSERT operation
		return "New Faculty has been added";
	}catch(Exception e) {
		return e.getMessage();
	}
}
}
