package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Units {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg", "root", "");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
	 }
	 return con;
	 } 
	
	

	public String insertRecords(String acc, String name, String address, Integer units, String date)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting !"; 
		 }
		 
		 	// create a prepared statement
		 	String query = " insert into unitrecords(`recordID`,`accountNo`,`consumerName`,`address`,`consumedUnits`, `date`)"
		 	+ " values (?, ?, ?, ?, ?, ?)";
		 	
		 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 	// binding values
		 	preparedStmt.setInt(1, 0);
		 	preparedStmt.setString(2, acc);
	 		preparedStmt.setString(3, name);
	 		preparedStmt.setString(4, address);
	 		preparedStmt.setInt(5, units);
	 		preparedStmt.setString(6, date);
	 		
	 		// execute the statement
	 
	 		preparedStmt.execute();
	 		con.close();
	 		output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 	output = "Error while inserting the record !";
		 	System.err.println(e.getMessage());
	 }
	 
	 return output;
	 
	 }

}
