package com;

import javax.ws.rs.Consumes;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Electricity; 

@Path("/electric")
public class ElectricityService {
	
	Electricity eleconnec = new Electricity();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCons()
	 {
		return "hell";
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
	

}
