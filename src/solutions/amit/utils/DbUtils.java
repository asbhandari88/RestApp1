package solutions.amit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils {
	
	private final static String URL="jdbc:mysql://localhost:3306/restapp";
	private final static String USER="root";
	private final static String PASSWORD="";

		static{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Connection connectToDb(){
			Connection con = null;
			
			try {
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection is successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error establishing the connection : "+e.getMessage());
			}
			
			return con;
		}
		
		public static void closeResources(CallableStatement cs,ResultSet rs,Connection con){
			try{
				if(cs != null){
					cs.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			}catch(SQLException e){
				 
			}
		}
		
		public static void main(String args[]){
			
			DbUtils.connectToDb();
		}
	
}
