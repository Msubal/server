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

import kr.ac.hansung.model.Building;
import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.service.BuildingService;
import kr.ac.hansung.service.ScheduleService;

@Controller
@RequestMapping(value="/admin/buildings/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private BuildingService buildingService;
	
	@RequestMapping(value="/{num}",method=RequestMethod.GET)
	public String getSchedules(@PathVariable int num, Model model) {
		
		Building building = buildingService.getBuildingById(num);
		int number = building.getNumber();
		List<Schedule> schedules = scheduleService.getSchedules(number);
		model.addAttribute("schedules", schedules);
		return "schedules";
		
	}
	
	@RequestMapping(value="/addSchedule/{num}",method=RequestMethod.GET)
	public String addSchedule(Model model,@PathVariable int num) {
		
		Schedule schedule = new Schedule();	
		Building building = buildingService.getBuildingById(num);

		String name = building.getName();
		int number = building.getNumber();
		
		schedule.setName(name);
		schedule.setNumber(number);
		
		model.addAttribute("schedule",schedule);
		
		return "addSchedule";
	}
	
	@RequestMapping(value="/addSchedule/{number}",method=RequestMethod.POST)
	public String addSchedulePost(@Valid Schedule schedule,@PathVariable int number, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "error";
		}
		
		if( !scheduleService.addSchedule(schedule)) {
			System.out.println("Adding schedule Can't be done!!");
		}
		return "redirect:/admin/buildings/schedules/{number}";
	}
	
	@RequestMapping(value="/deleteSchedule/{num}",method=RequestMethod.GET)
	public String deleteSschedule(@PathVariable int num) {
		
		Schedule schedule = scheduleService.getScheduleById(num);
		String name = schedule.getName();
		int number = schedule.getNumber();
		Building building = buildingService.getBuildingByIdNumber(name,number);
		
		if( !scheduleService.deleteSchedule(num)) {
			System.out.println("Deleting schedule Can't be Done!!");
		}
		int building_num = building.getNum();
		
		return "redirect:/admin/buildings/schedules/"+building_num;
	}
	
	@RequestMapping(value="/updateSchedule/{num}",method=RequestMethod.GET)
	public String updateSchedule(@PathVariable int num, Model model) {
		
		Schedule schedule = scheduleService.getScheduleById(num);
				
		model.addAttribute("schedule", schedule);
		return "updateSchedule";
	}
	
	@RequestMapping(value="/updateSchedule/{num}",method=RequestMethod.POST)
	public String updateSchedulePost(@Valid Schedule schedule,@PathVariable int num, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateSchedule";
		}
		
		if( !scheduleService.updateSchedule(schedule))
			System.out.println("Adding student Can't be done!!");
		
		String name = schedule.getName();
		int number = schedule.getNumber();
		Building building = buildingService.getBuildingByIdNumber(name,number);
		int building_num = building.getNum();
		
		return "redirect:/admin/buildings/schedules/"+building_num;
	}
}
