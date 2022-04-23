package model;

import java.sql.*; 

public class Cunsumption 
{ //A common method to connect to the DB
  private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertconsumption(String Dname, String DZipCode, String DUsedUnits, String Note) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into consumption(`ID`,`Dname`,`String DZipCode`,`DUsedUnits`,`Note`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, Dname); 
 preparedStmt.setString(3, DZipCode); 
 preparedStmt.setDouble(4, Double.parseDouble(DUsedUnits)); 
 preparedStmt.setString(5, Note); 
 
//execute the statement

 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the Details."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 }
public String readCunsumption() {
	// TODO Auto-generated method stub
	return null;
} 
}
