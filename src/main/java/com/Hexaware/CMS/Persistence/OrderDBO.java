package com.Hexaware.CMS.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Order;

import java.sql.PreparedStatement;

/**
 * OrderDb class used to connect to data base.
 * @author hexware
 */
public class OrderDBO {
   static int i;
   
    public static int insertDb(int Order_No, String Vendor_Id, String Customer_Id, int Food_Id, int Quantity, double Order_value, String Order_Status, int Estimate_Time) {        
    	int i = 0;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("INSERT INTO ORDERDETAILS VALUES (?,?,?,?,?,CURRENT_TIMESTAMP + INTERVAL ? MINUTE,CURRENT_TIMESTAMP,?,?)");
		
    	stmt.setInt(1, Order_No);
    	stmt.setString(2, Vendor_Id);
    	stmt.setString(3, Customer_Id);
    	stmt.setInt(4, Food_Id);
    	stmt.setInt(5, Quantity);
    	stmt.setInt(6, Estimate_Time);
    	stmt.setDouble(7, Order_value);
    	stmt.setString(8, Order_Status);
    	i = stmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getErrorCode() == 1062) {
				Order_No = OrderFactory.getTokenNum();
				i = insertDb(Order_No, Vendor_Id, Customer_Id, Food_Id, Quantity, Order_value, Order_Status, Estimate_Time);
			}
			e.printStackTrace();
		
		}
		return i;
    }
    
    
    public static double fetchOrderAmount(int Order_Id) throws SQLException {
    	double Order_value = 0;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT ORDER_VALUE FROM ORDERDETAILS WHERE ORDER_ID = ?");
    	stmt.setInt(1, Order_Id);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()) {
    		Order_value = rs.getDouble(1);
    	}
    	return Order_value;
    }
    
    
    public static Order[] fetchDataByOrderId(int Order_Id) throws SQLException {
    	Order orders[] = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERDETAILS WHERE ORDER_ID = ?");
    	stmt.setInt(1, Order_Id);
    	ResultSet rs = stmt.executeQuery();
    	ArrayList<Order> list = new ArrayList<Order>();
    	while(rs.next()) {
    		list.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6),rs.getTimestamp(7),rs.getDouble(8),rs.getString(8)));
    		orders = new Order[list.size()];
            orders = list.toArray(orders);
    	}	
    	
    	return orders;
    }

    public static Order[] fetchDataByVendorId(String Vendor_Id) throws SQLException {
    	Order[] orders = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERDETAILS WHERE VENDOR_ID = ?");
    	stmt.setString(1, Vendor_Id);
    	ResultSet rs = stmt.executeQuery();
    	ArrayList<Order> list = new ArrayList<Order>();
    	while(rs.next()) {
    		list.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6),rs.getTimestamp(7),rs.getDouble(8),rs.getString(9)));
    		
    	}	
    	
    	orders = new Order[list.size()];
        orders = list.toArray(orders);
    	
    	return orders;
    }
    
    public static Order[] fetchDataByCustomerId(String Customer_Id) throws SQLException {
    	Order[] orders = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERDETAILS WHERE CUSTOMER_ID = ?");
    	stmt.setString(1, Customer_Id);
    	ResultSet rs = stmt.executeQuery();
    	ArrayList<Order> list = new ArrayList<Order>();
    	while(rs.next()) {
    		list.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6),rs.getTimestamp(7),rs.getDouble(8),rs.getString(9)));
    		
    	}	
    	orders = new Order[list.size()];
        orders = list.toArray(orders);
    	
    	return orders;
    }
    
    public static int deleteOrderById(int Order_Id) throws SQLException {
    	int i = 0;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("DELETE FROM ORDERDETAILS WHERE ORDER_ID = ?");
    	stmt.setInt(1, Order_Id);
    	i = stmt.executeUpdate();
    	return i;
    }
    
    
    public static String fetchOrderStatusById(int Order_Id) throws SQLException {
    	String Order_Status = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT ORDER_STATUS FROM ORDERDETAILS WHERE ORDER_ID = ?");
    	stmt.setInt(1, Order_Id);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()) {
    		Order_Status = rs.getString(1);
    	}
    	return Order_Status;
    }
    
    
    public static Order[] fetchDataByFoodId(int Food_Id) throws SQLException {
    	Order[] orders = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERDETAILS WHERE FOOD_ID = ?");
    	stmt.setInt(1, Food_Id);
    	ResultSet rs = stmt.executeQuery();
    	ArrayList<Order> list = new ArrayList<Order>();
    	while(rs.next()) {
    		list.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6),rs.getTimestamp(7),rs.getDouble(8),rs.getString(8)));
        	orders = new Order[list.size()];
            orders = list.toArray(orders);

    	}	
    	return orders;
    }
    
    public static int updateOrderStatusFromVendor(int Order_Id, String New_Status) throws SQLException {
    	int i = 0;
    	Connection connetion = ConnectDB.Connect();
    	PreparedStatement stmt = connetion.prepareStatement("UPDATE ORDERDETAILS SET ORDER_STATUS = ? WHERE ORDER_ID = ?");
    	stmt.setString(1, New_Status);
    	stmt.setInt(2, Order_Id);
    	i = stmt.executeUpdate();
    	return i;
    }
    
    public static Order fetchSingleOrderByOrderId(int Order_Id) throws SQLException {
    	Order order = null;
    	Connection connection = ConnectDB.Connect();
    	PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ORDERDETAILS WHERE ORDER_ID = ?");
    	stmt.setInt(1, Order_Id);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()) {
    		order = new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getTimestamp(6),rs.getTimestamp(7),rs.getDouble(8),rs.getString(9));
    	}	
    	
    	return order;
    }
    
    
    
} 