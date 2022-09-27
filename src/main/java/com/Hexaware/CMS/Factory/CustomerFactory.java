package com.Hexaware.CMS.Factory;

import java.sql.SQLException;
import java.util.Random;

import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Persistence.CustomerDB;


public class CustomerFactory {

	public static String AddNewCustomer(String Customer_Name, long Customer_Phone, String Customer_Email, double Customer_WalletBal, String Password) throws SQLException {
		String Customer_Id = GenerateCustomerId();
		String Customer_Coupon = "FIRSTCOUPON";
		int i = CustomerDB.insertDb(Customer_Id, Customer_Name, Customer_Phone, Customer_Email,Customer_Coupon, Customer_WalletBal);
		if(i != 0) {
			LoginFactory.AddNewUser(Customer_Id, Password);
		}
		else {
			Customer_Id = null;
		}
		return Customer_Id;
	}
	
	public static double getWalletBal(String Customer_Id) throws SQLException {
		return CustomerDB.fetchCustomerWalletBal(Customer_Id);
	}
	
	public static int changeWalletBal(String Customer_Id, double newBalance) throws SQLException {
		return CustomerDB.updateCustomerWalletBal(Customer_Id, newBalance);
	}
	
	
	// public static Customer customerProfile(){}
	
	public static Customer CustomerProfile(String Customer_Id) throws SQLException {
		return CustomerDB.fetchData(Customer_Id);
	}
	
	public static String GenerateCustomerId() {
    	Random random = new Random();
    	String Customer_Id = String.valueOf(random.nextInt(100000,999999)) + "@CUSTOMER";
    	return Customer_Id;
    }
	
}
