package com.Hexaware.CMS.Cli;

import java.sql.SQLException;
import java.util.Scanner;

import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.FoodItemFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.FoodItem;
import com.Hexaware.CMS.Model.Login;
import com.Hexaware.CMS.Model.Order;

/**
 * CliMain used as Client interface for java coding.
 * @author hexaware
 */
public class CliMain {
    
    static Scanner sc = new Scanner(System.in);
    static boolean checkLoggedOrNot = false; 

/**
 * main method  used to display the option we had in the application.
 * @throws SQLException 
 */
    public static void main( String[] args ) throws SQLException
    {      
        System.out.println( "----------------Canteen Management System----------------" );      
  
        while(true) {
	        System.out.println("Menu       - 1");
	        System.out.println("PlaceOrder - 2");
	        System.out.println("Login      - 3");
	        System.out.println("SignUp     - 4");
	        System.out.println("Exit       - 5");
	        
	        System.out.print("Enter your choice : ");
	        int choice=sc.nextInt();
	        System.out.println( "-------------------------------------------------------- " );
	        
	        switch(choice) {
	        
	        case 1 -> {
	        	Menu();
	        }
	        case 2 ->{
	        	PlaceOrder(null);
	        }
	        case 3 ->{
	        	String Login_Id = LoginSection();
	        	if(Login_Id.substring(Login_Id.lastIndexOf('@')+1,Login_Id.length()).equals("VENDOR")) {
	        		VendorUtil.VendorProcess(Login_Id);
	        	}
	        	else if(Login_Id.substring(Login_Id.lastIndexOf('@')+1,Login_Id.length()).equals("CUSTOMER")) {
	        		CustomerUtil.CustomerProcess(Login_Id);
	        	}
	        }
	        case 4 ->{
	        	registerNewUser();
	        }
	        case 5 ->{
	        	System.out.println("Thanks For Using Canteen Management System");
	        	System.exit(0);
	        }
	        default -> {
	        	System.out.println("Enter the Valid Choice");
	        }
	        }
        }        
    }
    
    
    public static void Menu() throws SQLException {
    	FoodItem[] menu = FoodItemFactory.fetchMenu();												 
    	System.out.println("-------------------------------------------------------------------------------------------"); 
    	System.out.format("| %15s | %15s | %15s | %15s | %15s |", "Food_Id","Food_Name","Food_Price","FoodType","Prepration_Time");
    	System.out.println();
    	System.out.println("-------------------------------------------------------------------------------------------"); 
    	
    	for(int i=0;i<menu.length;i++) {
    		FoodItem foodItem = menu[i];
    		System.out.format("| %15s | %15s | %15s | %15s | %15s |",foodItem.getFoodId(),foodItem.getFoodName(),foodItem.getFoodPrice(),foodItem.getFoodType(),foodItem.getPreparation_Time());
    		System.out.println();
    		System.out.println("-------------------------------------------------------------------------------------------"); 
    		
    	}
    }
    
    public static boolean balanceCheckAndPay(double Customer_WalletBal,double Order_Value, String Customer_Id) throws SQLException {
    	boolean result = false;
    	double NewWallAmount = 0;
    	if(Customer_WalletBal < Order_Value) {
			System.out.println("You Don't Have Enough Balance In you Wallet");
			double desiredAmount = Order_Value - Customer_WalletBal + 1;
			double BalanceAmount = CustomerUtil.AddAmount(desiredAmount);
			NewWallAmount = Customer_WalletBal + BalanceAmount;
		}
    	else {
    		NewWallAmount = Customer_WalletBal;
    	}
    	
    	NewWallAmount = NewWallAmount - Order_Value;
    	int i = CustomerFactory.changeWalletBal(Customer_Id, NewWallAmount);
		if(i != 0) {
			result = true;
		}
		
		return result;
    }
    
