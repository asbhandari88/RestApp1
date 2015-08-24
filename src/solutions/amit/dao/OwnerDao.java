package solutions.amit.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import solutions.amit.app.AppException;
import solutions.amit.model.Auth;
import solutions.amit.model.Owner;
import solutions.amit.utils.DbUtils;

public class OwnerDao {

	public boolean ownerAuthenticate(Auth auth) throws AppException{
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try{
			cs = con.prepareCall("{call authenticate_user(?,?)}");
			cs.setString(1, auth.getUsername());
			cs.setString(2, auth.getPassword());
			rs = cs.executeQuery();
			if(rs.next()){
				if(rs.getInt("count") != 1){
					return false;
				}
			}else{
				System.out.println("Result set empty");
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Sql Exception while authenticating user",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return true;
		
	}
	
	
	public Owner getOwnerInfo(String username) throws AppException{
		
		Connection con = DbUtils.connectToDb();
		CallableStatement cs = null;
		ResultSet rs = null;
		Owner ow = new Owner();
		try{
			cs = con.prepareCall("{call getOwnerInfo(?)}");
			cs.setString(1, username);
			rs = cs.executeQuery();
			if(rs.next()){
				ow.setEmp_id(rs.getInt("emp_id"));
				ow.setName(rs.getString("name"));
				ow.setUsername(rs.getString("username"));
				ow.setPassword(rs.getString("password"));
				ow.setPhone(rs.getString("phone"));
				ow.setPosition(rs.getString("position"));
				ow.setAddress(rs.getString("address"));
				ow.setState(rs.getString("state"));
				ow.setCity(rs.getString("city"));
				ow.setZip(rs.getInt("zip"));
			}else{
				System.out.println("Result set empty");
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Sql Exception while fetching user information",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return ow;
	}
	
public Owner setOwnerInfo(Owner owner) throws AppException{
		
		Connection con = DbUtils.connectToDb();
		ResultSet rs = null;
		CallableStatement cs = null;
		Owner result_ow = new Owner();
		try{
			cs = con.prepareCall("{call setOwnerInfo(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, owner.getEmp_id());
			cs.setString(2,owner.getName());
			cs.setString(3,owner.getUsername());
			cs.setString(4,owner.getPhone());
			cs.setString(5,owner.getPosition());
			cs.setString(6,owner.getAddress());
			cs.setString(7,owner.getState());
			cs.setString(8,owner.getCity());
			cs.setInt(9,owner.getZip());
			cs.executeQuery();
			result_ow = getOwnerInfo(owner.getUsername());
		}catch(SQLException e){
			e.printStackTrace();
			throw new AppException("Sql Exception while setting user information",e.getCause());
		}finally{
			DbUtils.closeResources(cs, rs, con);
		}
		
		return result_ow;
	}

public boolean changeOwnerPassowrd(Owner own) throws AppException{
	
	Connection con = DbUtils.connectToDb();
	CallableStatement cs = null;
	ResultSet rs = null;
	try{
		cs = con.prepareCall("{call changeOwnerPassowrd(?,?)}");
		cs.setInt(1, own.getEmp_id());
		cs.setString(2,own.getPassword());
		cs.executeQuery();
		
	}catch(SQLException e){
		e.printStackTrace();
		throw new AppException("Sql Exception while updating password ",e.getCause());
	}finally{
		DbUtils.closeResources(cs, rs, con);
	}
	
	
	return true;
}
	
}
