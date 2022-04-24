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
	public String insertBillDetails(
									
									@FormParam("AccountNo") String AccountNo, 
									@FormParam("Month") String Month, 
									@FormParam("Units") String Units, 
									@FormParam("Amount") String Amount,
									@FormParam("Date") String Date)
	
	{ 
	 String output = BillObj.insertBillDetails(AccountNo, Month, Units, Amount,Date); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBillDetails(String billingDatata) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject BillObj1 = new JsonParser().parse(billingDatata).getAsJsonObject(); 
	//Read the values from the JSON object'
	 String Bill_ID = BillObj1.get("Bill_ID").getAsString(); 
	 String AccountNo = BillObj1.get("AccountNo").getAsString(); 
	 String Month = BillObj1.get("Month").getAsString(); 
	 String Units = BillObj1.get("Units").getAsString(); 
	 String Amount = BillObj1.get("Amount").getAsString(); 
	 String Date = BillObj1.get("Date").getAsString(); 
	 
	 String output = BillObj.updateBillDetails(Bill_ID,AccountNo,Month,Units,Amount,Date);
	 
	return output; 
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteBillDetails(String billingDatata)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse( billingDatata, "", Parser.xmlParser());

	//Read the value from the element 
	 String Bill_ID = doc.select("Bill_ID").text();
	 
	 String output = BillObj.deleteBillDetails(Bill_ID);
	return output;
	}

	
}
