/**
 * Class to handle all SQL queries.
 * Queries exist for establishing a connection,
 * 	basic user account functionalities (create/delete,
 * 	check if user exists, check if password is correct),
 * 	and for getting doctor information.
 * 
 * @author Dheeraj
 * @version 3.0
 * @since 3.0
 * 
 */

package hospital_gui;


import java.sql.*;
import java.util.ArrayList;

public class SQLQUERIES {
	
	/**
	 * Establish connection to SQL database.
	 * 
	 * Must be modified for each connection to localhost
	 * 
	 * @return Connection object
	 * @throws Exception unsuccessful connection
	 */
	public Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/seng300";
			String username = "root";
			String password = "87LC**pm45mysql";
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

	/**
	 * Check if user account exists
	 * 
	 * @param username_input
	 * @return true or false
	 */
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
	
	/**
	 * Check if password corresponds to username
	 * 
	 * @param username_input
	 * @param password_input
	 * @return true or false
	 */
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
	
	/**
	 * Delete user account
	 * 
	 * @param username_input
	 * @param password_input
	 */
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
	
	/**
	 * Get the "role" of a user account 
	 * 	(general, doctor, nurse, or admin)
	 * 
	 * @param username_input
	 * @param password_input
	 * @return string specifying account type
	 */
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
	
	/**
	 * Add new account to database
	 * 
	 * @param username
	 * @param password
	 * @param role
	 */
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
	
	/**
	 * Specify all user information for an account in the database
	 * 
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param address
	 * @param phone_number
	 * @param dob
	 * @param speciality
	 * @param role
	 */
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
	
	/**
	 * Empty contents of account table
	 */
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
	
	/**
	 * Specify doctor first name and last name to get 
	 * 	unique identifier 
	 * 
	 * @param firstname
	 * @param lastname
	 * @return int for doctor ID
	 */
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
	
	/**
	 * For admins to remove account of type doctor
	 * 	from account and user information tables
	 * @param id
	 */
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
	
	/**
	 * Count the number of users of a specific role
	 * 
	 * @param role
	 * @return int of number of accounts of a specific role
	 */
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
	
	/**
	 * Count the number of users of a certain specialty
	 * 
	 * @param role
	 * @param speciality
	 * @return int of number of users of a specialty 
	 */
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
	
	/**
	 * Get desired piece of user information after specifying user ID.
	 * 
	 * @param user_id patient of interest
	 * @param whatdouwant user information column to be extracted
	 * @return String containing desired information
	 */
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
	
	/**
	 * Get user id of an account
	 * 
	 * @param usernamne
	 * @param password
	 * @return int user ID
	 */
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
	
	/**
	 * Get an array of doctors of a specified role and specialty
	 * 
	 * @param role
	 * @param speciality
	 * @return ArrayList of doctor names (first and last)
	 */
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