package kr.ac.hansung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String Student_Json(Model model) {
		
		String str = "{"
                + "\"name\":\"choHeeSoung\","
                + "\"age\":\"27\" "
                + "}";

		
		return str;
	}
}
