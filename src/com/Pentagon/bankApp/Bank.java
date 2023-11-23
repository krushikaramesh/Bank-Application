package com.Pentagon.bankApp;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args) 
	{
		int option=0;
		Scanner sc=new Scanner(System.in);
	
	do
	{
		System.out.println("Select an option");
		System.out.println("1, Employee Interface");
		System.out.println("2, Customer Interface");
		System.out.println("3, Exit");
		option=sc.nextInt();
		
		switch(option) {
		case 1: 
			EmployeeInterface.employeeLogin();
		    break;
		case 2:
			CustomerInterface.showInterface();
	        break;
		case 3:
			System.out.println("Exiting the program.Goodbye!");
			break;
	    default:
	    	System.out.println("Invalid choice.Please select again.");
		}
	}
	while(option!=3);
	sc.close();
	}

}
