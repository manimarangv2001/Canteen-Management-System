package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Hexaware.CMS.Model.Vendor;

public class VendorDB {
	
	public static int insertDb(String Vendor_Id, String Vendor_Name, long Vendor_Phone, String Vendor_Specs) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO VENDOR VALUES (?,?,?,?)");  
        stmt.setString(1,Vendor_Id);  
        stmt.setString(2,Vendor_Name);
        stmt.setLong(3,Vendor_Phone);
        stmt.setString(4,Vendor_Specs);
        i = stmt.executeUpdate();   
		return i;
	}
	
	public static Vendor fetchData(String Vendor_Id) throws SQLException {
		Vendor vendor = null;
		
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM VENDOR WHERE VENDOR_ID = ?");  
        stmt.setString(1,Vendor_Id); 
        ResultSet rs = stmt.executeQuery(); 
		if(rs.next()) {
			vendor = new Vendor(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4));
		}
		return vendor;
	}
	
	public static String getSpecificVendor(String Food_Type) throws SQLException {
		String SpecsVendorId = null;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT VENDOR_ID FROM VENDOR WHERE VENDOR_SPECS = ?");
		stmt.setString(1, Food_Type);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			SpecsVendorId = rs.getString(1);
		}
		
		return SpecsVendorId;
	}
	
	
	
	
}
