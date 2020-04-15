/**
 * Basic functionalities for users of account type admin.
 * Admins can add/remove user accounts of any type.
 * 
 * @author Dheeraj
 * @version 3.0
 * @since 2.0
 */

package allusers;
import java.util.ArrayList;
import hospital_gui.*;
public class Admin {
 
	/*
	 * Private instance variables
	 */
	private String username;
	private String password;
	private ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	private SQLQUERIES sqlcommands = new SQLQUERIES();
	
	/*
	 * constructor for the Admin class that takes 2 parameters:
	 * username- admins username.
	 * passowrd- the password that aligns with the the username.
	 */
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
		
	}
	
	/*
	 * Method for adding a new doctor to the whole system. 
	 * Takes a name, username, password and what kind of doctor they are and adds it to the database.  
	 */
	public void adddoc(String name, String username, String password, String whatdoctor) {
		Doctor newDoc = new Doctor(name,username,password,whatdoctor);
		doctors.add(newDoc);
		sqlcommands.enterinfo(username, password, "doctor");	
		
	}

	/*
	 * Method for removing doctor. 
	 * Takes only a name and removes the doctor from the hospital database. 
	 */
	public void removedoc(String name) {
		for(Doctor doc : doctors) {
			if(doc.getName().contentEquals(name)) {
				doctors.remove(doc);
				sqlcommands.removeaccount(doc.getUsername(), doc.getPassword());
			}
		}
	}

	/*
	 * Method for adding new nurse. 
	 * Similar to adding doctors above. 
	 */
	public void addnurse(String name, String username, String password, String whatdoctor) {
		Nurse nurse = new Nurse(name,username,password);
		nurses.add(nurse);
		sqlcommands.enterinfo(username, password, "nurse");	
		
	}


	/*
	 * Method for removing nurse.
	 * Similar tot removing doctors above. 
	 */
	public void removenurse(String name) {
		for(Nurse nurse : nurses) {
			if(nurse.getName().contentEquals(name)) {
				nurses.remove(nurse);
				sqlcommands.removeaccount(nurse.getUsername(), nurse.getPassword());
			}
		}
	}

	/*
	 * Getter method for username.
	 * returns a string that represents username. 
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * Getter method for password.
	 * returns a string that represents the password. 
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * Getter method for an array containing all the doctors. 
	 */
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	/*
	 * Getter method for an array containing all the nurses.
	 */
	public ArrayList<Nurse> getNurses() {
		return nurses;
	}
	
}
