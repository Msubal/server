package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.OtpDao;
import kr.ac.hansung.model.Otp;

@Service
public class OtpService {
   
   @Autowired
   private OtpDao otpDao;
   
   public List<Otp> getLogs(){
      return otpDao.getLog();
   }
}