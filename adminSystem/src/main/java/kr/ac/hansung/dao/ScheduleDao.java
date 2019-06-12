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

	public List<Schedule> getschedules(int number) {

		String sqlStatement = "select * from `schedule` where `number` = ?";

		return jdbcTemplate.query(sqlStatement,new Object[] { number },new RowMapper<Schedule>() {

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
	
	public boolean addSchedule(Schedule schedule) {
		
		String name = schedule.getName();
		int number = schedule.getNumber();
		int day = schedule.getDay();
		int time = schedule.getTime();
		
		String sqlStatement = "insert into schedule ( name, number, day, time) values( ?, ?, ?, ?)";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, number, day, time}) == 1);
	}

	
	public boolean deleteSchedule(int num) {
		
		String sqlStatement = "delete from schedule where num = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] {num}) == 1);
	}
	
	public boolean updateSchedule(Schedule schedule) {
		
		int num = schedule.getNum();
		String name = schedule.getName();
		int number = schedule.getNumber();
		int day = schedule.getDay();
		int time = schedule.getTime();
		
		String sqlStatement = "update schedule set name = ?, number = ?, day = ?, time = ? where num = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] { name, number, day, time, num }) == 1);
	}
	
//	public Schedule getScheduleByNumber(int number) {
//		
//		String sqlStatement = "select * from schedule where number = ?"; // id value search
//
//		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { number }, new RowMapper<Schedule>() {
//
//			@Override
//			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//				Schedule schedule = new Schedule();
//				
//				schedule.setNum(rs.getInt("num"));
//				schedule.setName(rs.getString("name"));
//				schedule.setNumber(rs.getInt("number"));
//				schedule.setDay(rs.getInt("day"));
//				schedule.setTime(rs.getInt("time"));
//
//				return schedule;
//			}
//		});
//	}
	
	public Schedule getScheduleById(int num) {

		String sqlStatement = "select * from schedule where num = ?"; // id value search

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { num }, new RowMapper<Schedule>() {

			@Override
			public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

				Schedule schedule = new Schedule();
				
				schedule.setNum(rs.getInt("num"));
				schedule.setName(rs.getString("name"));
				schedule.setNumber(rs.getInt("number"));
				schedule.setDay(rs.getInt("day"));
				schedule.setTime(rs.getInt("time"));

				return schedule;
			}
		});
	}
	
}
