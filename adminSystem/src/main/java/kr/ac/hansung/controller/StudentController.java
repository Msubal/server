package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.StudentService;

@Controller
//@RequestMapping("/admin")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
//	@RequestMapping
//	public String adminPage() {
//		return "admin";
//	}
	
	@RequestMapping("/admin/students")
	public String getStudents(Model model) {
		
		List<Student> students = studentService.getStudents();
		model.addAttribute("students",students);
		
		return "students";
	}
	
	@RequestMapping(value="/admin/students/addStudent",method=RequestMethod.GET)
	public String addStudent(Model model) {
		
		Student student = new Student();		
		
		model.addAttribute("student",student);
		
		return "addStudent";
	}
	
	@RequestMapping(value="/admin/students/addStudent",method=RequestMethod.POST)
	public String addStudentPost(@Valid Student student, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addStudent";
		}
		
		if( !studentService.addStudent(student))
			System.out.println("Adding student Can't be done!!");
		
		return "redirect:/admin/students";
	}
	
	@RequestMapping(value="/admin/students/deleteStudent/{num}",method=RequestMethod.GET)
	public String deleteStudent(@PathVariable int num) {
		
		if( !studentService.deleteStudent(num)) {
			System.out.println("Deleting student Can't be Done!!");
		}
		
		return "redirect:/admin/students";
	}
	
	@RequestMapping(value="/admin/students/updateStudent/{num}",method=RequestMethod.GET)
	public String updateStudent(@PathVariable int num, Model model) {
		
		Student student = studentService.getStudentById(num);
		model.addAttribute("student", student);
		return "updateStudent";
	}
	
	@RequestMapping(value="/admin/students/updateStudent",method=RequestMethod.POST)
	public String updateStudentPost(@Valid Student student, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateStudent";
		}
		
		if( !studentService.updateStudent(student))
			System.out.println("Adding student Can't be done!!");
		
		return "redirect:/admin/students";
	}
}
