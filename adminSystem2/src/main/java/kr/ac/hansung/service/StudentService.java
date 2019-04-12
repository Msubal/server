package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.StudentDao;
import kr.ac.hansung.model.Student;


@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	public List<Student> getStudents(){
		return studentDao.getProducts();
	}

}
