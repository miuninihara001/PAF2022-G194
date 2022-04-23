package com;
import model.Billing;


// For REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;  


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Billing")

public class BillingService {

	
	// Insert Data to the Bill
	
	Billing BillObj = new Billing(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBillDetails() 
	 { 
	 return BillObj.readBillDetails(); 
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBillDetails(@FormParam("AccountNo") String AccountNo, 
									 @FormParam("Month") String Month, 
									 @FormParam("Units") String Units, 
									 @FormParam("Amount") String Amount,
									 @FormParam("Date") String Date)
	
	{ 
	 String output = BillObj.insertBillDetails(AccountNo, Month, Units, Amount,Date); 
	return output; 
	}
	
	
	//
	

	
}
