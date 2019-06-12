package kr.ac.hansung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.OtpDao;
import kr.ac.hansung.model.Otp;

@Service
public class OtpService {
	
	@Autowired
	OtpDao otpDao;
	

	public boolean addOtp(Otp otp) {
		return otpDao.addOtp(otp);
	}
	
	public boolean addLog(Otp otp) {
		return otpDao.addLog(otp);
	}
	
	public boolean deleteOtp(String student_id) {
		return otpDao.deleteOpt(student_id);
	}
	
	public Otp getOtpById(int id) {
		return otpDao.getOtpById(id);
	}
}
