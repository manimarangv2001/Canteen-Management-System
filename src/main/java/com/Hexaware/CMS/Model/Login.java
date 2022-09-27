package com.Hexaware.CMS.Model;

public class Login {

	private String Login_Id;
	private String Password;
	
	Login(){}
	
	public Login(String Login_Id, String Password){
		this.setLogin_Id(Login_Id);
		this.setPassword(Password);
		
	}

	public String getLogin_Id() {
		return Login_Id;
	}

	public void setLogin_Id(String login_Id) {
		Login_Id = login_Id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
}

//
//Login_Id | varchar(255) | YES  | MUL | NULL    |       |
//| Password | varchar(255)