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
	
	//insert new electricity connection
	
	public String insertNewConnection(String AccountNum, String LineNum, String ClientName, String NIC, String ConnecType, String Email, String Address, String WiringType)
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
				output = "New electricity connection details inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the item.";
	 			System.err.println(e.getMessage());
			}
			return output;
	 }
	
	
	
	public String readConnections()
	 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Account Number</th><th>Line Number</th>" +
						"<th>Client Name</th>" +
						"<th>NIC</th>"+" <th>Connection Type</th>"+" <th>Email</th>"+" <th>Address</th>"+" <th>Wiring Type</th>" +
						"<th>Update</th><th>Remove</th></tr>";

				String query = "select * from connections";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String AccountNum = rs.getString("AccountNum");
					String LineNum = rs.getString("LineNum");
					String ClientName = rs.getString("ClientName");
					String NIC = rs.getString("NIC");
					String ConnecType = rs.getString("ConnecType");
					String Email = rs.getString("Email");
					String Address = rs.getString("Address");
					String WiringType = rs.getString("WiringType");
	 
					// Add into the html table
					output += "<tr><td>" + AccountNum + "</td>";
					output += "<td>" + LineNum + "</td>";
					output += "<td>" + ClientName + "</td>";
					output += "<td>" + NIC + "</td>";
					output += "<td>" + ConnecType + "</td>";
					output += "<td>" + Email + "</td>";
					output += "<td>" + Address + "</td>";
					output += "<td>" + WiringType + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							+ "<td><form method='post' action='items.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							+ "<input name='AccountNum' type='hidden' value='" + AccountNum + "'>" + "</form></td></tr>";
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
	
	
	
	public String updateConnections(String ConCode,String AccountNum, String LineNum, String ClientName, String NIC, String ConnecType, String Email, String Address, String WiringType)
	{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE connections SET AccountNum=?,LineNum=?,ClientName=?,NIC=?,ConnecType=?,Email=?,Address=?,WiringType=? WHERE ConCode=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, AccountNum);
				preparedStmt.setString(2, LineNum);
				preparedStmt.setString(3, ClientName);
				preparedStmt.setString(4, NIC);
				preparedStmt.setString(5, ConnecType);
				preparedStmt.setString(6, Email);
				preparedStmt.setString(7, Address);
				preparedStmt.setString(8, WiringType);
				preparedStmt.setInt(9, Integer.parseInt(ConCode));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Electricity connection details updated successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}
			return output;
	} 
	
	public String deleteConnection(String ConCode)
	 {
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// create a prepared statement
				String query = "delete from connections where ConCode=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ConCode));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
	 }
	
	
public String searchConnection(String AccountNum) {
		
		String output="";
		try{ 
			Connection con = connect(); 
			if (con == null)  {
				return "Error while connecting to the database";
				} 
				
				output = "<html>"
						+"<table border='1'><tr><th>AccountNum</th>"+
						"<th>LineNum</th>"+
						"<th>ClientName</th>" +
						"<th>NIC</th>" +
						"<th>ConnecType</th>" +
						"<th>Email</th>" +
						"<th>Address</th>" +
						"<th>WiringType</th></tr>"; 

			// create a prepared statement
			String query = "select * from connections where AccountNum='"+AccountNum+"'"; 
			Statement stmt = con.createStatement(); 
		 	ResultSet rs = stmt.executeQuery(query);
		 	while(rs.next()) {
		 		//String AccountNum = rs.getString("AccountNum"); 
		 		String LineNum = rs.getString("LineNum"); 
				String ClientName = rs.getString("ClientName");
				String NIC= rs.getString("NIC"); 
				String ConnecType = rs.getString("ConnecType");
				String Email = rs.getString("Email");
				String Address = rs.getString("Address");
				String WiringType = rs.getString("WiringType");
				
				output += "<td>" + AccountNum + "</td>"; 
 				output += "<td>" + LineNum + "</td>"; 
 				output += "<td>" + ClientName + "</td>"; 
 				output += "<td>" + NIC + "</td>"; 
 				output += "<td>" + ConnecType + "</td>"; 
 				output += "<td>" + Email + "</td>"; 
 				output += "<td>" + Address + "</td>"; 
 				output += "<td>" + WiringType + "</td></tr>"; 

				
		 	}
			
			con.close(); 
	 
				output += "</table></html>"; 

		} 
		catch (Exception e) { 
			output = "Error while searching"; 
			System.err.println(e.getMessage()); 
		} 
		return output;
	

}
}
