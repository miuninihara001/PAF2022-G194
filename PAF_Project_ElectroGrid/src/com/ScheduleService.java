package com;
import model.Schedule;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/ ")
public class ScheduleService {
	
	Schedule scheduleObj = new Schedule();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return scheduleObj.readPowerCutDetails();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String InsertPowerCutDetails(@FormParam("lineNo") String lineNo,
				@FormParam("areaNo") String areaNo,
				@FormParam("areaName") String areaName,
				@FormParam("startTime") String startTime,
				@FormParam("endTime") String endTime,
				@FormParam("date") String date,
				@FormParam("reason") String reason)
	{
		String output = scheduleObj.InsertPowerCutDetails(lineNo, areaNo, areaName, startTime, endTime, date, reason);
		return output;
	}
	
}