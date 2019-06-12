package kr.ac.hansung.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.model.Otp;
import kr.ac.hansung.service.OtpService;

@Controller
@RequestMapping(value="/admin/otps")
public class OtpController {
	   @Autowired
	   private OtpService otpService;
	   
	   @RequestMapping(value="")
	   public String getOtp(Model model) {
	      
	      List<Otp> otps = otpService.getLogs();
	      model.addAttribute("otps", otps);
	      
	      return "otps";
	   }

}
