package com.cjc.bms.app.serviceimpl;
import com.cjc.bms.app.model.Account;
import com.cjc.bms.app.servicei.Rbi;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;

public class Sbi implements Rbi {
       Scanner sc=new Scanner(System.in);
       Account a=new Account();
	@Override
	public void createAccount() throws Exception{
	
		System.out.println("create account");
		
		System.out.println("Enter 10 digits account number ");
		String accno=sc.next();
		a.setAccNo(accno);
		
		System.out.println("Enter name");
		String name=sc.next();
		a.setName(name);
		
		System.out.println("Enter mobno");
		String mobno=sc.next();
		a.setMobNo(mobno);
		
		System.out.println("Enter adharno");
		String adharno=sc.next();
		a.setAdharNo(adharno);
		
		System.out.println("Enter gender");
		String gender=sc.next();
		a.setGender(gender);
		
		System.out.println("Enter age");
		int age=sc.nextInt();
		a.setAge(age);
		
		System.out.println("Enter balance");
		double balance=sc.nextDouble();
		a.setBalance(balance);
	     
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver class loaded successfully");
		
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db", "root", "*password*");
	    System.out.println("connection get established");
	    
 String sql="insert into accounts values ("+a.getAccNo()+", '"+a.getName()+"', '"+a.getMobNo()+"', '"+a.getAdharNo()+"', '"+a.getGender()+"',"
	    		+ " "+a.getAge()+", "+a.getBalance()+")";
	    System.out.println("construct sql query");
	    
	    Statement smt=con.createStatement();
	    System.out.println("create object of statement");
	    
	    smt.executeUpdate(sql);
	    System.out.println("execute sql query");
	    
	    smt.close();
	    con.close();
	    System.out.println("Account created successfully...!");
		
	}

	@Override
	public void displayAllDetails()throws Exception {
		
		System.out.println("Display all details...!");
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver class loaded successfully");
		
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db", "root", "*password*");
	    System.out.println("connection get established");
	    
	    String sql="select * from accounts";
	    System.out.println("construct sql query");
	    
	    Statement smt=con.createStatement();
	    System.out.println("create object of statement");
	    
	           ResultSet rs=smt.executeQuery(sql);
	           System.out.println("execute sql query");
	           
	           while(rs.next()) {
	        	  
	        	   System.out.println("account holder accno- " + rs.getString(1));
	        	   System.out.println("account holder name - " + rs.getString(2));
	        	   System.out.println("account holder mobno- " + rs.getString(3));
	        	   System.out.println("account holder adharno- " + rs.getString(4));
	        	   System.out.println("account holder gender- " + rs.getString(5));
	        	   System.out.println("account holder age- " + rs.getInt(6));
	        	   System.out.println("account holder balance- " + rs.getDouble(7));
	           }
		
	            smt.close();
	            con.close();
	            
	}

	@Override
	public void depositeMoney() throws Exception{
		
		
		System.out.println("Enter account no");
		String accnum=sc.next();
		
		if(accnum.length()!=10) { 
			System.out.println( "please enter 10 digits valid account number");
			return;	
		}	
		
		System.out.println("Enter amount");
		double depositeamount=sc.nextDouble();
		
		if(depositeamount<=0) { 
			System.out.println("please enter positive amount");
			return;
		}
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver class loaded successfully");
		
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db", "root", "*password*");
	    System.out.println("connection get established");
	    
	    String sql = "update accounts set balance =balance+? where accno=?";
	    System.out.println("construct sql query");
	    
	   PreparedStatement ps=con.prepareStatement(sql);
	    System.out.println("create object of statement");
	    
	         ps.setDouble(1, depositeamount);
	         ps.setString(2, accnum);
	       
	       ps.executeUpdate();
	       System.out.println("execute sql query");
	       
	       ps.close();
	       con.close();
	       
	      System.out.println("Deposited amount successfully...! " + depositeamount);
	       
	}

	@Override
	public void withdrawalMoney()throws Exception {
		
		
		System.out.println("Enter account no");
		String accnum=sc.next();
		
		if(accnum.length()!=10) { 
			System.out.println("please enter 10 digits account number");
			return;
		}
		
	    System.out.println("Enter amount");
		double withdrawalamount=sc.nextDouble();
		
		double balance = a.getBalance(); 

	    if(withdrawalamount <= 0) { 
	        System.out.println("Please enter sufficient amount");
	        return;
	    }
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver class loaded successfully");
		
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db", "root", "*password*");
	    System.out.println("connection get established");
	    
	    String sql="select * from accounts";
	    System.out.println("construct sql query");
	    
	    Statement smt=con.createStatement();
	    System.out.println("create statement referances");
	    
	     ResultSet rs=smt.executeQuery(sql);
	     while(rs.next()) {
	       System.out.println(rs.getString(1));
      	   System.out.println(rs.getString(2));
      	   System.out.println(rs.getString(3));
      	   System.out.println(rs.getString(4));
      	   System.out.println(rs.getString(5));
      	   System.out.println(rs.getInt(6));
      	   System.out.println(rs.getDouble(7));
	     }
		String sql1="update accounts set balance=balance- ? where accno =? ";
		System.out.println("consrtuct sql query");
		
		PreparedStatement ps=con.prepareStatement(sql1);
		 
		    ps.setDouble(1, withdrawalamount);
		    ps.setString(2, accnum);
		
		    ps.executeUpdate();
		    System.out.println("execute sql query");
		    
		    smt.close();
		    con.close();
		    ps.close();
		System.out.println("withdrawal amount successfully...! " + withdrawalamount);
		
	}

	@Override
	public void balanceCheck()throws Exception {
		
		System.out.println("check balance");
		
		System.out.println("Enter acc no");
		String accnum=sc.next();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver class loaded successfully");
		
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bms_db", "root", "*password*");
	    System.out.println("connection get established");
	    
	    String sql="select balance from accounts where accno=?";
	    System.out.println("construct sql query");
		
	    PreparedStatement ps=con.prepareStatement(sql);
		System.out.println("create object of statement");
		
		        ps.setString(1, accnum);
		       
		       ResultSet rs=ps.executeQuery();
		       while(rs.next()) {
		    	   System.out.println("Total account balance- " +rs.getInt(1));
		       }
		       ps.close();
		       con.close();
		       
	 
		       
	}

}
