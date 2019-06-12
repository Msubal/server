package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Schedule;

@Repository
public class ScheduleDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Schedule> getSchedule() {
		String sqlStatement = "select * from schedule";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
				Schedule schedule = new Schedule();
				schedule.setNum(rs.getInt("num"));
				schedule.setName(rs.getString("name"));
				schedule.setDay(rs.getInt("day"));
				schedule.setTime(rs.getInt("time"));
				// schedule.setOtp_password(rs.getInt("otp_password"));

				return schedule;
			}
		});
	}

	public List<Schedule> getschedules(int number) {

		String sqlStatement = "select * from `schedule` where `number` = ?";

		return jdbcTemplate.query(sqlStatement, new Object[] { number }, new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

				Schedule schedule = new Schedule();

				schedule.setNum(rs.getInt("num"));
				schedule.setName(rs.getString("name"));
				schedule.setNumber(number);
				schedule.setDay(rs.getInt("day"));
				schedule.setTime(rs.getInt("time"));
				return schedule;
			}

		});
	}
	
	public boolean deleteSchedule(int day,int time) {
		
		String sqlStatement = "delete from schedule where day = ? and time=?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] {day,time}) == 1);
	}

	public boolean addSchedule(Schedule schedule) {

		String name = schedule.getName();
		int number = schedule.getNumber();
		int day = schedule.getDay();
		int time = schedule.getTime();

		String sqlStatement = "insert into schedule ( name, number, day, time) values( ?, ?, ?, ?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { name, number, day, time }) == 1);
	}

}
