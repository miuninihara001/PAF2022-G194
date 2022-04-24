package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	 @FormParam("houseNo") String houseNo,
	 @FormParam("district") String district,
	 @FormParam("consumedUnits") Integer consumedUnits,
	 @FormParam("date") String date)
	{
	 String output = readObj.insertRecords(accountNo, consumerName, houseNo, district, consumedUnits, date);
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
		String houseNo = itemObject.get("houseNo").getAsString();
		String district = itemObject.get("district").getAsString();
		String consumedUnits = itemObject.get("consumedUnits").getAsString();
		String date = itemObject.get("date").getAsString();
		
		String output = readObj.updateUnit(recordID, accountNo, consumerName, houseNo, district, consumedUnits, date);
		
		return output;
	}
	
	
	
	
}
