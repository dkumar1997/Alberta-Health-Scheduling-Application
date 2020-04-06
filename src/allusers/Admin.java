package allusers;
import java.util.ArrayList;
import hospital_gui.*;
public class Admin {
	private String username;
	private String password;
	private ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	private SQLQUERIES sqlcommands = new SQLQUERIES();
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
		
	}
	
	public void adddoc(String name, String username, String password, String whatdoctor) {
		Doctor newDoc = new Doctor(name,username,password,whatdoctor);
		doctors.add(newDoc);
		sqlcommands.enterinfo(username, password, "doctor");	
		
	}
	public void removedoc(String name) {
		for(Doctor doc : doctors) {
			if(doc.getName().contentEquals(name)) {
				doctors.remove(doc);
				sqlcommands.removeaccount(doc.getUsername(), doc.getPassword());
			}
		}
	}
	public void addnurse(String name, String username, String password, String whatdoctor) {
		Nurse nurse = new Nurse(name,username,password);
		nurses.add(nurse);
		sqlcommands.enterinfo(username, password, "nurse");	
		
	}
	public void removenurse(String name) {
		for(Nurse nurse : nurses) {
			if(nurse.getName().contentEquals(name)) {
				nurses.remove(nurse);
				sqlcommands.removeaccount(nurse.getUsername(), nurse.getPassword());
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public ArrayList<Nurse> getNurses() {
		return nurses;
	}
	
}
