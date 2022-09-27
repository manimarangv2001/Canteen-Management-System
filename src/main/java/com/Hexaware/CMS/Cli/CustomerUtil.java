package com.Hexaware.CMS.Cli;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import com.Hexaware.Bank.Payment.UpiPayment;
import com.Hexaware.CMS.Factory.CustomerFactory;
import com.Hexaware.CMS.Factory.LoginFactory;
import com.Hexaware.CMS.Factory.OrderFactory;
import com.Hexaware.CMS.Model.Customer;
import com.Hexaware.CMS.Model.Login;
import com.Hexaware.CMS.Model.Order;

public class CustomerUtil {

	static Scanner sc = new Scanner(System.in);
	
	public static void CustomerProcess(String Customer_Id) throws SQLException {
		Customer customer = CustomerFactory.CustomerProfile(Customer_Id);
		loop1:
		while(true) {
			System.out.println("-----------------------------");
		    System.out.println("Welcome "+customer.getCustomer_Name());
		    System.out.println("-----------------------------");
		    System.out.println("Show Menu           - 1");
		    System.out.println("Placing Order       - 2");
		    System.out.println("View Profile        - 3");
		    System.out.println("View Wallet Balance - 4");
		    System.out.println("View Order History  - 5");
		    System.out.println("Cancel Order        - 6");
		    System.out.println("Check Order Status  - 7");
		    System.out.println("Change Password     - 8");
		    System.out.println("Add Wallet Amount   - 9");
		    System.out.println("LogOut              - 10");
		    System.out.println("-----------------------------");
		    System.out.print( " Enter your choice : " );
		    int Choice = sc.nextInt();
		    
		    switch(Choice) {
		    
		    case 1 -> {
		    	CliMain.Menu();
		    }
		    case 2 -> {
		    	CliMain.PlaceOrder(Customer_Id);
		    }
		    
//		    String Customer_Id, String Customer_Name, long Customer_Phone, String Customer_Email, String Customer_Coupon, String Customer_WalletBal
		    
		    case 3 -> {
		    	System.out.println(""
		    			+ "\nCustomer Id           : " + customer.getCustomer_Id()
		    			+ "\nCustomer Name         : " + customer.getCustomer_Name()
		    			+ "\nCustomer Phone Number : " + customer.getCustomer_Phone()
		    			+ "\nCustomer Email Id     : " + customer.getCustomer_Email()
		    			+ "\nCustomer Coupon       : " + customer.getCustomer_Coupon()
		    			);
		    }
		    
		    case 4 -> {
		    	System.out.println("Customer Available Wallet Balance : " + customer.getCustomer_WalletBal());
		    }
//		    int Order_No, String Vendor_Id, String Customer_Id, int Food_Id, int Quantity, Timestamp ETA, Timestamp DateAndTime, double Order_value, String Order_Status
		    case 5 -> {
		    	Order[] orders = OrderFactory.customerOrderHistory(Customer_Id);
		    	CliMain.printFormattedOrders(orders);
		    }
		    
		    case 6 -> {
		    	System.out.print("Enter the Order Id you want cancel : ");
		    	int Order_Id = sc.nextInt();
		    	int i = OrderFactory.changeOrderStatus(Order_Id, "CANCELLED");
		    	if(i != 0) {
		    		double returnAmount = OrderFactory.getOrderAmount(Order_Id);
		    		double balanceAmount = CustomerFactory.getWalletBal(Customer_Id);
		    		CustomerFactory.changeWalletBal(Customer_Id, (returnAmount + balanceAmount));
		    		System.out.println("Your Order Cancelled and the Order Amount is Returned");
		    	}
		    	else {
		    		System.out.println("Cancellation Failed. Enter the Correct Order Id");
		    	}
		    }
		    
		    case 7 -> {
		    	System.out.print("Enter the Order Id : ");
		    	int Order_Id = sc.nextInt();
		    	String Customer_Status = OrderFactory.checkOrderStatus(Order_Id);
		    	if(Customer_Status != null) {
		    		System.out.println("Your Order Status is : "+Customer_Status);
		    	}
		    	else {
		    		System.out.println("Enter the Correct Order Id");
		    	}
		    }
		    
		    case 8 -> {
		    	System.out.print("Enter the New Password : ");
		    	String NewPassword = sc.next();
		    	int i = LoginFactory.ChangePassword(Customer_Id, NewPassword);
		    	if(i != 0) {
		    		System.out.println("Password Changed SuccessFully");
		    	}
		    }
		    
		    case 9 -> {
		    	double Customer_NewAmount = AddAmount(0);
		    	System.out.println(Customer_NewAmount);
		    	CustomerFactory.changeWalletBal(Customer_Id, Customer_NewAmount);
		    	System.out.println("Added SuccessFully");	
		    }   
		    case 10 -> {
		    	break loop1;
		    }
		    }
		}
	}
	
	public static void CustomerRegistration() throws SQLException {
		System.out.println();
    	System.out.print("Enter Your Name          : ");
		String Customer_Name = sc.next();
		System.out.print("Enter Your Phone Number  : ");
		long Customer_Phone = sc.nextLong();
		System.out.print("Enter Your Email Id      : ");
		String Customer_Email = sc.next();
		System.out.print("Enter the New Password   : ");
		String Password = sc.next();
		double Customer_WalletBal = AddAmount(0);
		String Customer_Id = CustomerFactory.AddNewCustomer(Customer_Name, Customer_Phone,Customer_Email, Customer_WalletBal, Password);
		if(Customer_Id != null) {
			System.out.println("Registered SuccessFull");
			System.out.println("Your Login Id is "+Customer_Id);
		}
		System.out.println();
    }
	
	public static double AddAmount(double Customer_WalletBal) {
		System.out.print("Are You Want to Add Money To Your Wallet (Yes/No): ");
		String Choice1 = sc.next();
		if(Choice1.toLowerCase().equals("yes")) {
			
			if(Customer_WalletBal == 0) {
				System.out.print("How Much Many You Want : ");
				Customer_WalletBal = sc.nextDouble();
			}
			Customer_WalletBal = UpiPayment.getUpiBalance(Customer_WalletBal);
			System.out.println("Credited SuccessFully");
		}
		else {
			System.out.println("Your Wallet Amount is "+ Customer_WalletBal);
		}
		return Customer_WalletBal;
	}
	
	public static String CustomerLogin() throws SQLException {
		String Customer_Id = null;
		while(true) {
			Login loginCredFromUser = CliMain.getLoginCredential();
	    	boolean exist = LoginFactory.CheckUser(loginCredFromUser);
	    	if(exist) {
	    		System.out.println("SuccessFully Logged In");
	    		Customer_Id = loginCredFromUser.getLogin_Id();
	    		break;
	    	}
		}
		return Customer_Id;
    }
}
