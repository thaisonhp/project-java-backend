package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.config.ModelMapperConfig;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper ; 
	
	public BuildingDTO toBuildingDTO(BuildingEntity entity) {
		BuildingDTO building = modelMapper.map(entity, BuildingDTO.class);
//		building.setName(entity.getName());
		DistrictEntity de = districtRepository.byDistrictById(entity.getDistrictId());
		building.setAddress(entity.getStreet() + "," + entity.getWard() + "," + de.getName());
		List<RentAreaEntity> raentity = rentAreaRepository.getByBuildingID(entity.getId());
		String typesOfRentArea = raentity.stream().map(i -> i.getValue().toString()).collect(Collectors.joining(","));
		building.setRentedArea(typesOfRentArea);
//		building.setNumberOfBasement(entity.getNumberOfBasement());
//		building.setFloorArea(entity.getFloorArea());
//		building.setPhoneNumber(entity.getManagerPhonenumber());
//		building.setEmptyArea(entity.ge);
//		building.setRentPrice(entity.getRentPrice());
//		building.setServiceFee(entity.getServiceFee());
//		building.setMGfee(entity.getM);
		return building ;
	}
}
