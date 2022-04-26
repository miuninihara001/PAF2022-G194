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

import model.Payment;

@Path("/Payment") 
public class PaymentService {
	
	Payment payment = new Payment(); 
	
	//Add Payment
	@POST
	@Path("/Add") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("Name") String Name, 
	 @FormParam("Address") String Address,
	 @FormParam("MobileNo") String MobileNo,
	 @FormParam("CardNo") String CardNo, 
	 @FormParam("HlderName") String HlderName, 
	 @FormParam("CVV") String CVV,
	 @FormParam("Exdate") String Exdate) 
	{ 
	 String output = payment.PaymentAdd(Name, Address, MobileNo, CardNo, HlderName, CVV, Exdate); 
	return output; 
	}
	
	// Read Payment 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() { 

		return payment.readPayment(); 

	}
	
	//Update Payment
	@PUT
	@Path("/Update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String PaymentData) {
		
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(PaymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String Name = itemObject.get("Name").getAsString(); 
	 String CardNo = itemObject.get("CardNo").getAsString(); 
	 String HlderName = itemObject.get("HlderName").getAsString(); 
	 String CVV = itemObject.get("CVV").getAsString(); 
	 String Exdate = itemObject.get("Exdate").getAsString();
	 
	 String output = payment.updatePayment(Name, CardNo, HlderName, CVV, Exdate); 
	return output; 
	}
	
	//Delete Payment
	@DELETE
	@Path("/Delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String PaymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String id = doc.select("id").text(); 
	 String output = payment.deletePayment(id); 
	return output; 
	}
}
