package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDb {
	
	public static int insertDb(String Login_Id, String Password) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO LOGIN VALUES (?,?)");  
        stmt.setString(1,Login_Id);  
        stmt.setString(2,Password);
        i = stmt.executeUpdate();   
		return i;
	}
	
	public static boolean CheckUser(String Login_Id, String Password) throws SQLException {
		boolean exist = false;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM LOGIN WHERE LOGIN_ID = ? AND PASSWORD = ?");  
        stmt.setString(1,Login_Id);  
        stmt.setString(2, Password);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	exist = true;
        }
		return exist;
	}
	
	public static int UpdatePassword(String Login_Id, String NewPassword) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("UPDATE LOGIN SET PASSWORD = ? WHERE LOGIN_ID = ?");
		stmt.setString(1, NewPassword);
		stmt.setString(2, Login_Id);
		i = stmt.executeUpdate();
		return i;
	}
	
}
