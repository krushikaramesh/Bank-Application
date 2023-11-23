package com.Pentagon.bankApp;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class EmployeeInterface 

	{
	public static void employeeLogin()
	{
		Scanner sc=new Scanner(System.in);
		boolean login=false;
		int attempts=0;
		System.out.println("<---Employee Interface--->");
		System.out.println("Welcome to New bank");
		while(!login&&attempts<3)
			{
				System.out.println("Enter the Employee ID:");
				int id=sc.nextInt();
				System.out.println("Enter the Password:");
				String pwd=sc.next();
				if (loginValidation(id,pwd))
				{
					System.out.println("Login successful!");
					login=true;
				}
				else
				{
					attempts++;
					System.out.println("Login failed. Please retry.");
				}
			}
		if (attempts>=3)
		{
			System.out.println("Max login attempts reached. Exiting.");
			return;
		}
		//after the validation
		System.out.println("Select an option:");
		System.out.println("1. Add a customer data");
		System.out.println("2. Update a customer data");
		System.out.println("3. Delete customer data");
		System.out.println("4. View customer details");
		System.out.println("5. Go Back to Main Menu");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			addNewCustomer();
			break;
		case 2:
			updateCustomer();
			break;
		case 3:
			deleteCustomer();
			break;
		case 4:
			viewCustomerData();
			break;
		case 5:
			System.out.println("Returning to main menu.");
			break;
		default:
			System.out.println("Invalid choice");
		}
		}
	private static void viewCustomerData() {
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement ps5=null;
	ResultSet rs=null;
	String ViewData="SELECT * FROM CUSTOMER WHERE ACCNO=?";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user= root&password=tiger");
				ps5=con.prepareStatement(ViewData);
				System.out.println("Enter the account number: ");
				int accno=sc.nextInt();
				ps5.setInt(1, accno);
				rs=ps5.executeQuery();
				if(rs.next())
				{
					String name=rs.getString(2);
					long phone=rs.getLong(3);
					String mail=rs.getString(4);
					double bal=rs.getDouble(5);
	System.out.println("The account holder name is"+name);
	System.out.println("The phone number is "+phone);
	System.out.println("The mail ID is "+mail);
	System.out.println("The account balance is Rs."+bal);
	}
	else {
		System.out.println("Invalid account number");
		return;
	}
	}
	catch(Exception e)
		{
		e.printStackTrace();
		}
	finally
	{
		try
		{
			if(rs!=null)rs.close();
			if(ps5!=null)ps5.close();
			if(con!=null)con.close();
		}
	catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		}
	private static void deleteCustomer() {
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement ps4=null;
	String Dquery="DELETE FROM CUSTOMER WHERE ACCNO=?";
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=tiger");
	ps4=con.prepareStatement(Dquery);
	System.out.println("Enter the account number: ");
	int accno=sc.nextInt();
	ps4.setInt(1,accno);
	System.out.println("Do you like to delete the data of"+accno+"?");
	System.out.println("Press yes to delete.");
	System.out.println("Press any other key to main menu");
	String option=sc.next();
	if(option.equalsIgnoreCase("yes"))
	{
	ps4.executeUpdate();
	System.out.println("Data deleted successfully");
	}
	else {
	System.out.println("Returning to main menu..!");
	return;
	}
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	finally
	
	{
	try
	{
	if(ps4!=null)ps4 .close();
	if(con!=null)con.close();
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
	}
	private static void updateCustomer()
	{
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement ps3=null;
	String Uquery="UPDATE CUSTOMER SET NAME=?,PHONE=?,EMAIL=? WHERE ACCNO=?";
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=tiger");
	ps3=con.prepareStatement(Uquery);
	System.out.println("Enter the name to be updated: ");
	String name=sc.next();
	ps3.setString(1,name);
	System.out.println("Enter the phone number to be updated:");
	long phone=sc.nextLong();
	ps3.setLong(2, phone);
	System.out.println("Enter the mail ID to be updated: ");
	String mail=sc.next();
	ps3.setString(3,mail);
	System.out.println("Enter the account number: ");
	int accno=sc.nextInt();
	ps3.setInt(4, accno);
	int update=ps3.executeUpdate();
	if(update>0)
	{
	System.out.println("Data updated successfully");
	}
	else
	{
	System.out.println("Failed to update the data");
	}
	} catch (ClassNotFoundException | SQLException e) {
	
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	finally
	{
	try
	{
	if(ps3!=null)ps3.close();
	if(con!=null)con.close();
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
	}
	//insert the customer data
	private static void addNewCustomer()
	{
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement ps2=null;
	String iQry="INSERT INTO CUSTOMER(NAME,PHONE,EMAIL)VALUES(?,?,?)";
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?"
	+ "user=root&password=tiger");
	ps2=con.prepareStatement(iQry);
	System.out.println("Enter the Customer name: ");
	String name=sc.next();
	ps2.setString(1, name);
	System.out.println("Enter the Customer Phone number: ");
	long phone=sc.nextLong();
	ps2.setLong(2, phone);
	System.out.println("Enter the Customer mail ID: ");
	String email=sc.next();
	ps2.setString(3, email);
	System.out.println("Data added successfully");
	ps2.executeUpdate();
	} 
	catch (ClassNotFoundException | SQLException |NoSuchElementException e) {
	
	e.printStackTrace();
	}finally
	{
	if(ps2!=null)
	
	{
	try {
	ps2.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	if(con!=null)
	{
	try {
	con.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
	}
	}
	public static boolean loginValidation(int id, String pwd) {
	Scanner sc=new Scanner(System.in);
	Connection con=null;
	PreparedStatement ps1=null;
	ResultSet rs1=null;
	String eVal="SELECT * FROM EMP WHERE EID=? AND PASSWORD=?";
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?"
	+ "user=root&password=tiger");
	ps1=con.prepareStatement(eVal);
	ps1.setInt(1, id);
	ps1.setString(2, pwd);
	rs1=ps1.executeQuery();
	if(rs1.next())
	{
	String name=rs1.getString(3);
	System.out.println("Welcome back "+name+",");
	return true;
	}
	else
	{
	System.out.println("Invalid Employee Id or password.Please try again!!");
	}
	} catch (ClassNotFoundException | SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}finally
	
	{
	try {
	if(rs1!=null)rs1.close();
	if(ps1!=null)ps1.close();
	if(con!=null)con.close();
	}
	catch (SQLException e)
	{
	e.printStackTrace();
	}
	}
	return false;
	}
	}	