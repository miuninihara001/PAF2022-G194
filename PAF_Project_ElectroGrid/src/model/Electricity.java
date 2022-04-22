package model;

import java.sql.*;

public class Electricity {
	
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	
	public String insertCon(String AccountNum, String LineNum, String ClientName, String NIC, String ConnecType, String Email, String Address, String WiringType)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into connections(`ConCode`,`AccountNum`,`LineNum`,`ClientName`,`NIC`,`ConnecType`,`Email`,`Address`,`WiringType`)"
	 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, AccountNum);
	 preparedStmt.setString(3, LineNum);
	 preparedStmt.setString(4, ClientName);
	 preparedStmt.setString(5, NIC);
	 preparedStmt.setString(6, ConnecType);
	 preparedStmt.setString(7, Email);
	 preparedStmt.setString(8, Address);
	 preparedStmt.setString(9, WiringType);
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	
	
	

}
