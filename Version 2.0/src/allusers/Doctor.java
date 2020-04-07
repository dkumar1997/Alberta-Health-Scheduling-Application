package allusers;
import schedule.Schedules;
public class Doctor {
	private String name;
	private String username;
	private String password;
	private String whatdoctor;
	public Schedules schedule;
	
	
	public Doctor(String name, String username, String password, String whatdoctor) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.whatdoctor = whatdoctor;
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
	public String getWhatdoctor() {
		return whatdoctor;
	}


	
}
