package com.Hexaware.CMS.Model;

/**
 * food class used to display food information.
 * @author hexware
 */
public class FoodItem {
    private int foodId;
    private String foodName;
    private double foodPrice;
    private String FoodType;
    private int Preparation_Time;

    public FoodItem(){}

    public FoodItem(int foodId,String foodName, double foodPrice, String FoodType, int Preparation_Time){
        this.foodId=foodId;
        this.foodName=foodName;
        this.foodPrice=foodPrice;
        this.FoodType = FoodType;
        this.Preparation_Time = Preparation_Time;

    }
    public void setFoodId(int foodId){
        this.foodId=foodId;
    }

    public int getFoodId(){
        return foodId;
    }

    public void setFoodName(String foodName){
        this.foodName=foodName;
    }

    public String getFoodName(){
        return foodName;
    }

    public void setFoodPrice(double foodPrice){
        this.foodPrice=foodPrice;
    }

    public double getFoodPrice(){
        return foodPrice;
    }
    public String toString(){
        return "food id:"+foodId+"food Name:"+foodName+"food Price"+foodPrice;
    }

	public String getFoodType() {
		return FoodType;
	}

	public void setFoodType(String foodType) {
		FoodType = foodType;
	}

	public int getPreparation_Time() {
		return Preparation_Time;
	}

	public void setPreparation_Time(int preparation_Time) {
		Preparation_Time = preparation_Time;
	}
}
