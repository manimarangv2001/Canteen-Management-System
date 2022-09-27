package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Hexaware.CMS.Model.FoodItem;

public class FoodItemDB {

	
	public static int insertDb(int Food_Id, String Food_Name, double Food_Price, String Food_Type, int prepTime) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO FOODITEM VALUES (?,?,?,?,?)");  
        stmt.setInt(1,Food_Id);  
        stmt.setString(2,Food_Name);
        stmt.setDouble(3,Food_Price);
        stmt.setString(4, Food_Type);
        stmt.setInt(5, prepTime);
        i = stmt.executeUpdate();   
		return i;
	}
	
	public static boolean FoodIdExist(int Food_Id) throws SQLException {
		boolean exist = false;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FOODITEM WHERE FOOD_ID = ?");  
        stmt.setInt(1,Food_Id);  
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
        	exist = true;
        } 
        return exist;
	}
	
	public static int changeFoodName(int Food_Id, String NewFoodName) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("UPDATE FOODITEM SET FOOD_NAME = ? WHERE FOOD_ID = ?");
		stmt.setString(1, NewFoodName);
		stmt.setInt(2, Food_Id);
		i = stmt.executeUpdate();
		return i;
	}
	
	public static int changeFoodPrice(int Food_Id, double NewFoodPrice) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("UPDATE FOODITEM SET FOOD_PRICE = ? WHERE FOOD_ID = ?");
		stmt.setDouble(1, NewFoodPrice);
		stmt.setInt(2, Food_Id);
		i = stmt.executeUpdate();
		return i;
	}
	
	public static int changeFoodType(int Food_Id, String NewFoodType) throws SQLException {
		int i = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("UPDATE FOODITEM SET FOOD_TYPE = ? WHERE FOOD_ID = ?");
		stmt.setString(1, NewFoodType);
		stmt.setInt(2, Food_Id);
		i = stmt.executeUpdate();
		return i;
	}
	
	
	public static FoodItem[] fetchData() throws SQLException {
		FoodItem[] foodItems = null;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FOODITEM");  
        ResultSet rs = stmt.executeQuery();
		ArrayList<FoodItem> tempArrayList = new ArrayList<>();
		while(rs.next()) {
			tempArrayList.add(new FoodItem(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5)));
		}
		foodItems = new FoodItem[tempArrayList.size()];
		foodItems = tempArrayList.toArray(foodItems);
		
		return foodItems;
	}
	
	public static double fetchFoodPrice(int Food_Id) throws SQLException {
		double Food_Price = 0;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT FOOD_PRICE FROM FOODITEM WHERE FOOD_ID = ?");
		stmt.setInt(1, Food_Id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			Food_Price = rs.getInt(1);
		}
		return Food_Price;
	}
	
	public static String fetchFoodType(int Food_Id) throws SQLException {
		String Food_Type = null;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT FOOD_TYPE FROM FOODITEM WHERE FOOD_ID = ?");
		stmt.setInt(1, Food_Id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			Food_Type = rs.getString(1);
		}
		return Food_Type;
	}
	
	public static int fetchPrepration_Time(int Food_Id) throws SQLException {
		int Estimate_Time = 5;
		Connection connection = ConnectDB.Connect();
		PreparedStatement stmt = connection.prepareStatement("SELECT PREPARATION_TIME FROM FOODITEM WHERE FOOD_ID = ?");
		stmt.setInt(1, Food_Id);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			Estimate_Time = rs.getInt(1);
		}
		return Estimate_Time;
	}
	
}
