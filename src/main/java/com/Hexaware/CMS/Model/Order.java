package com.Hexaware.CMS.Model;

import java.sql.Timestamp;

public class Order {

	private int Order_No;
	private String Vendor_Id;
	private String Customer_Id;
	private int Food_Id;
	private int Quantity;
	private Timestamp ETA;
	private Timestamp DateAndTime;
	private double Order_value;
	private String Order_Status;
	
	Order() {}
	
	public Order(int Order_No, String Vendor_Id, String Customer_Id, int Food_Id, int Quantity, Timestamp ETA, Timestamp DateAndTime, double Order_value, String Order_Status) {
		this.setOrder_No(Order_No); 
		this.setVendor_Id(Vendor_Id);
		this.setCustomer_Id(Customer_Id);
		this.setFood_Id(Food_Id);
		this.setQuantity(Quantity);
		this.setETA(ETA);
		this.setDateAndTime(DateAndTime);
		this.setOrder_value(Order_value);
		this.setOrder_Status(Order_Status);
	}

	public int getOrder_No() {
		return Order_No;
	}

	public void setOrder_No(int order_No) {
		Order_No = order_No;
	}

	public String getVendor_Id() {
		return Vendor_Id;
	}

	public void setVendor_Id(String vendor_Id) {
		Vendor_Id = vendor_Id;
	}

	public String getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}

	public int getFood_Id() {
		return Food_Id;
	}

	public void setFood_Id(int food_Id) {
		Food_Id = food_Id;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Timestamp getETA() {
		return ETA;
	}

	public void setETA(Timestamp eTA) {
		ETA = eTA;
	}

	public Timestamp getDateAndTime() {
		return DateAndTime;
	}

	public void setDateAndTime(Timestamp dateAndTime) {
		DateAndTime = dateAndTime;
	}

	public double getOrder_value() {
		return Order_value;
	}

	public void setOrder_value(double order_value) {
		Order_value = order_value;
	}

	public String getOrder_Status() {
		return Order_Status;
	}

	public void setOrder_Status(String order_Status) {
		Order_Status = order_Status;
	}
	
}
