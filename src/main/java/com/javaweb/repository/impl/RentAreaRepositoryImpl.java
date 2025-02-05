package com.javaweb.repository.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	
	
	@Override
	public List<RentAreaEntity> getByBuildingID(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM rentarea WHERE buildingid = " + id +" ; ";
		List<RentAreaEntity> result = new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql)){
			while(rs.next()) {
				RentAreaEntity entity = new RentAreaEntity();
				entity.setId(rs.getInt("id"));
				entity.setValue(rs.getString("value"));
				entity.setBuildingId(rs.getInt("buildingid"));
				entity.setCreatedDate(rs.getString("createddate"));
				entity.setModifiedBy(rs.getString("modifiedby"));
				entity.setCreatedBy(rs.getString("createdby"));
				entity.setModifiedDate(rs.getString("modifieddate"));
				result.add(entity);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
}
