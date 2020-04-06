package schedule;
import allusers.Patient;
import java.util.ArrayList;
public class Schedules {
	private String month;
	private ArrayList<ArrayList<Patient>> alldays = new ArrayList<ArrayList<Patient>>();
	
	// months with 31 days January, March, May, July, Aug, Oct, dec
	// months with 30 days April, June, Sept, Nov
	// months with 29 days Feb
	public int addappointment(Patient patient, int day) {
		
		if(day < 1 || day > 31) {
			return -1;
		}
		// this makes sure that the doctor will only have 5 patients max a day
		if(alldays.get(day).size() <= 5) {
			alldays.get(day).add(patient);
		}
		
		return 0;
	}
	
	public void clearschedule() {
		for(int i = 0; i <alldays.size(); i++) {
			alldays.get(i).clear();
		}
	}
	public int removeappointment(int day, Patient patient) {
		if(day < 1 || day > 31) {
			return -1;
		}
		// this makes sure that the doctor will only have 5 patients max a day
		if(alldays.get(day).contains(patient)) {
			alldays.get(day).remove(patient);
		}
		
		return 0;
	}		
	
	
	public void setmonth(String month) {
		this.month = month;
	}
	public ArrayList<ArrayList<Patient>> getSchedule(){
		return alldays;
	}

}
