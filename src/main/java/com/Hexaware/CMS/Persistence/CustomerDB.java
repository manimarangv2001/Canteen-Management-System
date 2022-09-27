package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Hexaware.CMS.Model.Customer;


public class CustomerDB {

	public static int insertDb(String Customer_Id, String Customer_Name, long Customer_Phone, String Customer_Email, String Customer_Coupon, double Customer_WalletBal) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?)");  
        stmt.setString(1,Customer_Id);  
        stmt.setString(2,Customer_Name);
        stmt.setLong(3,Customer_Phone);
        stmt.setString(4,Customer_Email);
        stmt.setString(5,Customer_Coupon);
        stmt.setDouble(6,Customer_WalletBal);
        i = stmt.executeUpdate();   
		return i;
	}
	
	public static double fetchCustomerWalletBal(String Customer_Id) throws SQLException {
		double balance = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT CUSTOMER_WALLETBAL FROM CUSTOMER WHERE CUSTOMER_ID = ?");
		stmt.setString(1, Customer_Id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			balance = rs.getDouble(1);
		}
		return balance;
	}
	
	public static int updateCustomerWalletBal(String Customer_Id, double newBalance) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("UPDATE CUSTOMER SET CUSTOMER_WALLETBAL = ? WHERE CUSTOMER_ID = ?");
		stmt.setDouble(1, newBalance);
		stmt.setString(2, Customer_Id);
		i = stmt.executeUpdate();
		return i;
	}
	
	public static Customer fetchData(String Customer_Id) throws SQLException {
		Customer customer = null;
		
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?");  
        stmt.setString(1,Customer_Id); 
        ResultSet rs = stmt.executeQuery(); 
		if(rs.next()) {
			customer = new Customer(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5), rs.getString(6));
		}
		return customer;
	}
	
}
