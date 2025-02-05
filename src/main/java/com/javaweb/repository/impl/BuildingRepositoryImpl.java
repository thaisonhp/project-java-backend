package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.StringUtil;
import com.javaweb.utils.numberUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	
	
	public static void joinTable(Map<String,Object> params,List<String> typeCode, StringBuilder sql) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkNullString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding asb on asb.buildingId = building.id ");	
		}
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON buildingrenttype.buildingid = building.id ");
			sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
		String rentAreaFrom = (String)params.get("rentAreaFrom") ; 
		String rentAreaTo   = (String)params.get("rentAreaTo") ; 
		if(StringUtil.checkNullString(rentAreaTo) || StringUtil.checkNullString(rentAreaFrom)) {
			sql.append(" INNER JOIN rentarea ON rentarea.buildingid = building.id ");
		}
		
	}
	
	public static void querryNormal(Map<String,Object> params,List<String> typeCode,StringBuilder where) {
		for(Map.Entry<String, Object> it :params.entrySet()) {
			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode")
			&& !it.getKey().startsWith("rentArea") && !it.getKey().startsWith("rentPrice")) {
				// kiem tra du lieu la xau hay so 
				String value = it.getValue().toString() ;
				if(StringUtil.checkNullString(value)) {
					if(numberUtil.isNumber(value)) {
						where.append(" AND building." + it.getKey() + " = " + value + " ");
					}else {
						where.append(" AND building." + it.getKey() + " like '%" + it.getValue() + "%' ");
					}
				}
			}
		}
	}
	
	public static void querrySpecial(Map<String,Object> params,List<String> typeCode ,StringBuilder where) {
		// tim kiem theo id 
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkNullString(staffId)) {
			where.append(" and assignmentbuilding.staffid = " + staffId) ;
		}
		// tim kiem theo rentArea
		String rentAreaFrom = (String)params.get("rentAreaFrom") ; 
		String rentAreaTo   = (String)params.get("rentAreaTo") ;
		
		if (StringUtil.checkNullString(rentAreaFrom)) {
			where.append(" and rentarea.value >= " + rentAreaFrom);
		}else if (StringUtil.checkNullString(rentAreaTo)) {
			where.append(" and rentarea.value <= " + rentAreaTo);
		}
		// tim kiem theo rentPrice
		String rentPriceFrom = (String)params.get("rentPriceFrom") ; 
		String rentPriceTo   = (String)params.get("rentPriceTo") ; 
		
		if (StringUtil.checkNullString(rentPriceFrom)) {
			where.append(" and building.rentprice >= " + rentPriceFrom);
		}
		if (StringUtil.checkNullString(rentPriceTo)) {
			where.append(" and building.rentprice <= " + rentPriceTo);
		}
		
		// tim kiem theo renttype 
		if(typeCode != null && typeCode.size() != 0) {
//			where.append(" and typecode.code IN(" + String.join(",", typeCode) + ") ");
			List<String> code = new ArrayList<>() ; 
			for(String item : typeCode) {
				code.add("'" + item + "'");
			}
			where.append(" AND typecode.code IN (" + String.join(",", code));
		}
		
	}
	
	
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params,List<String> typeCode) {
		System.out.println("api duoc goi");
	    StringBuilder sql =  new StringBuilder("SELECT * FROM building " );
	    BuildingRepositoryImpl.joinTable(params, typeCode, sql);
	    StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
	    List<BuildingEntity> result = new ArrayList<>();
	    BuildingRepositoryImpl.querryNormal(params, typeCode, where);
	    BuildingRepositoryImpl.querrySpecial(params, typeCode, where);
	    sql.append(where.toString());
	    try (Connection conn = ConnectionJDBCUtil.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql.toString())) {
	        
	        while (rs.next()) {
	        	BuildingEntity building = new BuildingEntity();
                building.setId(rs.getInt("id"));
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                building.setDistrictId(rs.getInt("districtid"));
                building.setStructure(rs.getString("structure"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setDirection(rs.getString("direction"));
                building.setLevel(rs.getDouble("level"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setRentpricedescription(rs.getString("rentpricedescription"));;
                building.setServiceFee(rs.getDouble("servicefee"));
                building.setCarFee(rs.getDouble("carfee"));
                building.setMotorBikeFee(rs.getDouble("motorbikefee"));;
                building.setOvertimeFee(rs.getDouble("overtimefee"));
                building.setWaterFee(rs.getDouble("waterfee"));
                building.setElectricityFee(rs.getDouble("electricityfee"));
                building.setDeposit(rs.getString("deposit"));
                building.setPayment(rs.getDouble("payment"));
                building.setRentTime(rs.getString("renttime"));
                building.setDecorationTime(rs.getString("decorationtime"));
                building.setBrokerageFee(rs.getDouble("brokeragefee"));
                building.setNote(rs.getString("note"));
                building.setLinkOfBuilding(rs.getString("linkofbuilding"));
                building.setMap(rs.getString("map"));
                building.setImage(rs.getString("image"));
                building.setCreatedDate(rs.getString("createddate"));
                building.setModifiedDate(rs.getString("modifieddate"));
                building.setCreatedBy(rs.getString("createdby"));
                building.setModifiedby(rs.getString("modifiedby"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhonenumber(rs.getString("managerphonenumber"));
                result.add(building);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result ; 
	}
	
	
	@Override
	public List<BuildingEntity> findByTypeCode(List<String> typeCode) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<BuildingEntity> findByName(String name) {
		System.out.print("da goi findByName");
		// TODO Auto-generated method stub
		// Buoc 1 : viet cau lenh SQL de truy van trong database : lay tat ca toa nha co ten {name} tu bang name
		StringBuilder sql = new StringBuilder("SELECT * FROM building WHERE 1 = 1 ");
		if(name != null && name != "") {
			sql.append("AND building.name like '%" + name + "%' ");	
		}
		
		List<BuildingEntity> result = new ArrayList<>() ;
		try(Connection conn = ConnectionJDBCUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString())){
			// code o day 
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
                building.setId(rs.getInt("id"));
                building.setName(rs.getString("name"));
                building.setStreet(rs.getString("street"));
                building.setWard(rs.getString("ward"));
                building.setDistrictId(rs.getInt("districtid"));
                building.setStructure(rs.getString("structure"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setDirection(rs.getString("direction"));
                building.setLevel(rs.getDouble("level"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setRentpricedescription(rs.getString("rentpricedescription"));;
                building.setServiceFee(rs.getDouble("servicefee"));
                building.setCarFee(rs.getDouble("carfee"));
                building.setMotorBikeFee(rs.getDouble("motorbikefee"));;
                building.setOvertimeFee(rs.getDouble("overtimefee"));
                building.setWaterFee(rs.getDouble("waterfee"));
                building.setElectricityFee(rs.getDouble("electricityfee"));
                building.setDeposit(rs.getString("deposit"));
                building.setPayment(rs.getDouble("payment"));
                building.setRentTime(rs.getString("renttime"));
                building.setDecorationTime(rs.getString("decorationtime"));
                building.setBrokerageFee(rs.getDouble("brokeragefee"));
                building.setNote(rs.getString("note"));
                building.setLinkOfBuilding(rs.getString("linkofbuilding"));
                building.setMap(rs.getString("map"));
                building.setImage(rs.getString("image"));
                building.setCreatedDate(rs.getString("createddate"));
                building.setModifiedDate(rs.getString("modifieddate"));
                building.setCreatedBy(rs.getString("createdby"));
                building.setModifiedby(rs.getString("modifiedby"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhonenumber(rs.getString("managerphonenumber"));
                result.add(building);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
