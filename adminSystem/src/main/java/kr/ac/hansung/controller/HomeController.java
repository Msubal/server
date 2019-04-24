package kr.ac.hansung.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required = false) String error,
			@RequestParam(value="logout", required = false) String logout,Model model) {
		
		if(error != null) {
			model.addAttribute("errorMsg","Invalid Username and Password!!");
		}
		if(logout != null) {
			model.addAttribute("logoutMsg","You have been logged out succesfully!!");
		}
		return "home";
	}
	
	@RequestMapping("/error")
	public String error(@RequestParam(value="error", required = false) String error,
			@RequestParam(value="logout", required = false) String logout,Model model) {
		
		if(error != null) {
			model.addAttribute("errorMsg","Invalid Username and Password!!");
		}
		if(logout != null) {
			model.addAttribute("logoutMsg","You have been logged out succesfully!!");
		}
		return "error";
	}
	
}
