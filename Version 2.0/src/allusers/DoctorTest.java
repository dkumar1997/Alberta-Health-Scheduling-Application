/**
 * Basic tests for users of account type doctor
 * 
 * @author Abel
 * @version 3.0
 * @since 3.0
 */

package allusers;

import schedule.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DoctorTest {
	
	Admin admin = new Admin("admin", "123");

	 /**
     * User stories #1 create a new account. 
     */
    @Test
    public void createDoctor(){
        int count;
        try{
        Doctor newDoctor = new Doctor("newDoctor", "newDoc", "pass", "general");
        count = admin.getDoctors().size();
        count++; 
        admin.adddoc(newDoctor.getName(), newDoctor.getUsername(), newDoctor.getPassword(), newDoctor.getWhatdoctor());
        assertEquals(count, admin.getDoctors().size());}
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
    		Doctor newDoctor = new Doctor("newDoctor", "newDoc", "pass", "general");
    		admin.adddoc(newDoctor.getName(), newDoctor.getUsername(), newDoctor.getPassword(), newDoctor.getWhatdoctor());
            assertEquals(true, admin.getSQL().checkaccount(newDoctor.getUsername(), newDoctor.getPassword()));}
            catch(Exception e){
                System.out.println(e);
            }
    }
    
    /**
     * User story 4 to be able to see add shift. 
     */
   @Test
   public void requestSchedule() {
	   try {
	   ArrayList<ArrayList<Patient>> alldays = new ArrayList<ArrayList<Patient>>();
	   Doctor newDoctor = new Doctor("newDoctor", "newDoc", "pass", "general");
	   Patient patient = new Patient();
	   Schedules ssch = newDoctor.getSchedule();
	   ssch.addappointment(patient, 15);
	   alldays.get(15).add(patient); 
	   assertEquals(alldays, ssch.getSchedule());
	   }catch(Exception e) {
		   System.out.println(e);
	   }
   }
   
   /**
    * User story 5 to be able to see remove shift. 
    */
   @Test
   public void removeShift() {
	   try {
		   ArrayList<ArrayList<Patient>> alldays = new ArrayList<ArrayList<Patient>>();
		   Doctor newDoctor = new Doctor("newDoctor", "newDoc", "pass", "general");
		   Patient patient = new Patient();
		   Schedules ssch = newDoctor.getSchedule();
		   ssch.addappointment(patient, 15);
		   alldays.get(15).add(patient); 
		   ssch.removeappointment(15, patient);
		   alldays.get(15).remove(1);
		   assertEquals(alldays, ssch.getSchedule());
	   }catch(Exception e){
		   System.out.println(e);
	   }
   }

   /**
    * User story 6 to be able to see nurse schedules. 
    */
   @Test
   public void checkSchedule() {
   try {
   	Nurse newNurse = new Nurse("newNurse", "newNur", "pass");
   	Doctor doc = new Doctor("doc", "doc", "pass", "general");
   	ArrayList<ArrayList<Patient>> sch = newNurse.getSchedule().getSchedule(); 
   	assertEquals(sch, doc.getNurseSchedule(newNurse));
       
   }catch(Exception e) {
   	System.out.println(e);
   }
   }

   
}
