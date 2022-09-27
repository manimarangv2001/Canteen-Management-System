# Execute the following query

## Create Database
create database CMSDB;

use cmsdb;

## OrderDetails table

create table OrderDetails(Order_No int primary key, Vendor_Id int not null, Customer_Id varchar(255) not null, Food_Id int not null, Quantity int, ETA datetime, DateAndTime datetime, Order_value decimal(5,2), Order_Status varchar(10),
constraint vendor_id_fk foreign key (vendor_id) references vendor(vendor_id),
constraint customer_id_fk foreign key (customer_id) references customer(customer_id),
constraint food_id_fk foreign key (food_id) references fooditem(food_id));


## FoodItem table

create table FoodItem(Food_Id int primary key, Food_Name varchar(25) not null, Food_Price decimal(5,2));


## Login Table

create table Login(Login_Id varchar(255), Password varchar(255) not null, constraint login_id_fk foreign key (Login_Id) references customer(Customer_Id));(Don't Use this)

create table Login(Login_Id varchar(255) primary key, Password varchar(255) not null);


## CustomerProfile

create table customer(Customer_Id varchar(255) primary key,
Customer_Name varchar(255) not null,
Customer_Phone bigint,
Customer_Email varchar(255),
Customer_Coupon varchar(255),
Customer_WalletBal decimal(5,2));

## VendorProfile

create table Vendor(Vendor_Id int primary key, Vendor_Name varchar(25) not null, Vendor_Phone bigint, Vendor_Specs varchar(25));


SELECT * FROM VENDOR;
SELECT * FROM CUSTOMER;
SELECT * FROM LOGIN;
SELECT * FROM FOODITEM;
SELECT * FROM ORDERDETAILS;


SELECT * FROM LOGIN WHERE LOGIN_ID = '741486@CUSTOMER' AND PASSWORD = 'maran@1234';

DELETE FROM LOGIN;
DELETE FROM ORDERDETAILS;
DELETE FROM VENDOR;
DELETE FROM CUSTOMER;
DELETE FROM FOODITEM;


DESC LOGIN;
DESC ORDERDETAILS;
DESC VENDOR;
DESC CUSTOMER;
DESC FOODITEM;



