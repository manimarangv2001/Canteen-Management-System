package com.Hexaware.CMS;

import static org.junit.Assert.assertEquals;

import com.Hexaware.CMS.Model.FoodItem;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * Unit test for Menu class.
 */
public class MenuTest 
{
    static FoodItem menu;
    @BeforeClass
    public static void beforeClass(){
        menu=new FoodItem(101,"Burger",100);
    }
   
    @Test
    public  void testGet(){
       assertEquals(101,menu.getFoodId());
       assertEquals("Burger",menu.getFoodName());
       assertEquals(100,menu.getFoodPrice()); 
    }

    @Test
    public void testToString(){
        String str=menu.toString();
        String expected= "Menu id:"+menu.getFoodId()+"Menu Name:"+menu.getFoodName()+"Menu Price"+menu.getFoodPrice();
        assertEquals(expected,str);
    }
    @AfterClass
    public static void afterClass(){
        menu=null;
    }
}
