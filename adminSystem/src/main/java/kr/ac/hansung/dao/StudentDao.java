package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Student;

@Repository
public class StudentDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Student> getStudents(){
		String sqlStatement = "select * from student";
		
		return jdbcTemplate.query(sqlStatement, new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

				Student student = new Student();
				
				student.setNum(rs.getInt("num"));
				student.setId(rs.getInt("id"));
				student.setPassword(rs.getInt("password"));
				student.setName(rs.getString("name"));
				student.setCheck(rs.getString("check"));
				student.setOtp_check(rs.getString("otp_check"));
				
				return student;
			}
		});
	}
	
	public boolean addStudent(Student student) {
		
		String name = student.getName();
		int id = student.getId();
		int password = student.getPassword();
		String check = student.getCheck();
		String otp_check = student.getOtp_check();
		
		String sqlStatement = "insert into `student` (`id`,`password`,`name`,`check`,'otp_check') values(?, ?, ?, ?, ?)";
		
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { id, password,name, check, otp_check}) == 1);
	}
	
	public boolean deleteStudent(int num) {
		
		String sqlStatement = "delete from student where num = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] {num}) == 1);
	}
	
	public boolean updateSutent(Student student) {
		
		int num = student.getNum();
		String name = student.getName();
		int id = student.getId();
		int password = student.getPassword();
		String check = student.getCheck();
		String otp_check = student.getOtp_check();
		
		String sqlStatement = "update `student` set `id` = ?, `password` = ?, `check` = ?,`otp_check` = ?, `name` = ?"
								+" where `num` = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] { id, password, check,otp_check, name, num}) == 1);
	}
	
	public Student getStudentById(int num) {

		String sqlStatement = "select * from student where num = ?"; // id value search

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { num }, new RowMapper<Student>() {

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

				Student student = new Student();
				
				student.setNum(rs.getInt("num"));
				student.setId(rs.getInt("id"));
				student.setPassword(rs.getInt("password"));
				student.setName(rs.getString("name"));
				student.setCheck(rs.getString("check"));
				student.setOtp_check(rs.getString("otp_check"));

				return student;
			}
		});
	}
	
}
