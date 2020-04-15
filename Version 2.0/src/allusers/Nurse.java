/**
 * Basic functionalities for users of account type nurse
 * 
 * @author Dheeraj
 * @version 3.0
 * @since 2.0
 */

package allusers;
import schedule.Schedules;
import java.util.ArrayList;
public class Nurse {
	public String name;
	public String username;
	public String password;
	public Schedules schedule;

	/*
	 * Constructor for the Doctor class Takes 3 parameters that will define the
	 * instance varibales other than schedule.
	 */
	public Nurse(String name, String username, String password) {
		this.name=name;
		this.username = username;
		this.password = password;
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
	 * getter method that will return the schedule.
	 */
	public Schedules getSchedule() {
		return schedule;
	}

	/*
	 * getter method that will return a doctors schedule allowing nurses to view times the doctor is working, in appointments
	 * or whenever they are free.
	 */
	public ArrayList<ArrayList<Patient>> getdocschedule(Doctor doctor){
		return doctor.schedule.getSchedule();
	}
}
