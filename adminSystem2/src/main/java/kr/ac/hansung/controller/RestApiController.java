package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.service.ScheduleService;

@RestController
public class RestApiController {

	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = "/schedule_api/{number}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String schedule_Json(@PathVariable int number,Model model) {

		 List<Schedule> Schedules = scheduleService.getSchedules(number);
		   
		   ObjectMapper mapper = new ObjectMapper();
		   String result = "";
		   try {
		      result = mapper.writeValueAsString(Schedules);
		   } catch (Exception e) {
		      
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
		      
		      e.printStackTrace();
		   }
		   
		   return result;
	}
}