    public static void PlaceOrder(String Customer_Id) throws SQLException {
    	System.out.println("------------------Order Your Food Here------------------");
    	while(true) {
    		System.out.println();
    		System.out.print("You Want to Order Food (Yes/No) : ");
    		String result = sc.next();
    		if(result.toLowerCase().equals("yes")) {
    			System.out.print("Enter the Food_Id               : ");
            	int Food_Id = sc.nextInt();
            	System.out.print("Enter the Food_Quantity         : ");
            	int Quantity = sc.nextInt();
            	double Food_Price = FoodItemFactory.getFoodPrice(Food_Id);
            	double Order_Value = Food_Price * (double)Quantity ;
            	if(Customer_Id == null) {
            		Customer_Id = LoginProcess();
            	}
        		double Customer_WalletBal = CustomerFactory.getWalletBal(Customer_Id);
        		if(balanceCheckAndPay(Customer_WalletBal, Order_Value, Customer_Id)) {
        			System.out.println("Amount Paid SuccessFully");
        			int i = OrderFactory.OrderFood(Food_Id, Quantity, Customer_Id,Order_Value);
            		checkLoggedOrNot = true;
            		if(i != 0) {
            			
            			CustomerFactory.changeWalletBal(Customer_Id, (Customer_WalletBal - Order_Value));
            			System.out.println("Ordered Successfully");
            		}
            		else {
            			System.out.println("Oops! Order Failed");
            			System.out.println("Order Something New ..");
            		}
        		}
    		}
    		else {
    			CustomerUtil.CustomerProcess(Customer_Id);
    			break;
    		}	
    	}
    }
    
    public static String LoginProcess() throws SQLException {
    	System.out.println();
    	System.out.println("-----------Before Order the Food First LogIn-----------"
    			+ "\nIf You are New To CMS Register First"
    			+ "\n"
    			+ "\nRegister New User            : 1"
    			+ "\nLogin                        : 2\n");
    	System.out.print("Enter your Choice : ");
    	int choice = sc.nextInt();
    	String Customer_Id = null;
    	
    	if(choice == 1) {
    		CustomerUtil.CustomerRegistration();
    		Customer_Id = CustomerUtil.CustomerLogin();
    	}
    	else if(choice == 2) {
    		Customer_Id = CustomerUtil.CustomerLogin();
    	}
    	else {
    		System.out.println("Enter the Valid Number..");
    	}
    	return Customer_Id;
    }
    
        
    public static void registerNewUser() throws SQLException {
    	System.out.println("I am a Customer   - 1");
    	System.out.println("I am a Vendor     - 2");
    	System.out.print("Who You Are .. : ");
    	int choice = sc.nextInt();
    	if(choice == 1) {
    		CustomerUtil.CustomerRegistration();
    	}
    	else if(choice == 2) {
    		VendorUtil.VendorRegistration();
    	}
    	else {
    		System.out.println("Enter the Correct Choice...");
    	}    	
    }
    
    public static Login getLoginCredential() {
    	
    	System.out.print("Enter the Login_Id              : ");
    	String Login_Id = sc.next();
    	System.out.print("Enter the Password              : ");
    	String Password = sc.next();
    	Login login = new Login(Login_Id, Password);
    	return login;
    }
    
    public static void printFormattedOrders(Order[] orders) {
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	System.out.format("| %10s | %15s | %15s | %10s | %10s | %22s | %22s | %15s | %15s |" , "Order_Id","Vendor_Id","Customer_Id","Food_Id","Quantity","ETA","DateAndTime","Order_value","Order_Status" );
    	System.out.println();
    	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	for(Order order : orders) {
    		System.out.format("| %10s | %15s | %15s | %10s | %10s | %22s | %22s | %15s | %15s |" ,order.getOrder_No(),
    				order.getVendor_Id(),
    				order.getCustomer_Id(),
    				order.getFood_Id(),
    				order.getQuantity(),
    				order.getETA(),
    				order.getDateAndTime(),
    				order.getOrder_value(),
    				order.getOrder_Status()
    				);
    		System.out.println();
    		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	}
    }
    public static String LoginSection() throws SQLException {
		String Login_Id = null;
		while(true) {
			Login loginCredFromUser = CliMain.getLoginCredential();
	    	boolean exist = LoginFactory.CheckUser(loginCredFromUser);
	    	if(exist) {
	    		System.out.println("SuccessFully Logged In");
	    		Login_Id = loginCredFromUser.getLogin_Id();
	    		break;
	    	}
		}
		return Login_Id;
    }
}


