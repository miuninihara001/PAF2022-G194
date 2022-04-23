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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Electricity; 

@Path("/electric")
public class ElectricityService {
	
	Electricity eleconnec = new Electricity();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCons()
	 {
		return eleconnec.readCons();
	 } 

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCon(@FormParam("AccountNum") String AccountNum,
	 @FormParam("LineNum") String LineNum,
	 @FormParam("ClientName") String ClientName,
	 @FormParam("NIC") String NIC,
	 @FormParam("ConnecType") String ConnecType,
	 @FormParam("Email") String Email,
	 @FormParam("Address") String Address,
	 @FormParam("WiringType") String WiringType)
	{
	 String output = eleconnec.insertCon(AccountNum, LineNum, ClientName, NIC,ConnecType,Email,Address,WiringType);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCode(String ConData)
	{
	//Convert the input string to a JSON object
	 JsonObject ConObject = new JsonParser().parse(ConData).getAsJsonObject();
	//Read the values from the JSON object
	 String ConCode = ConObject.get("ConCode").getAsString();
	 String AccountNum = ConObject.get("AccountNum").getAsString();
	 String LineNum = ConObject.get("LineNum").getAsString();
	 String ClientName = ConObject.get("ClientName").getAsString();
	 String NIC = ConObject.get("NIC").getAsString();
	 String ConnecType = ConObject.get("ConnecType").getAsString();
	 String Email = ConObject.get("Email").getAsString();
	 String Address = ConObject.get("Address").getAsString();
	 String WiringType = ConObject.get("WiringType").getAsString();
	 
	 String output = eleconnec.updateCon(ConCode, AccountNum, LineNum, ClientName, NIC,ConnecType,Email,Address,WiringType);
	 
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCons(String ConData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(ConData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ConCode = doc.select("ConCode").text();
	 String output = eleconnec.deleteCons(ConCode);
	return output;
	}

	
	

}
