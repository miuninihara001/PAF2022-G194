package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Schedule {
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String InsertPowerCutDetails(String lineno, String areano, String areaname, String starttime, String endtime, String date, String reason)
	{
		String output = "";
		
		try {
				
				Connection con = connect();
			
				if(con == null) {
					return "Error while connecting to the DB for inserting";
				}
			
			//prepared statment
				String query = " insert into shedules (`sheduleId`,`lineNo`,`areaNo`,`areaName`,`startTime`,`endTime`,`date`,`reason`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStat = con.prepareStatement(query);
			
			//values binding
			preparedStat.setInt(1, 0);
			preparedStat.setString(2, lineno);
			preparedStat.setString(3, areano);
			preparedStat.setString(4, areaname);
			preparedStat.setString(5, starttime);
			preparedStat.setString(6, endtime);
			preparedStat.setString(7, date);
			preparedStat.setString(8, reason);
			
			//Execute statement
			preparedStat.execute();
			con.close();
			output = "Power cut shedule details inserted successfully";
			
			
		}catch(Exception e) {
			output ="Error while inserting power cut shedule details";
			System.out.println(e.getMessage());
		}
		
		return output;
	}
	public String readPowerCutDetails() {
		
		String output = "";
		
		try {
				Connection con = connect();
				
				if(con == null) {
					return "Error while connecting to the DB for reading";
				}
				
				//html table to be display
				output = "<table border='1'><tr><th>Line Number</th><th>Area Number</th><th>Area Name</th>"+
				          "<th>Start Time</th><th>End Time</th>"+
				          "<th>Date</th><th>Reason</th></tr>";
				
				String query = "select * from shedules";
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(query);
				
				//iteration through the rows of result set
				while(rs.next()) {
					
					String sheduleId = Integer.toString(rs.getInt("sheduleId"));
					String lineNo = rs.getString("lineNo");
					String areaNo = rs.getString("areaNo");
					String areaName = rs.getString("areaName");
					String startTime = rs.getString("startTime");
					String endTime = rs.getString("endTime");
					String date = rs.getString("date");
					String reason = rs.getString("reason");
					
					//add raws into the html table
					output += "<tr><td>" + lineNo + "</td>";
					output += "<td>" + areaNo + "</td>";
					output += "<td>" + areaName + "</td>";
					output += "<td>" + startTime + "</td>";
					output += "<td>" + endTime + "</td>";
					output += "<td>" + date + "</td>";
					output += "<td>" + reason + "</td>";
					
					//buttons
					
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
									+ "<td><form method='post' action='shedules.jsp'>"
									+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
									+ "<input name='sheduleId' type='hidden' value='" + sheduleId
									+ "'>" + "</form></td></tr>";
							
				} 
				
				con.close();
				
				//complete html table
				output +="</table>";
				
		}catch(Exception e) {
			output = "Error while reading the power cut shedule details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
