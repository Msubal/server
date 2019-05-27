package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.ScheduleDao;
import kr.ac.hansung.model.Schedule;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao ;
	
	public List<Schedule> getSchedule() {
		
		return scheduleDao.getSchedule();
	}

	public List<Schedule> getSchedules(int number) {
		
		return scheduleDao.getschedules(number);
	}

}
