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
		return jdbcTemplate.query(sqlStatement, new RowMapper<Student>(){

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				
				student.setNum(rs.getInt("num"));
				student.setId(rs.getInt("id"));
				student.setPassword(rs.getInt("password"));
				student.setName(rs.getString("name"));
				student.setCheck(rs.getInt("check"));

				
				return student;
			}			
		});
		
	}
}
