package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.StudentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StudentsController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String studentsShow(Model model) {

		List<Student> Students = studentService.getStudents();
		model.addAttribute("Students", Students);

		return "home";
	}
}
