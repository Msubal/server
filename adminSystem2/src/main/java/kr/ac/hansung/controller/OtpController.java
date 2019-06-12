package kr.ac.hansung.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.Login;
import kr.ac.hansung.model.Otp;
import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.model.Student;
import kr.ac.hansung.service.OtpService;
import kr.ac.hansung.service.ScheduleService;
import kr.ac.hansung.service.StudentService;

@RestController
public class OtpController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private StudentService studentService;

	//otp 신청
	@RequestMapping(value = "/otpSystem", method = RequestMethod.POST)
	public ResponseEntity<Otp> otpController(@RequestBody Otp otp) {

		int number = otp.getBuilding_num();
		int day = otp.getDay();
		int start_time = otp.getStart_time();
		int end_time = otp.getEnd_time();
	
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int weekgap = day - dayOfWeek;
		
		if(weekgap < 0) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		else{
			date = date + weekgap;
			}
//		System.out.println(date);
//		System.out.println(number + "호" + day + "요일" + start_time + "시 부터 " + end_time + "시 까지" );
		
		Student student = null;
		List<Student> students = studentService.getStudents();
		
		for (int i = start_time; i <= end_time ; i++) {
			Schedule s = new Schedule();
			s.setName("공학관");
			s.setNumber(number);
			s.setDay(day);
			s.setTime(i);

			scheduleService.addSchedule(s);
			//System.out.println("추가");
		}
		
		//otp 비밀번호 생성 --> 첫번째 번호만 0이 아닌 4자리 숫자 출력
		Random ramdom = new Random();
		int thou = ramdom.nextInt(10);
		while(thou == 0) {
			thou = ramdom.nextInt(10);
		}
		int otp_pw = thou * 1000 + ramdom.nextInt(10) * 100 + ramdom.nextInt(10) * 10
				+ ramdom.nextInt(10);
		
		//otp 번호 신청했을때 시간저장
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Date time = new Date();
		String current = format1.format(time); 
		
		otp.setDay(day);
		otp.setOtp_pw(otp_pw);
		otp.setCurrent(current);

		otpService.addOtp(otp);
		otpService.addLog(otp);
		
		for (int i = 0; i < students.size(); i++) 
		{
			if ((students.get(i).getId() == Integer.parseInt(otp.getStudent_id()))) {
				if (students.get(i).getOtp_check().equals("x")) {
					student = students.get(i);
					student.setOtp_check("o");
				} else
					break;

				studentService.updateStudent(student);
				//System.out.println("성공");
				break;
			}
		}
		
		return new ResponseEntity<Otp>(otp, HttpStatus.OK);
	}
	
	
	// otp를 가지고 있는지 아닌지를 검사하는 곳
	@RequestMapping(value = "/otpCheck", method = RequestMethod.POST)
	public ResponseEntity<Otp> otpCheck(@RequestBody Login login){
		
		Otp otpCheck = otpService.getOtpById(login.getId());
		
		
		return new ResponseEntity<Otp>(otpCheck,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/otpDelete", method = RequestMethod.POST)
	public ResponseEntity<Otp> otpDelete(@RequestBody Otp otp){
		
		int day = otp.getDay();
		int start_time = otp.getStart_time();
		int end_time = otp.getEnd_time();
		
		Student student = null;
		List<Student> students = studentService.getStudents();
		
		for (int i = 0; i < students.size(); i++) 
		{
			if ((students.get(i).getId() == Integer.parseInt(otp.getStudent_id()))) {
				if (students.get(i).getOtp_check().equals("o")) {
					student = students.get(i);
					student.setOtp_check("x");
				} else
					break;

				studentService.updateStudent(student);
				//System.out.println("성공");
				break;
			}
		}
		
		otpService.deleteOtp(otp.getStudent_id());
		
		for (int i = start_time; i <= end_time ; i++) {
			scheduleService.deleteSchedule(day, i);
			//System.out.println("삭제");
		}
		
		return new ResponseEntity<Otp>(otp, HttpStatus.OK);
	}

}
