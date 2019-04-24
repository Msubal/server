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

import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.service.ScheduleService;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value="/admin/buildings/schedules/{number}",method=RequestMethod.GET)
	public String getSchedules(@PathVariable int number, Model model) {
		
		List<Schedule> schedules = scheduleService.getSchedules(number);
		model.addAttribute("schedules", schedules);
		return "schedules";
		
	}
	@RequestMapping(value="/admin/buildings/schedules/addSchedule/{number}",method=RequestMethod.GET)
	public String addSchedule(Model model,@PathVariable int number) {
		
		Schedule schedule = new Schedule();		
		
		model.addAttribute("schedule",schedule);
		
		return "addSchedule";
	}
	
	@RequestMapping(value="/admin/buildings/schedules/addSchedule/{number}",method=RequestMethod.POST)
	public String addSchedulePost(@Valid Schedule schedule,@PathVariable int number, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addSchedule";
		}
		
		if( !scheduleService.addSchedule(schedule))
			System.out.println("Adding schedule Can't be done!!");
		
		return "redirect:/admin/buildings/schedules/{number}";
	}
	
	@RequestMapping(value="/admin/buildings/schedules/deleteSchedule/{number}/{num}",method=RequestMethod.GET)
	public String deleteSschedule(@PathVariable int num,@PathVariable int number) {
		
		if( !scheduleService.deleteSchedule(num)) {
			System.out.println("Deleting schedule Can't be Done!!");
		}
		
		return "redirect:/admin/buildings/schedules/{number}";
	}
	
	@RequestMapping(value="/admin/buildings/schedules/updateSchedule/{num}",method=RequestMethod.GET)
	public String updateSchedule(@PathVariable int num, Model model) {
		
		Schedule schedule = scheduleService.getScheduleById(num);
		model.addAttribute("schedule", schedule);
		return "updateSchedule";
	}
	
	@RequestMapping(value="/admin/buildings/schedules/updateSchedule/{number}",method=RequestMethod.POST)
	public String updateSchedulePost(@Valid Schedule schedule,@PathVariable int number, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateStudent";
		}
		
		if( !scheduleService.updateSchedule(schedule))
			System.out.println("Adding student Can't be done!!");
		
		return "redirect:/admin/buildings/schedules/{number}";
	}
}
