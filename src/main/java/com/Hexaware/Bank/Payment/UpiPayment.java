package com.Hexaware.Bank.Payment;

import java.util.Scanner;

import com.Hexaware.Bank.Persistence.UpiPayementDB;

public class UpiPayment {

	static Scanner sc = new Scanner(System.in);
	
	
	public static double getUpiBalance(double desiredAmount) {
		
		return upiProcess(desiredAmount);
	}
	
	public static double upiProcess(double EnterMoney){
		int i = 0;
		String upiId;
		int upiPin;
		
		while(true) {
			System.out.println("UPI Payment Method..");
			System.out.print("Enter Your UPI Id  : ");
			upiId = sc.next();
			System.out.print("Enter Your UPI Pin : ");
			upiPin = sc.nextInt();
			i = UpiPayementDB.CheckUser(upiId, upiPin);
			if(i != 0) {
				break;
			}
			else {
				System.out.println("Enter the Valid UPI Id and Pin");
			}
		}
		
		double balance = UpiPayementDB.fetchBalance(upiId, upiPin);
		System.out.println("Your Balance Amount : "+balance);
		while(true) {
			System.out.print("Are you want to pay (Yes/No) : ");
			String result = sc.next();
			if(result.toLowerCase().equals("yes")) {
				
				if(EnterMoney > balance) {
					System.out.println("You Don't Have Enough Money");
					EnterMoney = 0;
				}
				else {
					balance = balance - EnterMoney;
					UpiPayementDB.updateBal(upiId, upiPin, balance);
					break;
				}
			}
			else {
				break;
			}
		}
		return EnterMoney;
	}
	
}
