package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.BuildingDao;
import kr.ac.hansung.model.Building;

@Service
public class BuildingService {

	@Autowired
	private BuildingDao buildingDao;
	
	public List<Building> getBuildings(){
		return buildingDao.getBuildings();
	}
	
	public boolean addBuilding(Building building) {
		return buildingDao.addBuilding(building);
	}
	
	public boolean deleteBuilding(int num) {
		return buildingDao.deleteBuilding(num);
	}
	
	public boolean updateBuilding(Building building) {
		return buildingDao.updateBuilding(building);
	}
	
	public Building getBuildingById(int id) {
		return buildingDao.getBuildingById(id);
	}
}
