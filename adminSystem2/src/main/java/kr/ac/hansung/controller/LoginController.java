package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.Login;
import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.StudentService;

@RestController
public class LoginController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/loginSystem", method = RequestMethod.POST)
	public ResponseEntity<Student> loginController(@RequestBody Login login) {

		ResponseEntity<Student> response;
		Student student = null;

		List<Student> students = studentService.getStudents();
		for (int i = 0; i < students.size(); i++) 
		{
			if ((students.get(i).getId() == login.getId())&&(students.get(i).getPassword()==Integer.parseInt(login.getPassword()))) {
				if (students.get(i).getCheck().equals("x")) {
					student = students.get(i);
					student.setCheck("o");
				} else
					break;

				studentService.updateStudent(student);
				break;
			}
		}

		response = new ResponseEntity<Student>(student, HttpStatus.OK);
		return response;
	}

	
	@RequestMapping(value = "/logoutSystem", method = RequestMethod.POST)
	public ResponseEntity<Student> logoutController(@RequestBody Login login) {

		ResponseEntity<Student> response;
		Student student = null;

		List<Student> students = studentService.getStudents();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId() == login.getId()) {
				student = students.get(i);
				student.setCheck("x");
				studentService.updateStudent(student);
				break;
			}
		}

		response = new ResponseEntity<Student>(student, HttpStatus.OK);
		return response;
	}

}
