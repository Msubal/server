package kr.ac.hansung.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.StudentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
	
		List<Student> Students = studentService.getStudents();
		model.addAttribute("Students", Students);
		
		return "home";
	}
	
}
