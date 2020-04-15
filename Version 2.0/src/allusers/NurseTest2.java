/**
 * Basic tests for users of account type nurse
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

class NurseTest2 {

	Admin admin = new Admin("admin", "123");
	
	
	

    /**
     * User stories #1 create a new account. 
     */
    @Test
    public void createNurse(){
        int count;
        try{
        Nurse newNurse = new Nurse("newNurse", "newNur", "pass");
        count = admin.getNurses().size();
        count++; 
        admin.addnurse(newNurse.getName(), newNurse.getUsername(), newNurse.getPassword(), "general");
        assertEquals(count, admin.getNurses().size());}
        catch(Exception e){
            System.out.println(e);
        }
       
    }
    
    /**
     * User story 2 to see if you can login successfully. 
     */
    @Test
    public void login() {
    	try{
            Nurse newNurse = new Nurse("newNurse", "newNur", "pass");
            admin.addnurse(newNurse.getName(), newNurse.getUsername(), newNurse.getPassword(), "general");
            assertEquals(true, admin.getSQL().checkaccount(newNurse.getUsername(), newNurse.getPassword()));}
            catch(Exception e){
                System.out.println(e);
            }
    }
    

    /**
     * User stories #3 to be able to see patient info.
     */
    @Test
    public void viewRecords() {
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
        
        SQLQUERIES sql = admin.getSQL();
        Nurse newNurse = new Nurse("newNurse", "newNur", "pass");
        admin.addnurse(newNurse.getName(), newNurse.getUsername(), newNurse.getPassword(), "general");
        String roletest = admin.getSQL().getrole(newNurse.getUsername(), newNurse.getPassword());
        if(roletest.equals("nurse")){
            sql.setinfo(fname, last_name, email, address, phone_number, dob, speciality, role);
            patientID = sql.getPatientid(fname, last_name);
            fnametest = sql.getinfo(patientID, "first_name");
        }
        else{
            fnametest = "nope";
        }
        assertEquals(fnametest, fname);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    /**
     * User stories 4-6 to be able to see others' schedules. 
     */
    @Test
    public void checkSchedule() {
    try {
    	Nurse newNurse = new Nurse("newNurse", "newNur", "pass");
    	Doctor doc = new Doctor("doc", "doc", "pass", "general");
    	ArrayList<ArrayList<Patient>> sch = doc.getSchedule().getSchedule(); 
    	assertEquals(sch, newNurse.getdocschedule(doc));
        
    }catch(Exception e) {
    	System.out.println(e);
    }
    }


}
