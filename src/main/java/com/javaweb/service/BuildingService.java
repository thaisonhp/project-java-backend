package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String,Object> params , List<String> typeCode) ; 
	List<BuildingDTO> findByTypeCode(List<String> typeCode);
	List<BuildingDTO> findByName(String name);
}
