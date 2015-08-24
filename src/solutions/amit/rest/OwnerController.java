package solutions.amit.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import solutions.amit.app.AppException;
import solutions.amit.dao.OwnerDao;
import solutions.amit.model.Auth;
import solutions.amit.model.Owner;

@Path("/ownerDetails")
public class OwnerController {
	

	@POST
	@Path("/auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse loginOwner(Auth auth,@Context HttpServletRequest request){
		
		AppResponse resp = new AppResponse();
		
		OwnerDao ow = new OwnerDao();
		boolean isAuthenticated;
		try {
			isAuthenticated = ow.ownerAuthenticate(auth);
			
			if(isAuthenticated){
				resp.setMessage("Login successful!");
				HttpSession session = request.getSession(true);
				session.setAttribute("user", auth);
				resp.setPayload(ow.getOwnerInfo(auth.getUsername()));
			}else{
				resp.setMessage("Login Invalid!");
				resp.setStatus("failure");
			}
			
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
			
		}
		return resp;
	}
	
	
	@POST
	@Path("/updateProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateProfile(Owner own){
		
		AppResponse resp = new AppResponse();
		
		OwnerDao ow = new OwnerDao();
		Owner result = new Owner();
		try {
			result = ow.setOwnerInfo(own);
			
			if(result != null){
				resp.setMessage("Profile updated successfully!");
				resp.setPayload(result);
			}else{
				resp.setMessage("Profile updated unsuccessful!");
				resp.setStatus("failure");
			}
			
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
			
		}
		return resp;
	}
	
	
	@POST
	@Path("/changePassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse changePassword(Owner req){
		
		AppResponse resp = new AppResponse();
		
		OwnerDao ow = new OwnerDao();
		boolean result= false;
		try {
			 result = ow.changeOwnerPassowrd(req);
			
			if(result == true){
				resp.setMessage("Password updated successfully!");
			}else{
				resp.setMessage("Password update unsuccessful!");
				resp.setStatus("failure");
			}
			
		} catch (AppException e) {
			e.printStackTrace(); 
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
			
		}
		return resp;
	}
	
	
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse logoutOwner(@Context HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();
		
		AppResponse resp = new AppResponse();
		resp.setMessage("logout");
		return resp;
		
	}
	
}
