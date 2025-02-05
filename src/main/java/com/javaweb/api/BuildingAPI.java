package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

//@Controller
@RestController
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService ; 
	
	@Autowired
	private BuildingRepository buildingRepository; 
	
	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name="typeCode", required = false) List<String> typeCode ) {
		List<BuildingDTO> result = buildingService.findAll(params,typeCode);
	    return result ;
	}
	
	// du lieu khi gui API la nhieu gia tri thi dung List de hung 
	
	@GetMapping(value="/api/building/byName")
	public List<BuildingDTO> getBuildingByName(@RequestParam(name = "name", required = false) String name) {
		List<BuildingDTO> buildings = buildingService.findByName(name);
	    return buildings ;
	}
	@GetMapping(value="/api/building/byFloorArea")
	public List<BuildingDTO> getBuildingByTypeCode(@RequestParam(name = "floorArea", required = false) List<String> floorArea) {
		List<BuildingDTO> buildings = buildingService.findByTypeCode(floorArea);
	    return buildings ;
	}
	
	@RequestMapping(value = "/api/building2/",method = RequestMethod.POST)
    public void getBuilding2(@RequestBody Map<String,Object> params) {
        System.out.println(params);
    }
	
	@RequestMapping(value = "/api/building3/",method = RequestMethod.GET)
    public BuildingDTO getBuilding3(@RequestBody BuildingDTO params) {
        System.out.println(params); // neu in nhu nay thi ket qua se nhu sau : com.javaweb.Beans.BuildingDTO@10ec469f
        return params ;
    }
	
}

