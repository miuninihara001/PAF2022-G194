package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Cunsumption;

@Path("/Cunsumption") 

public class CunsumptionService {
   
	

	
		
		Cunsumption consumpObj = new Cunsumption(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readCunsumption() 
		 { 
			return "Hello";
			//return consumpObj.readCunsumption();
		 } 

}
