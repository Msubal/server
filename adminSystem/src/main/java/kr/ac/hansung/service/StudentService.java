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
		return studentDao.getStudents();
	}
	
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
	}
	
	public boolean deleteStudent(int num) {
		return studentDao.deleteStudent(num);
	}
	
	public boolean updateStudent(Student student) {
		return studentDao.updateSutent(student);
	}
	
	public Student getStudentById(int num) {
		
		return studentDao.getStudentById(num);
	}
}
