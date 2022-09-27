package com.Hexaware.CMS.Cli;

import java.sql.SQLException;
import java.util.Scanner;

import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.FoodItemFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Factory.VendorFactory;
import com.Hexaware.CMS.Model.Login;
import com.Hexaware.CMS.Model.Order;
import com.Hexaware.CMS.Model.Vendor;

public class VendorUtil {

	static Scanner sc = new Scanner(System.in);
	static Vendor vendor;
	public static void VendorProcess(String Vendor_Id) throws SQLException {
		vendor = VendorFactory.VendorProfile(Vendor_Id);
		System.out.println("----------------------------");
        System.out.println("Welcome "+vendor.getVendor_Name());
        System.out.println("----------------------------");
        
        loop1 :
        while(true) {
        	System.out.println("Show Menu                       - 1");
            System.out.println("Accept or Reject Order Requests - 2");
            System.out.println("Add Food to Menu                - 3");
            System.out.println("Edit Menu                       - 4");
            System.out.println("Order History                   - 5");
            System.out.println("Profile                         - 6");
            System.out.println("Change Password                 - 7");
            System.out.println("Log out                         - 8");
            System.out.println("----------------------------");
            System.out.print("Please enter your choice: ");
            int Choice = sc.nextInt();
            
            switch(Choice) {
            
            case 1 -> {
            	//Show Menu
            	CliMain.Menu();
            }
            
            case 2 -> {
            	//Accept or Reject Order Requests
            	System.out.println("Enter the Order id : ");
            	int Order_id = sc.nextInt();
            	Order order = OrderFactory.getOrderByOrderId(Order_id);
            	System.out.println(Order_id + " Status is : "+order.getOrder_Status());
            	System.out.println("Accept the Order : 1"
            				   + "\nReject the Order : 2");
            	System.out.print("Enter Your Choice : ");
            	int Choice1 = sc.nextInt();
            	if(Choice1 == 1) {
            		int i = OrderFactory.changeOrderStatus(Order_id, "ACCEPTED");
            		if(i != 0) {
            			System.out.println("Order Accepted");
            		}
            	}
            	else if(Choice1 == 2) {
            		int i = OrderFactory.changeOrderStatus(Order_id, "REJECTED");
            		if(i != 0) {
            			double returnAmount = OrderFactory.getOrderAmount(Order_id);
    		    		double balanceAmount = CustomerFactory.getWalletBal(order.getCustomer_Id());
    		    		CustomerFactory.changeWalletBal(order.getCustomer_Id(), (returnAmount + balanceAmount));
            			System.out.println("Order Rejected");
            		}
            	}
            	else {
            		System.out.println("Enter the Valid Choice");
            	}
            	
            }
            
            case 3 -> {
            	//Add Food to Menu
//            	insertNewFood(int Food_Id, String Food_Name, double Food_Price, String Food_Type)
            	int i = AddNewFood();
            	if(i != 0) {
            		System.out.println("SuccessFully Added..");
            	}
            	else {
            		System.out.println("Enter the Valid Data And Try Again");
            	}
            	
            }
            case 4 -> {
            	//Edit Menu
            	System.out.println("Enter the Food Id You want to Edit : ");
            	int Food_Id = sc.nextInt();
            	if(FoodItemFactory.checkFoodIdExist(Food_Id)) {
            		System.out.print("Which One You want to Modify \n"
            				+ "\nFood_Name   - 1"
            				+ "\nFood_Price  - 2"
            				+ "\nFood_Type   - 3"
            				+ "\nEnter Your Choice : ");
            		int Choice2 = sc.nextInt();
            		switch(Choice2) {
            		case 1 -> {
            			System.out.print("Enter the New Food Name : ");
            			String NewFoodName = sc.next();
            			int i = FoodItemFactory.changeFoodName(Food_Id, NewFoodName);
            			if(i != 0) {
            				System.out.println("Name Changed SuccessFully");
            			}
            		}
            		case 2 -> {
            			System.out.print("Enter the New Food Price : ");
            			double newFoodPrice = sc.nextDouble();
            			int i = FoodItemFactory.changeFoodPrice(Food_Id, newFoodPrice);
            			if(i != 0) {
            				System.out.println("Food Price Chanced SuccessFully");
            			}
            		}
            		case 3 -> {
            			System.out.print("Enter the Food Type : ");
            			String newFoodType = sc.next();
            			int i = FoodItemFactory.changeFoodType(Food_Id, newFoodType);
            			if(i != 0) {
            				System.out.println("Food Type Chanced SuccessFully");
            			}
            		}
            		default -> {
            			System.out.println("Enter the Valid Choice");
            		}
            		
            		}
            	}
            	else {
            		System.out.println("Enter the Correct Food Id");
            	}
            }
            case 5 -> {
            	//Order History
            	Order[] orders = OrderFactory.vendorOrderHistory(Vendor_Id);
            	CliMain.printFormattedOrders(orders);
            	
            }
            case 6 -> {
            	//Profile
            	System.out.println(""
		    			+ "\nVendor Id             : " + vendor.getVendor_Id()
		    			+ "\nVendor Name           : " + vendor.getVendor_Name()
		    			+ "\nVendor Phone Number   : " + vendor.getVendor_Phone()
		    			+ "\nVendor Specification  : " + vendor.getVendor_Specs()
		    			);
            	
            }
            case 7 -> {
            	//Change Password
            	System.out.print("Enter the New Password : ");
		    	String NewPassword = sc.next();
		    	int i = LoginFactory.ChangePassword(Vendor_Id, NewPassword);
		    	if(i != 0) {
		    		System.out.println("Password Changed SuccessFully");
		    	}
            
            }
            case 8 -> {
            	//Log out
            	break loop1;
            	
            }
            default -> {
            	System.out.println("Enter the Valid Choice");
            }
            
            
            }
        }
	}
	
	public static int AddNewFood() throws SQLException {
		int Food_Id = FoodItemFactory.getTokenNum();
    	System.out.println("---------Add New Food---------");
    	System.out.print("Enter the Food Name          : ");
    	String Food_Name = sc.next();
    	System.out.print("Enter the Food Price         : ");
    	double Food_Price = sc.nextDouble();
    	String Food_Type = vendor.getVendor_Specs();
    	System.out.print("Enter the Preparation Time : ");
    	int prepTime = sc.nextInt();
    	return FoodItemFactory.insertNewFood(Food_Id, Food_Name, Food_Price, Food_Type, prepTime);
	}
	
	
	public static String VendorLogin() throws SQLException {
		String Vendor_Id = null;
		while(true) {
			Login loginCredFromUser = CliMain.getLoginCredential();
	    	boolean exist = LoginFactory.CheckUser(loginCredFromUser);
	    	if(exist) {
	    		System.out.println("SuccessFully Logged In");
	    		Vendor_Id = loginCredFromUser.getLogin_Id();
	    		break;
	    	}
		}
    	return Vendor_Id;
    }
	
	public static void VendorRegistration() throws SQLException {
    	System.out.print("Enter Your Name          : ");
		String Vendor_Name = sc.next();
		System.out.print("Enter Your Phone Number  : ");
		long Vendor_Phone = sc.nextLong();
		System.out.print("Enter Your Specification : ");
		String Vendor_Specs = sc.next();
		System.out.print("Enter the New Password   : ");
		String Password = sc.next();
		
		String Vendor_Id = VendorFactory.AddNewVendor(Vendor_Name, Vendor_Phone,Vendor_Specs, Password);
		if(Vendor_Id != null) {
			System.out.println("Registered SuccessFull");
			System.out.println("Your Login Id is "+Vendor_Id);
		}
    }
	
}
