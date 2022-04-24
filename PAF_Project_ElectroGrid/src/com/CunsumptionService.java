package com;

//import javax.swing.text.Document;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import org.jsoup.parser.*;
//import org.jsoup.Jsoup;
//
//
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.protobuf.Parser;
//
import model.Cunsumption;




// For REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;  


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Cunsumption") 

public class CunsumptionService {
   
	

	
		
		Cunsumption consumpObj = new Cunsumption(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readCunsumption() 
		 { 
		
		return consumpObj.readCunsumption();
		 } 
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String insertconsumption(@FormParam("Dname") String Dname, 
		   @FormParam("DZipCode") String DZipCode, 
		   @FormParam("DUsedUnits") String DUsedUnits, 
		   @FormParam("Month") String Month,
		   @FormParam("Note") String Note) 
		{ 
		 String output = consumpObj.insertconsumption(Dname, DZipCode, DUsedUnits, Month, Note); 
		 return output; 
		}

		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateCunsumption(String consumptionData) 
		{ 
			
			
		//Convert the input string to a JSON object 
		 JsonObject ConObject1 = new JsonParser().parse(consumptionData).getAsJsonObject(); 
		 
		 
		 
		//Read the values from the JSON object
		 String ID =ConObject1.get("ID").getAsString(); 
		 String Dname = ConObject1.get("Dname").getAsString(); 
		 String DZipCode = ConObject1.get("DZipCode").getAsString(); 
		 String DUsedUnits = ConObject1.get("DUsedUnits").getAsString(); 
		 String Month = ConObject1.get("Month").getAsString();
		 String Note = ConObject1.get("Note").getAsString(); 
		 
		 String output = consumpObj.updateCunsumption(ID, Dname, DZipCode, DUsedUnits,Month, Note); 
		 return output; 
		}
		
		
		
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteCunsumption(String consumptionData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String ID = doc.select("ID").text(); 
		 
		 String output = consumpObj.deleteCunsumption(ID); 
		return output; 
		}
	
		//search
		
		@GET
		@Path("/search")
		@Produces(MediaType.TEXT_HTML)
		public String searchCunsumption(String consumptionData) {
			Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser()); 
			String ID = doc.select("Dname").text(); 
			
			return consumpObj.searchCunsumption(Dname);
		}
		
}
