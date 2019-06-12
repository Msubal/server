package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Otp;

@Repository
public class OtpDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Otp> getOtp() {
		String sqlStatement = "select * from schedule";
		return jdbcTemplate.query(sqlStatement, new RowMapper<Otp>() {

			@Override
			public Otp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Otp otp = new Otp();
				otp.setNum(rs.getInt("num"));
				otp.setStudent_id(rs.getString("student_id"));
				otp.setBuilding_num(rs.getInt("building_num"));
				otp.setDay(rs.getInt("day"));
				otp.setStart_time(rs.getInt("start_time"));
				otp.setEnd_time(rs.getInt("end_time"));
				otp.setOtp_pw(rs.getInt("otp_pw"));
				otp.setCurrent(rs.getString("current"));
				
				return otp;
			}
		});
	}

	public boolean addOtp(Otp otp) {
		String student_id = otp.getStudent_id();
		int building_num = otp.getBuilding_num();
		int day = otp.getDay();
		int start_time = otp.getStart_time();
		int end_time = otp.getEnd_time();
		int otp_pw = otp.getOtp_pw();
		String current = otp.getCurrent();
		
		String sqlStatement = "insert into otprecord (student_id,building_num,day,start_time,end_time,otp_pw,current) values( ?, ?, ?, ?, ?, ?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { student_id, building_num, day, start_time,end_time,otp_pw,current}) == 1);
	}
	
	public boolean addLog(Otp otp) {
		String student_id = otp.getStudent_id();
		int building_num = otp.getBuilding_num();
		int day = otp.getDay();
		int start_time = otp.getStart_time();
		int end_time = otp.getEnd_time();
		int otp_pw = otp.getOtp_pw();
		String current = otp.getCurrent();
		
		String sqlStatement = "insert into log (student_id,building_num,day,start_time,end_time,otp_pw,current) values( ?, ?, ?, ?, ?, ?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { student_id, building_num, day, start_time,end_time,otp_pw,current}) == 1);
	}
	
	public boolean deleteOpt(String student_id) {
		
		String sqlStatement = "delete from otprecord where student_id = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] {student_id}) == 1);
	}
	
	public Otp getOtpById(int id) {

		String sqlStatement = "select * from otprecord where student_id = ?"; // id value search

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id }, new RowMapper<Otp>() {

			@Override
			public Otp mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Otp otp = new Otp();
				otp.setNum(rs.getInt("num"));
				otp.setStudent_id(rs.getString("student_id"));
				otp.setBuilding_num(rs.getInt("building_num"));
				otp.setDay(rs.getInt("day"));
				otp.setStart_time(rs.getInt("start_time"));
				otp.setEnd_time(rs.getInt("end_time"));
				otp.setOtp_pw(rs.getInt("otp_pw"));
				otp.setCurrent(rs.getString("current"));
				
				return otp;
			}
			
		});
		
	}
	
}
