package com.Hexaware.CMS.Model;

public class Customer {
	
	private String Customer_Id;
	private String Customer_Name;
	private long Customer_Phone;
	private String Customer_Email;
	private String Customer_Coupon;
	private String Customer_WalletBal;
	
	
	
	Customer(){}
	
	public Customer(String Customer_Id, String Customer_Name, long Customer_Phone, String Customer_Email, String Customer_Coupon, String Customer_WalletBal){
		this.Customer_Id = Customer_Id;
		this.Customer_Name = Customer_Name;
		this.Customer_Phone = Customer_Phone;
		this.Customer_Email = Customer_Email;
		this.Customer_Coupon = Customer_Coupon;
		this.Customer_WalletBal = Customer_WalletBal;
	}
	
	public String getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}
	public long getCustomer_Phone() {
		return Customer_Phone;
	}
	public void setCustomer_Phone(long customer_Phone) {
		Customer_Phone = customer_Phone;
	}
	public String getCustomer_Email() {
		return Customer_Email;
	}
	public void setCustomer_Email(String customer_Email) {
		Customer_Email = customer_Email;
	}
	public String getCustomer_Coupon() {
		return Customer_Coupon;
	}
	public void setCustomer_Coupon(String customer_Coupon) {
		Customer_Coupon = customer_Coupon;
	}
	public String getCustomer_WalletBal() {
		return Customer_WalletBal;
	}
	public void setCustomer_WalletBal(String customer_WalletBal) {
		Customer_WalletBal = customer_WalletBal;
	}
}


