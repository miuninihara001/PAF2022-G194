package com;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.parser.Parser;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import model.Units;

@Path("/unit") 

public class UnitServices {


	Units readObj = new Units();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("accountNo") String accountNo,
	 @FormParam("consumerName") String consumerName,
	 @FormParam("address") String address,
	 @FormParam("district") String district,
	 @FormParam("consumedUnits") Integer consumedUnits,
	 @FormParam("year") String year,
	 @FormParam("month") String month)
	{
	 String output = readObj.insertUnits(accountNo, consumerName, address, district, consumedUnits, year,month);
	return output;
	}

	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnits()
	 {
	 return readObj.readUnits();
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnit(String unitData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(unitData).getAsJsonObject();
		
		//Read the values from the JSON object
		String recordID = itemObject.get("recordID").getAsString();
		String accountNo = itemObject.get("accountNo").getAsString();
		String consumerName = itemObject.get("consumerName").getAsString();
		String address = itemObject.get("address").getAsString();
		String district = itemObject.get("district").getAsString();
		String consumedUnits = itemObject.get("consumedUnits").getAsString();
		String year = itemObject.get("year").getAsString();
		String month = itemObject.get("month").getAsString();
		
		String output = readObj.updateUnit(recordID, accountNo, consumerName, address, district, consumedUnits, year, month);
		
		return output;
	}
	

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String unitData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String recordID = doc.select("recordID").text();
	 String output = readObj.deleteUnit(recordID);
	return output;
	}
	
	


	@GET
	@Path("/searchUnits")
	@Produces(MediaType.TEXT_HTML)
	public String searchConnection(String unitData) {
			Document doc = Jsoup.parse(unitData, "", Parser.xmlParser()); 
			String accountNo = doc.select("accountNo").text(); 
			return readObj.searchConnection(accountNo);
	}
	
}
