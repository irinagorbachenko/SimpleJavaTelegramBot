package com.mycompany.simplejavabot.DatabaseLayer;

import java.sql.DriverManager;//управление версиями драйевров

import java.sql.Connection;//механизмы соединения БД
import java.sql.Statement;//для запросов select (update)
import java.sql.CallableStatement;//для работы с ХП
import java.sql.ResultSet;//хранилище результата выборки
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
                         //select

import java.util.Vector;

public class DataBase 
{

    public Vector<String> getProduct()
    {
        Vector<String> names = new Vector<>(1000);
        //
        try {
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\MyData\\test.db");
            
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT product_id,name,description FROM product");
               
                while (rs.next())
                {
                     int id = rs.getInt("product_id");
                     
                    String str = rs.getString("name");
                    String str1 = rs.getString("description");                    
                    String result = " id = "+id+"     имя = "+str+"      Описание = "+str1+"\n";
                    System.out.println(result);
                    
                    names.add(result);
                   
                }
               conn.close();
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }

       
      
        return names;
    }
    
     public Vector<String> getOrder()
    {
        Vector<String> names = new Vector<>(1000);
       
        try {
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\MyData\\test.db");
            
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT ORDER_ID,CUSTOMER_NAME,amount FROM ORDERS");
                
                while (rs.next())
                {
                    int id = rs.getInt("ORDER_ID");
                    
                    String str = rs.getString("CUSTOMER_NAME");
                    Float amount=rs.getFloat("amount");                  
                    String result = "Номер заказа  id = "+id+" имя  продавца = "+str+" Сумма заказа= "+amount+"\n";
                    System.out.println(result);
                   
                    names.add(result);
                }
               conn.close();
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }

        
      
        return names;
    }
     
     
     public Vector<String> getOrderProducts(String orderId)
    {
        Vector<String> names = new Vector<>(1000);
        //
        try {
           
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\MyData\\test.db");
            
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM product " +
                                                "INNER JOIN ORDERS ON  orders.ORDER_ID=product.order_id " +
                                                " WHERE orders.ORDER_ID=" + orderId);

                while (rs.next())
                {   
                     int id = rs.getInt("product_id");
                     
                    String str = rs.getString("name");
                    int ordId = rs.getInt("ORDER_ID");
                    String result = " id продукта = "+id+"   продукт = "+str+"    Номер заказа = "+ordId+"\n";

                    System.out.println(result);
                    
                    names.add(result);
                   
                }
               conn.close();
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }

        //
      
        return names;
    }
     
     
     public Vector<String> getProductPrice(String productName)
    {
        Vector<String> names = new Vector<>(1000);
        //
        try {
            
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\MyData\\test.db");
            
            String query="SELECT price,description,name FROM product WHERE name LIKE '"+productName+"'";
            System.out.println(query);
           
                    
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
                
                
                 
                while (rs.next())
                {
                      String str = rs.getString("name");                   
                    String str1 = rs.getString("price");
                    String str2 = rs.getString("description");                    
                    String result = " Имя товара = "+str+"    Цена = "+str1+"      Описание = "+str2+"\n";
                    System.out.println(result);
                   
                    names.add(result);
                   
                }
               conn.close();
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }

        //
      
        return names;
    }
}



