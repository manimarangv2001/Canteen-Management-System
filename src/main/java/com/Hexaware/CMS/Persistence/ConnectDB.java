package com.Hexaware.CMS.Persistence;

import java.sql.*;

public class ConnectDB {
	
	public static Connection Connect() {
		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMSDB","root","rootpassword"); 
		}
		catch(Exception e){
			System.out.println(e);
		}
		return connection;
	}

}
