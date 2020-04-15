/**
 * Basic functionalities for users of account type doctor
 * 
 * @author Dheeraj, Abel
 * @version 3.0
 * @since 2.0
 */

package allusers;
import java.util.ArrayList;

import schedule.Schedules;
public class Doctor {

	/*
	 * Private Instance Variables: 
	 */
	private String name;
	private String username;
	private String password;
	private String whatdoctor;
	public Schedules schedule;
	
	/*
	 * Constructor for the Doctor class
	 * Takes 4 parameters that will define the instance varibales other than schedule. 
	 */
	public Doctor(String name, String username, String password, String whatdoctor) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.whatdoctor = whatdoctor;
		schedule = new Schedules();
	}
	
	
	/*
	* getter method that will return a string representing the name. 
	 */
	public String getName() {
		return name;
	}

	/*
	 * getter method that will return a string representing the username. 
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * getter method that will return a string representing the password. 
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * getter method that will return a string representing the password. 
	 */
	public String getWhatdoctor() {
		return whatdoctor;
	}
	
	/*
	 * getter method that will return the schedule. 
	 */
	public Schedules getSchedule() {
		return schedule;
	}
	
	/*
	 * getter method that will return the schedule of a nurse. 
	 */
	public ArrayList<ArrayList<Patient>> getNurseSchedule(Nurse nurse){
		return nurse.getSchedule().getSchedule();
	}
}
