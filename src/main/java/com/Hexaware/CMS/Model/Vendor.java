package com.Hexaware.CMS.Model;

public class Vendor {
	
	private String Vendor_Id;
	private String Vendor_Name;
	private long Vendor_Phone;
	private String Vendor_Specs;
	
	
	public Vendor(String Vendor_Id, String Vendor_Name, long Vendor_Phone, String Vendor_Specs){
		
		this.Vendor_Id = Vendor_Id; 
		this.Vendor_Name = Vendor_Name;
		this.Vendor_Phone = Vendor_Phone;
		this.Vendor_Specs = Vendor_Specs;
	}
	
	Vendor(){
		
	}
	
	
	public String getVendor_Id() {
		return Vendor_Id;
	}
	public void setVendor_Id(String vendor_Id) {
		Vendor_Id = vendor_Id;
	}
	public String getVendor_Name() {
		return Vendor_Name;
	}
	public void setVendor_Name(String vendor_Name) {
		Vendor_Name = vendor_Name;
	}
	public long getVendor_Phone() {
		return Vendor_Phone;
	}
	public void setVendor_Phone(long vendor_Phone) {
		Vendor_Phone = vendor_Phone;
	}
	public String getVendor_Specs() {
		return Vendor_Specs;
	}
	public void setVendor_Specs(String vendor_Specs) {
		Vendor_Specs = vendor_Specs;
	}
	
}