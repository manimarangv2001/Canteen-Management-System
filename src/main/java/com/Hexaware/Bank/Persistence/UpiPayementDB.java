package com.Hexaware.Bank.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpiPayementDB {

	public static double fetchBalance(String upiId, int upiPin) {
		double balance = 0;
		
		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","rootpassword"); 
            PreparedStatement stmt = connection.prepareStatement("SELECT BALANCE FROM UPIPAYMENT WHERE UPIID = ? AND UPIPIN = ?");
            stmt.setString(1, upiId);
            stmt.setInt(2, upiPin);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	balance = rs.getDouble(1);
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return balance;
	}
	
	public static int updateBal(String upiId, int upiPin, double balance) {
		int i = 0;
		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","rootpassword"); 
            PreparedStatement stmt = connection.prepareStatement("UPDATE UPIPAYMENT SET BALANCE = ? WHERE UPIID = ? AND UPIPIN = ?");
            stmt.setDouble(1, balance);
            stmt.setString(2, upiId);
            stmt.setInt(3, upiPin);
            i = stmt.executeUpdate();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	
	public static int CheckUser(String upiId, int upiPin) {
		int i = 0;
		
		Connection connection = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","rootpassword"); 
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UPIPAYMENT WHERE UPIID = ? AND UPIPIN = ?");
            stmt.setString(1, upiId);
            stmt.setInt(2, upiPin);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
            	i = 1;
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	
}
