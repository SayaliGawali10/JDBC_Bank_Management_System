package com.cjc.bms.app.client;
import java.util.*;
import com.cjc.bms.app.servicei.Rbi;
import com.cjc.bms.app.serviceimpl.Sbi;

public class Test {

	public static void main(String[] args)throws Exception {
	
		Scanner sc=new Scanner(System.in);
		Rbi rbi=new Sbi();
		 while(true) {
		 System.out.println("1. create account");
		 System.out.println("2. dispaly all details");
		 System.out.println("3. deposite money");          
		 System.out.println("4. withdrawal money");
		 System.out.println("5. check balance");
		 System.out.println("Enter your choice");
		 int ch=sc.nextInt();
		 
         switch(ch) {                     
             
             case 1:
            	 rbi.createAccount();
            	 break;
             
             case 2:
            	 rbi.displayAllDetails();
            	 break;
             
             case 3:
            	 rbi.depositeMoney();
            	 break;
            	 
             case 4:
            	 rbi.withdrawalMoney(); 
            	 break;
            	 
             case 5:
            	 rbi.balanceCheck();
            	 break;
             
             default :
            	  System.out.println("Invalid choice, please enter valid choice");
             }
	}
	}
}
