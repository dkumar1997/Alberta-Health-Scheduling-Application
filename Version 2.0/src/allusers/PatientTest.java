/**
 * Basic tests for users of account type patient
 * 
 * @author Abel
 * @version 3.0
 * @since 3.0
 */



package allusers;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import hospital_gui.SQLQUERIES;

class PatientTest {

	Admin admin = new Admin("admin", "123");
	
	 /**
     * User stories #1 to be able to create an account. 
     */
	@Test
	public void createAccount() {
		String fname = "Kobe";
        String last_name = "Bryant";
        String email = "kobe@gmail.com";
        String address = "40 St NE";
        String phone_number = "4031234567";
        String dob = "04 13 1980";
        String speciality = "general";
        String role = "patient";
        int patientID;
        String fnametest;
        try{
        patientID = admin.getSQL().getPatientid(fname, last_name);
        fnametest = admin.getSQL().getinfo(patientID, "role");
        assertEquals(fnametest, "patient");
        }
        catch(Exception e){
            System.out.println(e);
        }
	}
	
	
	 /**
     * User stories #2 to be able to login. 
     */
	@Test
	public void login() {
        try{
        admin.getSQL().enterinfo("Kobe", "pass", "patient");
        assertEquals(true, admin.getSQL().checkaccount("Kobe","pass" ));
        }
        catch(Exception e){
            System.out.println(e);
        }
	}
	
	 /**
     * User stories #3 to get a doctors schedule.  
     */
	@Test
	public void getDocSchedule() {
		Patient patient = new Patient();
		Doctor doc = new Doctor("doc", "doc", "pass", "general");
    	ArrayList<ArrayList<Patient>> sch = doc.getSchedule().getSchedule(); 
    	assertEquals(sch, patient.getdocschedule(doc));
	}
	
	
	 /**
     * User stories #6 to get specialist info.  
     */
	@Test
	public void getSpecialistInfo() {
		try {
		Patient patient = new Patient();
		Doctor doc = new Doctor("doc", "docs", "passS", "Cardiologist");
		admin.getSQL().enterinfo("docs", "passS", doc.getWhatdoctor());
		ArrayList<ArrayList<Patient>> sch = doc.getSchedule().getSchedule(); 
		String type = admin.getSQL().getrole(doc.getUsername(), doc.getPassword());
		assertEquals(type, doc.getWhatdoctor());
    	assertEquals(sch, patient.getdocschedule(doc));
		}catch(Exception e) {
			System.out.println(e);
		}
		
		}
		
	
	
	

}
