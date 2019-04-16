package kr.ac.hansung.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.ScheduleService;
import kr.ac.hansung.service.StudentService;

@RestController
public class JsonController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = "/student_api", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String student_Json(Model model) {

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
	
	@RequestMapping(value = "/schedule_api", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String schedule_Json(Model model) {

		 List<Schedule> Schedules = scheduleService.getSchedule();
		   
		   ObjectMapper mapper = new ObjectMapper();
		   String result = "";
		   try {
		      result = mapper.writeValueAsString(Schedules);
		   } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		   }
		   
		   return result;
		}
}
