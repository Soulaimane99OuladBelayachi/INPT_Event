package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connect {
	
	private String url = "jdbc:mysql://localhost:3306/Project_JEE";
	private String user = "Projects";
	private String passwd = "Projects123456789";
	private Connection conn ;
	private static Connect mycnx ;
	
	public static Connect connectionFactory() throws ClassNotFoundException, SQLException{
		if(mycnx == null )
			mycnx =  new Connect();
			
		return mycnx;	
	}
	

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}


	private Connect() {
	
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("DRIVER OK ! ");
		
		conn =  DriverManager.getConnection(url, user, passwd);
		System.out.println("Connection effective !");		
		} catch (Exception e) {
			e.printStackTrace();
		}		
			
}
	
	public Connect(String url, String user, String passwd) {
		super();
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}

}