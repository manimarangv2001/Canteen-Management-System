package com.Hexaware.CMS.Factory;

import java.sql.SQLException;
import java.util.Random;

import com.Hexaware.CMS.Model.Order;
import com.Hexaware.CMS.Persistence.OrderDBO;

/**
 * OrderFactory class used to fetch and insert data to database.
 * @author hexware
 */
public class OrderFactory {
	
	
	public static int OrderFood(int Food_Id, int Quantity, String Customer_Id,double Order_Value) throws SQLException {
		int i = 0;
		String Food_Type = FoodItemFactory.getFoodType(Food_Id);
		String SpecVendorId = VendorFactory.getSpecVendor(Food_Type);
		int Estimate_Time = FoodItemFactory.getEstimate_Time(Food_Id);
		String Order_Status = "PENDING";
		if(SpecVendorId == null) {
			System.out.println("There is No "+ Food_Type + "Vendor");
		}
		else {
			int Order_Id = getTokenNum();
			//int Order_No, String Vendor_Id, String Customer_Id, int Food_Id, int Quantity, double Order_value, String Order_Status, int Estimate_Time
			i = OrderDBO.insertDb(Order_Id, SpecVendorId, Customer_Id, Food_Id, Quantity, Order_Value, Order_Status, Estimate_Time);
		}
		return i;
	}
    
    public static int getTokenNum() {
    	Random random = new Random();
    	int RandomNum = random.nextInt(100000,999999);
    	return RandomNum;
    }
    
    public static int cancelOrder(int Order_Id) throws SQLException {
    	return OrderDBO.deleteOrderById(Order_Id);
    }
    
    public static String checkOrderStatus(int Order_Id) throws SQLException {
    	return OrderDBO.fetchOrderStatusById(Order_Id);
    }
    
    // public static Order[] customerOrderHistory(){}
    public static Order[] customerOrderHistory(String Customer_Id) throws SQLException {
    	return OrderDBO.fetchDataByCustomerId(Customer_Id);
    }
    
    // public static Order[] vendorOrderHistory(){}
    public static Order[] vendorOrderHistory(String Vendor_Id) throws SQLException {
    	return OrderDBO.fetchDataByVendorId(Vendor_Id);
    }
    
    public static int changeOrderStatus(int Order_id, String newStatus) throws SQLException {
    	return OrderDBO.updateOrderStatusFromVendor(Order_id, newStatus);
    }
    
    public static double getOrderAmount(int Order_Id) throws SQLException {
    	return OrderDBO.fetchOrderAmount(Order_Id);
    }
    
    public static Order getOrderByOrderId(int Order_Id) throws SQLException {
    	return OrderDBO.fetchSingleOrderByOrderId(Order_Id);
    }
    
}
