package com.Hexaware.CMS.Factory;

import java.sql.SQLException;
import java.util.Random;

import com.Hexaware.CMS.Model.FoodItem;
import com.Hexaware.CMS.Persistence.FoodItemDB;

public class FoodItemFactory {

	public static int insertNewFood(int Food_Id, String Food_Name, double Food_Price, String Food_Type, int prepTime) throws SQLException {
		return FoodItemDB.insertDb(Food_Id, Food_Name, Food_Price,Food_Type, prepTime);
	}
	
	public static FoodItem[] fetchMenu() throws SQLException {
		FoodItem menu[]=FoodItemDB.fetchData();
        return menu;
	}
	
	public static double getFoodPrice(int Food_Id) throws SQLException {
		return FoodItemDB.fetchFoodPrice(Food_Id);
	}

	public static String getFoodType(int Food_Id) throws SQLException {
		return FoodItemDB.fetchFoodType(Food_Id);
	}
	
	public static int getEstimate_Time(int Food_Id) throws SQLException {
		return FoodItemDB.fetchPrepration_Time(Food_Id);
	}
	
	public static boolean checkFoodIdExist(int Food_Id) throws SQLException {
		return FoodItemDB.FoodIdExist(Food_Id);
	}
	
	public static int changeFoodName(int Food_Id, String NewFoodName) throws SQLException {
		return FoodItemDB.changeFoodName(Food_Id, NewFoodName);
	}
	
	public static int changeFoodPrice(int Food_Id, double NewFoodPrice) throws SQLException {
		return FoodItemDB.changeFoodPrice(Food_Id, NewFoodPrice);
	}
	
	public static int changeFoodType(int Food_Id, String NewFoodType) throws SQLException {
		return FoodItemDB.changeFoodType(Food_Id, NewFoodType);
	}
	
	public static int getTokenNum() {
    	Random random = new Random();
    	int RandomNum = random.nextInt(100000,999999);
    	return RandomNum;
    }
	
}
