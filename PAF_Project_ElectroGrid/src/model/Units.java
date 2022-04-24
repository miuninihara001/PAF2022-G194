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
	
	

	public String insertRecords(String acc, String name, String house, String district, Integer units, String date)
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
		 	String query = " insert into unitrecords(`recordID`,`accountNo`,`consumerName`,`houseNo`,`district`,`consumedUnits`, `date`)"
		 	+ " values (?, ?, ?, ?, ?, ?)";
		 	
		 	PreparedStatement preparedStmt = con.prepareStatement(query);
		 	// binding values
		 	preparedStmt.setInt(1, 0);
		 	preparedStmt.setString(2, acc);
	 		preparedStmt.setString(3, name);
	 		preparedStmt.setString(4, house);
	 		preparedStmt.setString(5, district);
	 		preparedStmt.setInt(6, units);
	 		preparedStmt.setString(7, date);
	 		
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
	
	

	public String readUnits()
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
		 }
		 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>AccountNO</th><th>Consumer Name</th>" +"<th>HouseNo</th>" +
	 "<th>District</th>" + "<th>Consumed Units</th>" + "<th>Date</th></tr>";

	 String query = "select * from unitrecords";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String recordID = Integer.toString(rs.getInt("recordID"));
		 String accountNo = rs.getString("accountNo");
		 String consumerName = rs.getString("consumerName");
		 String houseNo = rs.getString("houseNo");
		 String district = rs.getString("district");
		 String consumedUnits =Integer.toString(rs.getInt("consumedUnits"));
		 String date = rs.getString("date");
	 
		 // Add into the html table
		 output += "<tr><td>" + accountNo + "</td>";
		 output += "<td>" + consumerName + "</td>";
		 output += "<td>" + houseNo + "</td>";
		 output += "<td>" + district + "</td>";
		 output += "<td>" + consumedUnits + "</td>";
		 output += "<td>" + date + "</td>";
		 // buttons
//		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
//		+ "<input name='itemID' type='hidden' value='" + itemID
//		+ "'>" + "</form></td></tr>";
		 
	 }
	 	con.close();
	 	// Complete the html table
	 	output += "</table>";
	 }
	 		catch (Exception e)
	 {
	 		output = "Error while reading the items.";
	 		System.err.println(e.getMessage());
	 }
	 		return output;
	 }
	
	

}
