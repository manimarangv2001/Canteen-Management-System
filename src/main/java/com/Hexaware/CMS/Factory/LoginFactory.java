package com.Hexaware.CMS.Factory;

import java.sql.SQLException;

import com.Hexaware.CMS.Model.Login;
import com.Hexaware.CMS.Persistence.LoginDb;

public class LoginFactory {

	public static int AddNewUser(String Login_Id, String Password) throws SQLException {
		return LoginDb.insertDb(Login_Id, Password);	
	}
	
	public static boolean CheckUser(Login login) throws SQLException {
		return LoginDb.CheckUser(login.getLogin_Id(), login.getPassword());
	}
	
	
	
	public static int ChangePassword(String Login_Id, String NewPassword) throws SQLException {
		return LoginDb.UpdatePassword(Login_Id, NewPassword);
	}
	
	
	
}
