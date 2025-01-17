package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.model.BuildingDTO;


@Service 
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository ; 
	@Override
	public List<BuildingDTO> findAll(String name , Long districtID) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name,districtID);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>() ;
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = new  BuildingDTO() ; 
			building.setName(item.getName());
			building.setAddres(item.getStreet() + "," + item.getWard());
			building.setNumberOfBasement(item.getNumberOfBasement());
			result.add(building);
		}
		return result;
	}
	@Override
	public List<BuildingDTO> findByTypeCode(List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name,districtID);
		// TODO Auto-generated method stub
		return null;
	}

}
