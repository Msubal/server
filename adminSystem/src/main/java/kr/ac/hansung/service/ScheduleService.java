package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.ScheduleDao;
import kr.ac.hansung.model.Schedule;
import kr.ac.hansung.model.Student;

@Service
public class ScheduleService {
	
	@Autowired
	private ScheduleDao scheduleDao;
	
	public List<Schedule> getSchedules(int number){
		return scheduleDao.getschedules(number);
	}
	
	public boolean addSchedule(Schedule schedule) {
		return scheduleDao.addSchedule(schedule);
	}

	public boolean deleteSchedule(int num) {
		
		return scheduleDao.deleteSchedule(num);
	}
	
	public boolean updateSchedule(Schedule schedule) {
		
		return scheduleDao.updateSchedule(schedule);
	}

	public Schedule getScheduleById(int num) {
		
		return scheduleDao.getScheduleById(num);
	}
}
