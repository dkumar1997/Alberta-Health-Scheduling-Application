package hospital_gui;


import java.sql.*;
import java.util.ArrayList;

public class SQLQUERIES {
	
	public Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/new_sschema";
			String username = "root";
			String password = "sqlPassword";
			Class.forName(driver);
			
			Connection firstcon = DriverManager.getConnection(url, username, password);
			System.out.println("It connected");
			return firstcon;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
		
	}

	public boolean checkuser(String username_input) {
		try {
			Connection con = getConnection();
			String query = "SELECT username FROM allaccounts WHERE username = '" + username_input+ "'";
			PreparedStatement ifuser = con.prepareStatement(query);
			ResultSet result = ifuser.executeQuery();
			result.next();
		
			String value = result.getString("username");
			
			System.out.println(value);
			return false;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	public boolean checkaccount(String username_input, String password_input) {
		try {
			
			//over here first we establish a connection then we make our query statement
			//after that we prepare are statement
			//then we execute the statement and store the results
			//we move result.next by one because the first row has nothing useful
			
			Connection con = getConnection();
			String query = String.format("SELECT username, password FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username_input, password_input);
			PreparedStatement ifmember = con.prepareStatement(query);
			ResultSet result = ifmember.executeQuery();
			result.next();
			
			//just a double check the bottom 3 lines scan be removed
			String username = result.getString("username");
			String password = result.getString("password");
			System.out.println(username + " "+ password);
			return true;
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}
	public void removeaccount(String username, String password) {
		try {
			Connection con = getConnection();
			String query = String.format("DELETE FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username, password);
			PreparedStatement remove = con.prepareStatement(query);
			remove.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
		}
			
		
	}
	public String getrole(String username_input, String password_input) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT role FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"" , username_input, password_input);
			PreparedStatement givemerole = con.prepareStatement(query);
			ResultSet result = givemerole.executeQuery();
			result.next();
			String role = result.getString("role");
			return role;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	public void enterinfo(String username, String password, String role){
		try {
			Connection con = getConnection();
			PreparedStatement enterinfo = con.prepareStatement("INSERT INTO allaccounts (username, password, role) VALUES ('"+username+"','"+password+"','"+role+"')");
			enterinfo.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void setinfo(String first_name, String last_name,String email, String address, String phone_number, String dob, String speciality, String role) {
		try {
			Connection con = getConnection();
			String query = String.format("INSERT INTO user_info (first_name , last_name, email, address, phone_number, DOB, speciality, role) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\",\"%s\",\"%s\", \"%s\")",first_name, last_name, email, address, phone_number, dob, speciality, role );
			PreparedStatement insertinfo = con.prepareStatement(query);
			insertinfo.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void cleardatabase() {
		try {
			Connection con = getConnection();
			String query = String.format("TRUNCATE TABLE allaccounts");
			PreparedStatement remove = con.prepareStatement(query);
			remove.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
		}
			
		
	}
	public int getdoctorid(String firstname, String lastname) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT id FROM user_info WHERE first_name = \"%s\" AND last_name = \"%s\"", firstname, lastname);
			PreparedStatement givemedoc = con.prepareStatement(query);
			ResultSet result = givemedoc.executeQuery();
			result.next();
			int docint = result.getInt("id");
			return docint;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	public void deletedoctor(int id) {
		try {
			Connection  con = getConnection();
			String query = String.format("DELETE allaccounts, user_info FROM allaccounts JOIN user_info ON allaccounts.id = user_info.id WHERE allaccounts.id = %d", id);
			PreparedStatement deletedoc = con.prepareStatement(query);
			deletedoc.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public int getmecount(String role) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT COUNT(*) FROM user_info WHERE role = \"%s\"", role);
			PreparedStatement getmecount = con.prepareStatement(query);
			ResultSet result = getmecount.executeQuery();
			result.next();
			int count = result.getInt("COUNT(*)");
			return count;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	public int getmecount(String role, String speciality) {
		try {
			Connection con = getConnection();
			String query= String.format("SELECT COUNT(*) FROM user_info WHERE role = \"%s\" AND speciality = \"%s\"", role, speciality);
			PreparedStatement getmecount = con.prepareStatement(query);
			ResultSet result = getmecount.executeQuery();
			result.next();
			int count = result.getInt("COUNT(*)");
			return count;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// given user id get me a certain piece of information
	public String getinfo(int user_id, String whatdouwant) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT %s FROM user_info WHERE id = %d ", whatdouwant, user_id);
			PreparedStatement getme = con.prepareStatement(query);
			ResultSet result = getme.executeQuery();
			result.next();
			String what = result.getString(whatdouwant);
			return what;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "nothing";
	}
	
	public int getid(String username, String password) {
		try { 
			Connection con = getConnection();
			String query = String.format("SELECT id FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username, password);
			PreparedStatement getid = con.prepareStatement(query);
			ResultSet result = getid.executeQuery();
			result.next();
			int id = result.getInt("id");
			return id;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return -1;
	}
	
	public ArrayList<String> getspecificdoctor(String role, String speciality) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT first_name, last_name FROM user_info WHERE role = \"%s\" AND speciality = \"%s\"", role, speciality);
			PreparedStatement getinfo = con.prepareStatement(query);
			ResultSet result = getinfo.executeQuery();
			ArrayList<String> alldoctors = new ArrayList<String>();
			
			while(result.next()) {
				alldoctors.add(result.getString("first_name") + " " + result.getString("last_name"));
				
			}
			return alldoctors;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
}














