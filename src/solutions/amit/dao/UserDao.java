package solutions.amit.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import solutions.amit.app.AppException;
import solutions.amit.model.Seating;
import solutions.amit.model.User;
import solutions.amit.utils.DbUtils;

public class UserDao {

	
	
	public User getUserInfo(String email) throws AppException{
		
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		User user = new User();
		
		
		try{
			cs = con.prepareCall("{call getUserInfo(?)}");
			cs.setString(1, email);
			rs = cs.executeQuery();
			if(rs.next()){
				user.setUser_id(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				}else{
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Sql Exception while getting user information",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		
		return user;
	}
	
	
	public String getContacts() throws AppException{
		
				Connection con = DbUtils.connectToDb();
				CallableStatement cs = null;
				ResultSet rs = null;
				String json = new String();
				ArrayList<User> al = new ArrayList<User>();
				
		try{

					cs = con.prepareCall("{call getAllUsers()}");
					rs = cs.executeQuery();
					
						while(rs.next()){
							User user = new User();
							user.setUser_id(rs.getInt("user_id"));
							user.setName(rs.getString("name"));
							user.setEmail(rs.getString("email"));
							user.setPhone(rs.getString("phone"));
								al.add(user);
						}
						
						json = new Gson().toJson(al);
						
				}catch(SQLException e){
					e.printStackTrace();
					throw new AppException("SQL exception while fetching Users",e.getCause());
				}finally{
					DbUtils.closeResources(cs, rs, con);
				}
		
			return json;
	}
}
