package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	
	@Override
	public DistrictEntity byDistrictById(int id) {
	    // TODO Auto-generated method stub
	    String sql = "SELECT * FROM district WHERE id = " + id + ";";
	    DistrictEntity result = new DistrictEntity();
	    try (Connection conn = ConnectionJDBCUtil.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        
	        // Di chuyển con trỏ đến dòng đầu tiên
	        if (rs.next()) {
	            result.setId(rs.getInt("id"));
	            result.setCode(rs.getString("code"));
	            result.setName(rs.getString("name"));
	        } else {
	            // Xử lý trường hợp không tìm thấy dữ liệu với ID này
	            System.out.println("Không tìm thấy district với id = " + id);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}


}
