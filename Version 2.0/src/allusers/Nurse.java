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
	public Nurse(String name, String username, String password) {
		this.name=name;
		this.username = username;
		this.password = password;
		schedule = new Schedules();
	}
	
	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Schedules getSchedule() {
		return schedule;
	}

	public ArrayList<ArrayList<Patient>> getdocschedule(Doctor doctor){
		return doctor.schedule.getSchedule();
	}
}
