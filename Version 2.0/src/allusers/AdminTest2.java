/**
 * Basic tests for users of account type admin
 * 
 * @author Abel
 * @version 3.0
 * @since 3.0
 */


package allusers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hospital_gui.SQLQUERIES;

class AdminTest2 {

	private Admin admin = new Admin("admin", "123");

    /**
	 * Follows user story #1 for Admin and creates a new account for admin. 
	 */
    @Test
    public void createAdmin(){
        String username = "newAd";
        String password = "pass";
        Admin newAdmin = new Admin(username, password);
        assertEquals(username, newAdmin.getUsername());
        assertEquals(password, newAdmin.getPassword());
    }
    
    
    /**
     * User story 2 to see if you can login successfully. 
     */
    @Test
    public void login() {
    	try{
            assertEquals(true, admin.getSQL().checkaccount(admin.getUsername(), admin.getPassword()));}
            catch(Exception e){
                System.out.println(e);
            }
    }

    /**
     * Follows user story #4 for Admin and creates a new employee(doctor).
     */
    @Test
    public void testAddDoc(){
        int count = admin.getDoctors().size();
        count++; 
        String docName = "Test Doctor";
        String username = "newdoc";
        String password = "newpass";
        String whatdoctor = "General"; 

        try{
            admin.adddoc(docName, username, password, whatdoctor);
            assertEquals(count, admin.getDoctors().size());
        }catch(Exception e){
            System.out.println(e);
        }
       
    }
    
    /**
     * Follows user story #4 for Admin and creates a new employee(nurse).
     */
    @Test
    public void testAddNurse() {
        int count = admin.getNurses().size();
        count++;
        String name = "nurse1";
        String username = "newnurse";
        String password = "newpass";
        String whatdoctor = "General";

        try {
            admin.addnurse(name, username, password, whatdoctor);
            assertEquals(count, admin.getNurses().size());
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /**
     * Follows user story #7 for Admin and removes an employee(nurse).
     */
    @Test
    public void testRemoveNurse() {
        int count = admin.getNurses().size();
        String name = "nurse2";
        String username = "newnurse";
        String password = "newpass";
        String whatdoctor = "General";

        try {
            admin.addnurse(name, username, password, whatdoctor);
            admin.removenurse(name);
            assertEquals(count, admin.getNurses().size());
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /**
     * Follows user story #7 for Admin and removes an employee(doctor).
     */
    @Test
    public void testRemoveDoctor() {
        int count = admin.getDoctors().size();
        String name = "doctors2";
        String username = "newnurse";
        String password = "newpass";
        String whatdoctor = "General";

        try {
            admin.adddoc(name, username, password, whatdoctor);
            admin.removedoc(name);
            assertEquals(count, admin.getDoctors().size());
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /**
     * Follows user stories 2 and 5 as you can create a new patient and edit their info. 
     */
    @Test 
    public void setPatientRecords(){
        String fname = "Kobe";
        String last_name = "Bryant";
        String email = "kobe@gmail.com";
        String address = "40 St NE";
        String phone_number = "4031234567";
        String dob = "04 13 1980";
        String speciality = "general";
        String role = "patient";
        int ID; 
        SQLQUERIES sql = admin.getSQL();
        try{
        sql.setinfo(fname, last_name, email, address, phone_number, dob, speciality, role);
        ID = sql.getPatientid(fname, last_name);
        assertEquals(fname, sql.getinfo(ID, "first_name"));
        }
        catch(Exception exception){
            System.out.println(exception);
        }
       
    }

    /**
     * Follows user story #6 as it allows you to edit the employees information. 
     */
    @Test
    public void setEmployeeRecords() {
        String fname = "Abel";
        String last_name = "Bryant";
        String email = "kobe@gmail.com";
        String address = "40 St NE";
        String phone_number = "4031234567";
        String dob = "04 13 1980";
        String speciality = "general";
        String role = "doctor";
        int ID;
        SQLQUERIES sql = admin.getSQL();
        try {
            sql.setinfo(fname, last_name, email, address, phone_number, dob, speciality, role);
            ID = sql.getdoctorid(fname, last_name);
            assertEquals(fname, sql.getinfo(ID, "first_name"));
        } catch (Exception exception) {
            System.out.println(exception);
        }
       
    }

}
