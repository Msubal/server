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
import kr.ac.hansung.service.BuildingService;

@Controller
public class BuildingController {

	@Autowired
	private BuildingService buildingService;
	
	@RequestMapping("/admin/buildings")
	public String getBuildings(Model model) {
		
		List<Building> buildings = buildingService.getBuildings();
		model.addAttribute("buildings",buildings);
		
		return "buildings";
	}
	
	@RequestMapping(value="/admin/buildings/addBuilding",method=RequestMethod.GET)
	public String addBuilding(Model model) {
		
		Building building = new Building();		
		
		model.addAttribute("building",building);
		
		return "addBuilding";
	}
	
	@RequestMapping(value="/admin/buildings/addBuilding",method=RequestMethod.POST)
	public String addBuildingPost(@Valid Building building, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addBuilding";
		}
		
		if( !buildingService.addBuilding(building))
			System.out.println("Adding building Can't be done!!");
		
		return "redirect:/admin/buildings";
	}
	
	@RequestMapping(value="/admin/buildings/deleteBuilding/{num}",method=RequestMethod.GET)
	public String deleteBuilding(@PathVariable int num) {
		
		if( !buildingService.deleteBuilding(num)) {
			System.out.println("Deleting building Can't be Done!!");
		}
		
		return "redirect:/admin/buildings";
	}
	
	@RequestMapping(value="/admin/buildings/updateBuilding/{num}",method=RequestMethod.GET)
	public String updateBuilding(@PathVariable int num, Model model) {
		
		Building building = buildingService.getBuildingById(num);
		model.addAttribute("building", building);
		return "updateBuilding";
	}
	
	@RequestMapping(value="/admin/buildings/updateBuilding",method=RequestMethod.POST)
	public String updateBuildingPost(@Valid Building building, BindingResult result) {
		
		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateBuilding";
		}
		
		//System.out.println(product);
		if( !buildingService.updateBuilding(building))
			System.out.println("Adding building Can't be done!!");
		
		return "redirect:/admin/buildings";
	}
}
