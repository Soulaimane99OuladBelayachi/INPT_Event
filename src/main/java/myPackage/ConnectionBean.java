package myPackage;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped

public class ConnectionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String user_id;
    private String prenom;
    private String nom;
    private String telephone;
    private String addresse;
    private String ville;
    private String email;
    private String pass;
    private String eventDessert;
    private String eventType;
    private String eventDate;
    private String invites;
    private String eventPlace;
    private String eventBuffet;
    private String eventMenu;
    
    
    
    
    public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEventDessert() {
		return eventDessert;
	}
	public void setEventDessert(String eventDessert) {
		this.eventDessert = eventDessert;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getInvites() {
		return invites;
	}
	public void setInvites(String invites) {
		this.invites = invites;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	public String getEventBuffet() {
		return eventBuffet;
	}
	public void setEventBuffet(String eventBuffet) {
		this.eventBuffet = eventBuffet;
	}
	public String getEventMenu() {
		return eventMenu;
	}
	public void setEventMenu(String eventMenu) {
		this.eventMenu = eventMenu;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String logIn() {
		
		UserDao user = new UserDao();
		
		try {
			if(user.existUserLogin(this.getEmail())) {
	        	if(user.existUserPass(this.getEmail(), this.getPass())) {
	        		try {
	        			Class.forName("com.mysql.cj.jdbc.Driver");
	                    Connection con=DriverManager.getConnection(
	                            "jdbc:mysql://localhost:3306/Project_JEE","Projects","Projects123456789");

	                    Statement state=con.createStatement();
	                    
	        				ResultSet result = null;
	        				result = state.executeQuery("SELECT * FROM login natural join user_info where email = '"+ this.getEmail()+"';");
	        			while(result.next()){	
	        				setUser_id(result.getString("user_id"));
	        				setPrenom(result.getString("prenom"));
	        				setNom(result.getString("nom"));
	        				setTelephone(result.getString("telephone"));
	        				setAddresse(result.getString("addresse"));
	        				setVille(result.getString("ville"));
	        				setEmail(result.getString("email"));
	                    }
	        			
	        		}catch(ClassNotFoundException | SQLException e) {
	        			e.printStackTrace();
	        		}
	        		
	        		return "commande";
	        	}else {
	        		
	        		return "erreur2";
	        	}
	        }else {
	        	return "erreur1";
               
	        }
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
        return "login";
		
	}
	public String signUp() {
			
			String salt = PasswordUtils.getSalt(30);
	        String passHash =PasswordUtils.generateSecurePassword(this.getPass(), salt);
	        
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con=DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/Project_JEE","Projects","Projects123456789");
	
	            Statement stmt=con.createStatement();
	            String query1 = String.format("INSERT INTO login (email,pass,salt) value ('%s','%s','%s');",this.getEmail(),passHash,salt);
	            String query2 = String.format("INSERT INTO user_info (prenom,nom,telephone,addresse,ville) value ('%s','%s','%s','%s','%s');",this.getPrenom(),this.getNom(),this.getTelephone(),this.getAddresse(),this.getVille());
	            stmt.executeUpdate(query1);
	            stmt.executeUpdate(query2);
	
	            con.close();
	        }catch(Exception e){ System.out.println(e);}
			
			
			return "login";
		}
   
	public String sendCommand() {
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Project_JEE","Projects","Projects123456789");

            Statement stmt=con.createStatement();
            String query = String.format("INSERT INTO commande_info (user_id,command_date,eventDate,eventType,invites,eventPlace,eventBuffet,eventMenu,eventDessert) value ('%s',now(),'%s','%s',%s,'%s','%s','%s','%s');",this.getUser_id(),this.getEventDate(),this.getEventType(),this.getInvites(),this.getEventPlace(),this.getEventBuffet(),this.getEventMenu(),this.getEventDessert());
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
		
		return "recue";
	}
	

}