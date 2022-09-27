package com.Hexaware.CMS.Factory;

import java.sql.SQLException;
import java.util.Random;

import com.Hexaware.CMS.Model.Vendor;
import com.Hexaware.CMS.Persistence.VendorDB;

public class VendorFactory {

	public static String AddNewVendor(String Vendor_Name, long Vendor_Phone, String Vendor_Specs, String Password) throws SQLException {
		String Vendor_Id = GenerateVendorId();
		int i = VendorDB.insertDb(Vendor_Id, Vendor_Name, Vendor_Phone, Vendor_Specs);
		if(i != 0) {
			LoginFactory.AddNewUser(Vendor_Id, Password);
		}
		else {
			Vendor_Id = null;
		}
		return Vendor_Id;
	}
	
	// public static Vendor vendorProfile(){}
	public static Vendor VendorProfile(String Vendor_Id) throws SQLException {
		return VendorDB.fetchData(Vendor_Id);
	}
	
	public static String getSpecVendor(String Food_Type) throws SQLException {
		return VendorDB.getSpecificVendor(Food_Type);
	}
	
	
	public static String GenerateVendorId() {
    	Random random = new Random();
    	String Vector_Id = String.valueOf(random.nextInt(100000,999999)) + "@VENDOR";
    	return Vector_Id;
    }
	
	// public static String acceptRejectOrder(){}
	
//	public static String acceptRejectOrder() {
//		
//	}
	
	
	
	
	
	
	
}
