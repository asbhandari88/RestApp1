package solutions.amit.rest;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.util.JSONPObject;

import solutions.amit.app.AppException;
import solutions.amit.dao.ReservationDao;
import solutions.amit.dao.UserDao;
import solutions.amit.model.ConfirmationDetails;
import solutions.amit.model.Reservation;
import solutions.amit.model.Seating;

@Path("/reservation")
public class ReservationController {

	@POST
	@Path("/makeReservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse makeReservation(Reservation data){
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		ConfirmationDetails result =new ConfirmationDetails(); 
		
		try {
			
			result = reserve.newReservation(data);
			
			if(result != null){
				resp.setMessage("Reservation successful!");
				resp.setPayload(result);
			}else{
				resp.setStatus("failure");
				resp.setMessage("Reservation failed!");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	

	@GET
	@Path("/editReservation/{email}/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getReservation(@PathParam("email")  String email,@PathParam("cnfCode") String cnfCode){
		
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		Reservation result = new Reservation();
		try {
			
			result = reserve.getReservation(email,cnfCode);
			
			if(result != null){
				resp.setMessage("Your reservation details");
				resp.setPayload(result);
			}else{
				resp.setStatus("failure");
				resp.setMessage("Cannot find your reservation details!");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	@GET
	@Path("/getAllReservation")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllReservation(){
		
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		String result;
		try {
			
			result = reserve.getAllReservation();
			
			if(result != null){
				resp.setMessage("Your reservation details");
				resp.setPayload(result);
			}else{
				resp.setStatus("failure");
				resp.setMessage("Cannot find your reservation details!");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/cancelReservation/{email}/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse cancelReservation(@PathParam("email")  String email,@PathParam("cnfCode") String cnfCode){
		
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		boolean result;
		try {
			
			result = reserve.cancelReservation(email,cnfCode);
			
			if(result == true){
				resp.setMessage("Reservation cancelled!");
			}else{
				resp.setStatus("failure");
				resp.setMessage("Cannot find your reservation details!");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	@POST
	@Path("/editNewReservation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse editNewReservation(String jsonString){
		
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		ConfirmationDetails cd = new ConfirmationDetails();
		try{
			cd = reserve.editReservation(jsonString);
			
			if(cd == null){
				resp.setStatus("failure");
				resp.setMessage("No Tables available for Reservation");
			}else{
				resp.setPayload(cd);
				resp.setMessage("Reservation successful");
			}
		}catch(AppException e){
			
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
	
	@POST
	@Path("/getSeatings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse getSeatings(String jsonString){
		
		System.out.println(jsonString);
		AppResponse resp = new AppResponse();
		String result = new String();
		ReservationDao reserve = new ReservationDao();
		try{
			result = reserve.getSeatings(jsonString);
			
			if(result == null){
				resp.setStatus("failure");
				resp.setMessage("Table Reservation problem");
			}else{
				resp.setPayload(result);
				resp.setMessage("Reservation successful");
			}
		}catch(AppException e){
			
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
	
	
	@POST
	@Path("/makeSeatings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse makeSeatings(Seating seat){
		
		
		AppResponse resp = new AppResponse();
		ReservationDao reserve = new ReservationDao();
		ConfirmationDetails result =new ConfirmationDetails(); 
		
		try {
			
			result = reserve.makeSeating(seat);
			
			if(result != null){
				resp.setMessage("Reservation successful!");
				resp.setPayload(result);
			}else{
				resp.setStatus("failure");
				resp.setMessage("Reservation failed!");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/getContacts")
	@Produces(MediaType.APPLICATION_JSON)	
	public AppResponse getContacts(){
		
		AppResponse resp = new AppResponse();
		String result = new String();
		UserDao user = new UserDao();
		try{
			result = user.getContacts();
			
			if(result == null){
				resp.setStatus("failure");
				resp.setMessage("Table Contacts problem");
			}else{
				resp.setPayload(result);
				resp.setMessage("Reservation successful");
			}
		}catch(AppException e){
			
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
	
}
