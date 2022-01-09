package myPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;



public class UserDao {
	
	private ResultSet users ;
	
	
	
	public boolean existUserLogin(String email) throws SQLException, ClassNotFoundException{
		Connection conn =  Connect.connectionFactory().getConn();
		Statement state = (Statement) conn.createStatement();
		users = null;
		users = state.executeQuery("SELECT * FROM login");
		
		while(users.next()){	
			if (email.equals((users.getString("email"))))
				return true;
			}
					return false;
		}
	
	public boolean existUserPass(String email, String pass) throws SQLException, ClassNotFoundException{
		Connection conn =  Connect.connectionFactory().getConn();
		Statement state = (Statement) conn.createStatement();
		users = null;
		users = state.executeQuery("SELECT * FROM login where email = '" + email+"'");	
		
		
		while(users.next()){	
		
			if (PasswordUtils.verifyUserPassword(pass, users.getString("pass"), users.getString("salt")))
				return true;
			}
				return false;
		}
	
}

