package solutions.amit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import java.sql.CallableStatement;

import solutions.amit.app.AppException;
import solutions.amit.model.ConfirmationDetails;
import solutions.amit.model.Reservation;
import solutions.amit.model.Seating;
import solutions.amit.model.Table;
import solutions.amit.model.User;
import solutions.amit.utils.DbUtils;

public class ReservationDao {

	public ConfirmationDetails newReservation(Reservation data) throws AppException {
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Table> al = new ArrayList<Table>();
		ConfirmationDetails cd = new ConfirmationDetails();
		
			al = this.availableTable(data);
			System.out.println(al);
			Table tab = this.selectTable(al);
		
			
			if(tab == null){
				return null;
			
			}else{
				try{
					cs = con.prepareCall("{call makeNewReservation(?,?,?,?,?,?,?,?,?,?)}");
					cs.setString(1,data.getEmail());
					cs.setString(2,data.getName());
					cs.setString(3,data.getPhone());
					cs.setString(4,data.getDate());
					cs.setString(5,data.getTime_from());
					cs.setInt(6,data.getNo_of_people());
					cs.setString(7,data.getSpecial_arrangement());
					cs.setInt(8, tab.getTable_id());
					cs.setString(9, data.getCreatedBy());
					cs.registerOutParameter(10, java.sql.Types.INTEGER);
					
					cs.executeQuery();
					
					
					
					cd.setCnfCode("CNF"+cs.getInt(10));
					cd.setTable_id(tab.getTable_id());
					cd.setTable_name(tab.getTable_name());
					
					
				}catch(SQLException e){
					e.printStackTrace();
					throw new AppException("SQL exception while making reservation",e.getCause());
				}finally{
					DbUtils.closeResources(cs, rs, con);
				}
				
				
			
			}
		
			return cd;
	}
	
	
	public ArrayList<Table> availableTable(Reservation data) throws AppException{

		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<Table> al = new ArrayList<Table>();
		
		try{
			cs = con.prepareCall("{call availableTables(?,?,?)}");
			cs.setInt(1,data.getNo_of_people());
			cs.setString(2,data.getDate());
			cs.setString(3,data.getTime_from());
			
			
			rs = cs.executeQuery();
			
			while(rs.next()){
				al.add(new Table(rs.getInt("table_id"),rs.getString("table_name"),rs.getInt("table_capacity")));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("SQL exception while checking available tables",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		return al;
	}
	
	
	public Table selectTable(ArrayList<Table> al) throws AppException{
		
		
		if(al == null){
			return null;
		}
		
		Random randomGenerator = new Random();
		Table tab = al.get(randomGenerator.nextInt(al.size()));
		return tab;
	}
	
	
	public Reservation getReservation(String email,String cnfCode) throws AppException{
		
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		Reservation reserve = new Reservation();
		try{
			
			cs = con.prepareCall("{call getReservation(?,?)}");
			cs.setString(1, email);
			cs.setInt(2, Integer.parseInt(cnfCode.substring(3)));
			
			rs = cs.executeQuery();
			
			if(rs.next()){
				reserve.setReserve_id(rs.getInt("reserve_id"));
				reserve.setEmail(email);
				reserve.setDate(rs.getString("reserve_date"));
				reserve.setTime_from(rs.getString("time_from"));
				reserve.setTime_to(rs.getString("time_to"));
				reserve.setReserve_table_id(rs.getInt("reserve_table_id"));
				reserve.setNo_of_people(rs.getInt("no_of_people"));
				reserve.setSpecial_arrangement(rs.getString("special_arrangement"));
				reserve.setCreatedBy(rs.getString("created_by"));
				reserve.setName(rs.getString("name"));
				reserve.setPhone(rs.getString("phone"));
			}else{
				return null;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("SQL exception while fetching reservation",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return reserve;
	}
	
	
public ConfirmationDetails editReservation(String jsonString) throws AppException{
		
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		Reservation oldReserve = new Reservation();
		Reservation newReserve = new Reservation();
		ConfirmationDetails cd = new ConfirmationDetails();
		User user = new User();
		UserDao getUser = new UserDao();
		try{
			JSONObject obj = new JSONObject();
			JSONParser parser = new JSONParser();
			
			obj = (JSONObject) parser.parse(jsonString);
			//System.out.println("Object : "+obj.toString());
			
			oldReserve = this.getReservation((String)obj.get("email"),(String)obj.get("cnfCode"));
		//System.out.println("Email :"+obj.get("email")+"code : "+obj.get("cnfCode")+" date :" + obj.get("date") +"time :"+obj.get("time")+" size :"+obj.get("size")+" special_arrangement :"+ obj.get("special_arrangement")+" created by "+obj.get("createdBy"));

			
			boolean delete = this.cancelReservation(oldReserve.getEmail(), (String)"CNF"+oldReserve.getReserve_id());
			
			if(delete){
			user = getUser.getUserInfo((String) obj.get("email"));
			
			System.out.println(user.toString());
			newReserve.setName(user.getName());
			newReserve.setEmail(user.getEmail());
			newReserve.setPhone(user.getPhone());
			newReserve.setDate((String)obj.get("date"));
			newReserve.setTime_from((String)obj.get("time"));
			newReserve.setNo_of_people(((Long)obj.get("size")).intValue());
			newReserve.setSpecial_arrangement((String)obj.get("special_arrangement"));
			newReserve.setCreatedBy((String)obj.get("createdBy"));
			
			cd = this.newReservation(newReserve);
			}else{
				
			}
		}catch(ParseException e){
			e.printStackTrace();
			throw new AppException("Cannot parse data",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return cd;
	}

	public boolean cancelReservation(String email, String cnfCode) throws AppException{
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		System.out.println(cnfCode);
		
try{
			
			cs = con.prepareCall("{call cancelReservation(?,?)}");
			cs.setString(1, email);
			cs.setInt(2, Integer.parseInt(cnfCode.substring(3)));
			
			rs = cs.executeQuery();
		
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("SQL exception while cancelling reservation",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return true;
	}


	public String getAllReservation() throws AppException {
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		String json = new String();
		ArrayList<Reservation> al = new ArrayList<Reservation>();
		
		
		
try{
			
			cs = con.prepareCall("{call getAllReservation()}");
			rs = cs.executeQuery();
				while(rs.next()){
					
						Reservation reserve = new Reservation();
						reserve.setReserve_id(rs.getInt("reserve_id"));
						reserve.setUser_id(rs.getInt("user_id"));
						reserve.setName(rs.getString("name"));
						reserve.setEmail(rs.getString("email"));
						reserve.setPhone(rs.getString("phone"));
						reserve.setDate(rs.getString("reserve_date"));
						reserve.setTime_from(rs.getString("time_from"));
						reserve.setTime_to(rs.getString("time_to"));
						reserve.setReserve_table_id(rs.getInt("reserve_table_id"));
						reserve.setNo_of_people(rs.getInt("no_of_people"));
						reserve.setCreatedBy(rs.getString("created_by"));
						
						al.add(reserve);
				}
				
				json = new Gson().toJson(al);
				
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("SQL exception while fetching reservation",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}

	return json;
		
	}
	
public String getSeatings(String jsonString) throws AppException {
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		String json = new String();
		ArrayList<Seating> al = new ArrayList<Seating>();
		
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		
		
		
try{
			obj = (JSONObject) parser.parse(jsonString);
			cs = con.prepareCall("{call getSeatings(?,?)}");
			cs.setString(1, (String)obj.get("reserve_date"));
			cs.setString(2, (String)obj.get("time_from"));
			rs = cs.executeQuery();
			
				while(rs.next()){
					
						Seating seat = new Seating();
						seat.setTable_id(rs.getInt("table_id"));
						seat.setTable_name(rs.getString("table_name"));
						seat.setTable_capacity(rs.getInt("table_capacity"));
						seat.setReserve_id(rs.getInt("reserve_id"));
						seat.setUser_id(rs.getInt("user_id"));
						seat.setReserve_date(rs.getString("reserve_date"));
						seat.setTime_from(rs.getString("time_from"));
						seat.setTime_to(rs.getString("time_to"));
						seat.setNo_of_people(rs.getInt("no_of_people"));
						seat.setSpecial_arrangement(rs.getString("special_arrangement"));
						seat.setCreated_by(rs.getString("created_by"));
						seat.setName(rs.getString("name"));
						seat.setEmail(rs.getString("email"));
						seat.setPhone(rs.getString("phone"));
						if(rs.getInt("reserve_id") != 0){
							seat.setStatus("Occupied");
						}else{
							seat.setStatus("Empty");
						}
						al.add(seat);
				}
				
				json = new Gson().toJson(al);
				
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("SQL exception while fetching seating",e.getCause());
		}catch(ParseException e){
			e.printStackTrace();
			throw new AppException("Cannot parse data",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}

	return json;
		
	}


public ConfirmationDetails makeSeating (Seating seat) throws AppException{
	Connection con = DbUtils.connectToDb();
	CallableStatement cs = null;
	ResultSet rs = null;
	ConfirmationDetails cd = new ConfirmationDetails();

		
			try{
				cs = con.prepareCall("{call makeNewReservation(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1,seat.getEmail());
				cs.setString(2,seat.getName());
				cs.setString(3,seat.getPhone());
				cs.setString(4,seat.getReserve_date());
				cs.setString(5,seat.getTime_from());
				cs.setInt(6,seat.getNo_of_people());
				cs.setString(7,seat.getSpecial_arrangement());
				cs.setInt(8, seat.getTable_id());
				cs.setString(9, seat.getCreated_by());
				cs.registerOutParameter(10, java.sql.Types.INTEGER);
				
				cs.executeQuery();
				
				
				
				cd.setCnfCode("CNF"+cs.getInt(10));
				cd.setTable_id(seat.getTable_id());
				cd.setTable_name(seat.getTable_name());
				
				
			}catch(SQLException e){
				e.printStackTrace();
				throw new AppException("SQL exception while making reservation",e.getCause());
			}finally{
				DbUtils.closeResources(cs, rs, con);
			}
			
		return cd;
	
}

}
