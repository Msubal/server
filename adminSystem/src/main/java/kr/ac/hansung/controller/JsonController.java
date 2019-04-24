package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.StudentService;

@RestController
public class JsonController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/api", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String Student_Json(Model model) {

		 List<Student> Students = studentService.getStudents();
		   
		   ObjectMapper mapper = new ObjectMapper();
		   String result = "";
		   try {
		      result = mapper.writeValueAsString(Students);
		   } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		   }
		   
		   return result;
		}
}