package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Building;

@Repository
public class BuildingDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Building> getBuildings(){
		String sqlStatement = "select * from building";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Building>() {
			@Override
			public Building mapRow(ResultSet rs, int rowNum) throws SQLException {

				Building building = new Building();
				
				building.setNum(rs.getInt("num"));
				building.setName(rs.getString("name"));
				building.setNumber(rs.getInt("number"));

				return building;
			}

		});
	}
	
	public boolean addBuilding(Building building) {
		
		String name = building.getName();
		int number = building.getNumber();
		
		String sqlStatement = "insert into `building` (`name`,`number`) values(?, ?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] { name, number}) == 1);
	}
	
	public boolean deleteBuilding(int num) {
		
		String sqlStatement = "delete from `building` where `num` = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] {num}) == 1);
	}
	
	public boolean updateBuilding(Building building) {
		int num = building.getNum();
		String name = building.getName();
		int number = building.getNum();
		
		String sqlStatement = "update `building` set `name` = ?, `number` = ? where `num` = ?";
		
		return (jdbcTemplate.update(sqlStatement,new Object[] { name, number, num}) == 1);
	}

	public Building getBuildingById(int num) {


		String sqlStatement = "select * from `building` where `num` = ?"; // id value search

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { num }, new RowMapper<Building>() {

			@Override
			public Building mapRow(ResultSet rs, int rowNum) throws SQLException {

				Building building = new Building();
				
				building.setNum(rs.getInt("num"));
				building.setName(rs.getString("name"));
				building.setNumber(rs.getInt("number"));

				return building;
			}
		});
	}
}
