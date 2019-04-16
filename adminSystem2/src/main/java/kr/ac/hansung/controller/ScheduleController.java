package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.service.ScheduleService;

@Controller
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String schedule(Model model) {
	
		List<Schedule> Schedule = scheduleService.getSchedule();
		model.addAttribute("Schedule", Schedule);
		
		return "schedule";
	}
	
	@RequestMapping(value = "/scheduleSelect", method = RequestMethod.GET)
	public String scheduleSelect(Model model) {
	
		
		return "scheduleSelect";
	}
	
	
}
