package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import kr.ac.hansung.model.Building;
import kr.ac.hansung.model.Otp;

@Service
public class OtpDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Otp> getLog() {
		String sqlStatement = "select * from log";
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
   
}