package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter converter ;

	@Override
	public List<BuildingDTO> findAll( Map<String,Object> params, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity entity : buildingEntities) {
			BuildingDTO building = converter.toBuildingDTO(entity);
			result.add(building);
		}
		return result;
	}

	@Override
	public List<BuildingDTO> findByTypeCode(List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findByTypeCode(typeCode);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuildingDTO> findByName(String name) {
		// TODO Auto-generated method stub
		List<BuildingEntity> buildingEntities = buildingRepository.findByName(name);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity entity : buildingEntities) {
			BuildingDTO building = converter.toBuildingDTO(entity);
			result.add(building);
		}
		return result;
	}

}
