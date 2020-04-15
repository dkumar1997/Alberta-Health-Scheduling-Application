/**
 * Class that handles queries and communication to SQL database.
 * 
 * Currently includes functions for: creating a connection, user account
 * functionality (create/remove/get account by name or id, and so on), 
 * hospital summary statistics, booking information (create/get/remove appointments),
 * and creating and checking referral codes.
 * 
 * @author Dheeraj, Nick, Stefan
 * @version 3.0
 * @since 3.0
 * 
 */

package hospital_gui;


import java.sql.*;
import java.util.ArrayList;

public class SQLQUERIES {
	
	// Connects to the SQL database. 
	// *** You will need to import the dump file on to your own computer using MYSQLWORKBENCH in order to run our code. ***
	public Connection getConnection() throws Exception{
		try {
			// CHange password and url to match with that of your own computer. 
			String driver = "com.mysql.jdbc.Driver";
<<<<<<< HEAD
			String url = "jdbc:mysql://localhost:3306/project2";

			
			String username = "root";
			String password = "@Yohannes21";
=======
			String url = "jdbc:mysql://localhost:3306/new_sschema";

			
			String username = "root";
			String password = "sqlPassword";
>>>>>>> 3f599265ad52a2d210d4cb1cb08abce7b1fb2bf5
			Class.forName(driver);
			
			Connection firstcon = DriverManager.getConnection(url, username, password);
			System.out.println("It connected");
			return firstcon;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
		
	}

	// Checks if a user exists. 
	public boolean checkuser(String username_input) {
		try {
			Connection con = getConnection();
			String query = "SELECT username FROM allaccounts WHERE username = '" + username_input+ "'";
			PreparedStatement ifuser = con.prepareStatement(query);
			ResultSet result = ifuser.executeQuery();
			result.next();
		
			String value = result.getString("username");
			
			System.out.println(value);
			return false;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	// CHeck if the account matches using the username and password inputs. 
	public boolean checkaccount(String username_input, String password_input) {
		try {
			
			//over here first we establish a connection then we make our query statement
			//after that we prepare are statement
			//then we execute the statement and store the results
			//we move result.next by one because the first row has nothing useful
			
			Connection con = getConnection();
			String query = String.format("SELECT username, password FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username_input, password_input);
			PreparedStatement ifmember = con.prepareStatement(query);
			ResultSet result = ifmember.executeQuery();
			result.next();
			
			//just a double check the bottom 3 lines scan be removed
			String username = result.getString("username");
			String password = result.getString("password");
			System.out.println(username + " "+ password);
			return true;
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Removes an account from the database using the username and password to match it. 
	public void removeaccount(String username, String password) {
		try {
			Connection con = getConnection();
			String query = String.format("DELETE FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username, password);
			PreparedStatement remove = con.prepareStatement(query);
			remove.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
		}
			
		
	}

	// Returns if the role is patient, doctor, nurse, etc ...
	public String getrole(String username_input, String password_input) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT role FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"" , username_input, password_input);
			PreparedStatement givemerole = con.prepareStatement(query);
			ResultSet result = givemerole.executeQuery();
			result.next();
			String role = result.getString("role");
			return role;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	// Allows you to add new info. 
	public void enterinfo(String username, String password, String role){
		try {
			Connection con = getConnection();
			PreparedStatement enterinfo = con.prepareStatement("INSERT INTO allaccounts (username, password, role) VALUES ('"+username+"','"+password+"','"+role+"')");
			enterinfo.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	// Allows you to set info suing all 8 fields in the parameters. 
	public void setinfo(String first_name, String last_name,String email, String address, String phone_number, String dob, String speciality, String role) {
		try {
			Connection con = getConnection();
			String query = String.format("INSERT INTO user_info (first_name , last_name, email, address, phone_number, DOB, speciality, role) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\",\"%s\",\"%s\", \"%s\")",first_name, last_name, email, address, phone_number, dob, speciality, role );
			PreparedStatement insertinfo = con.prepareStatement(query);
			insertinfo.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	// Clears the database so you can start fresh. 
	public void cleardatabase() {
		try {
			Connection con = getConnection();
			String query = String.format("TRUNCATE TABLE allaccounts");
			PreparedStatement remove = con.prepareStatement(query);
			remove.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
		}
			
		
	}

	// Returns doctor ID
	public int getdoctorid(String firstname, String lastname) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT id FROM user_info WHERE first_name = \"%s\" AND last_name = \"%s\"", firstname, lastname);
			PreparedStatement givemedoc = con.prepareStatement(query);
			ResultSet result = givemedoc.executeQuery();
			result.next();
			int docint = result.getInt("id");
			return docint;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	// Removes a doctor from the database. 
	public void deletedoctor(int id) {
		try {
			Connection  con = getConnection();
			String query = String.format("DELETE allaccounts, user_info FROM allaccounts JOIN user_info ON allaccounts.id = user_info.id WHERE allaccounts.id = %d", id);
			PreparedStatement deletedoc = con.prepareStatement(query);
			deletedoc.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	// Returns the count. using only role. 
	public int getmecount(String role) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT COUNT(*) FROM user_info WHERE role = \"%s\"", role);
			PreparedStatement getmecount = con.prepareStatement(query);
			ResultSet result = getmecount.executeQuery();
			result.next();
			int count = result.getInt("COUNT(*)");
			return count;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	//returns the count using a role and specialty. 
	public int getmecount(String role, String speciality) {
		try {
			Connection con = getConnection();
			String query= String.format("SELECT COUNT(*) FROM user_info WHERE role = \"%s\" AND speciality = \"%s\"", role, speciality);
			PreparedStatement getmecount = con.prepareStatement(query);
			ResultSet result = getmecount.executeQuery();
			result.next();
			int count = result.getInt("COUNT(*)");
			return count;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// given user id get me a certain piece of information
	public String getinfo(int user_id, String whatdouwant) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT %s FROM user_info WHERE id = %d ", whatdouwant, user_id);
			PreparedStatement getme = con.prepareStatement(query);
			ResultSet result = getme.executeQuery();
			result.next();
			String what = result.getString(whatdouwant);
			return what;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "nothing";
	}
	
	// returns patient ID as an int. 
	public int getPatientid(String fName, String lName) {
		try { 
			Connection con = getConnection();
			String query = String.format("SELECT id FROM user_info WHERE first_name = \"%s\" AND last_name = \"%s\"", fName, lName);
			PreparedStatement getid = con.prepareStatement(query);
			ResultSet result = getid.executeQuery();
			result.next();
			int id = result.getInt("id");
			return id;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return -1;
	}
	
	// Returns ID using a username and password. 
	public int getid(String username, String password) {
		try { 
			Connection con = getConnection();
			String query = String.format("SELECT id FROM allaccounts WHERE username = \"%s\" AND password = \"%s\"", username, password);
			PreparedStatement getid = con.prepareStatement(query);
			ResultSet result = getid.executeQuery();
			result.next();
			int id = result.getInt("id");
			return id;
		}
		catch (Exception e){
			System.out.println(e);
		}
		return -1;
	}
	
	// returns an arraylist of doctors that fit into the role and specialty. 
	public ArrayList<String> getspecificdoctor(String role, String speciality) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT first_name, last_name FROM user_info WHERE role = \"%s\" AND speciality = \"%s\"", role, speciality);
			PreparedStatement getinfo = con.prepareStatement(query);
			ResultSet result = getinfo.executeQuery();
			ArrayList<String> alldoctors = new ArrayList<String>();
			
			while(result.next()) {
				alldoctors.add(result.getString("first_name") + " " + result.getString("last_name"));
				
			}
			return alldoctors;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Returns a boolean depending on if doctor is availiable during a set day and time. Used for booking. 
	public boolean get_appointment_available(int day, String time, int doctor_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT %s FROM appointments WHERE appointment_day = %d  AND doctor_id = %d", time, day, doctor_id);
			PreparedStatement ifavailble = con.prepareStatement(query);
			ResultSet result = ifavailble.executeQuery();
			
			
			System.out.println(query);
			while(result.next()) {
				if(result.getBoolean(time) == true) {
					return true;
				}
			}
			
			return false;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	
	}
	
	// Returns a boolean depending if the lab is open during a set day and time. 
	public boolean get_lab_availability(int day, String time) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT %s FROM lab_appointments WHERE lab_day = %d", time, day);
			PreparedStatement ifavailble = con.prepareStatement(query);
			ResultSet result = ifavailble.executeQuery();
			
			
			System.out.println(query);
			while(result.next()) {
				if(result.getBoolean(time) == true) {
					return true;
				}
			}
			
			return false;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	
	}
	
	/**
	 * Function for doctor to add an appointment to their schedule 
	 * 
	 * @param user_id user id of patient 
	 * @param doctor_id doctor id
	 * @param appointment_day numerical value (day of the month)
	 * @param appointment_time_1 true or false, same for times 2,3,4,5
	 * 
	 */
	public void add_appointment(int user_id, int doctor_id, int appointment_day, boolean appointment_time_1, boolean appointment_time_2, boolean appointment_time_3, boolean appointment_time_4, boolean appointment_time_5) {
		try {
			Connection con = getConnection();
			String query = String.format("INSERT INTO appointments (user_id , doctor_id, appointment_day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5) VALUES (%d, %d, %d, %b, %b, %b, %b, %b)" , user_id, doctor_id, appointment_day, appointment_time_1,  appointment_time_2, appointment_time_3, appointment_time_4,appointment_time_5);
			PreparedStatement insertinfo = con.prepareStatement(query);
			insertinfo.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * check if an appointment already exists in the database
	 * 
	 */
	public boolean check_appointment(int user_id, int doctor_id, int appointment_day, boolean appointment_time_1, boolean appointment_time_2, boolean appointment_time_3, boolean appointment_time_4, boolean appointment_time_5) {
		try {
			Connection con = getConnection();
			String query = String.format("select case when exists (select * from appointments where user_id = %d and doctor_id = %d and "
					+ " appointment_day=%d and appointment_time_1=%b and appointment_time_2=%b and appointment_time_3=%b and "
					+ " appointment_time_4=%b and appointment_time_5=%b) then 'true' else 'false' end", user_id, doctor_id, appointment_day, appointment_time_1,
					appointment_time_2, appointment_time_3, appointment_time_4,appointment_time_5);
			PreparedStatement checkAppt= con.prepareStatement(query);
			ResultSet result = checkAppt.executeQuery();
			System.out.println(result.next());
			while(result.next()) {
				System.out.println(result.next());
				if(result.next() == true) {
					return true;
				}
				
				return false;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// Deletes an appointment if it exists. 
	public void delete_appointment(int user_id, int doctor_id, int appointment_day, boolean appointment_time_1,
								boolean appointment_time_2, boolean appointment_time_3, 
								boolean appointment_time_4, boolean appointment_time_5) {
		try {
			Connection con = getConnection();
			String query = String.format("delete from appointments where user_id = %d and doctor_id = %d and "
					+ " appointment_day=%d and appointment_time_1=%b and appointment_time_2=%b and appointment_time_3=%b and "
					+ " appointment_time_4=%b and appointment_time_5=%b", user_id, doctor_id, appointment_day, appointment_time_1,
					appointment_time_2, appointment_time_3, appointment_time_4,appointment_time_5);
			PreparedStatement deleteAppt= con.prepareStatement(query);
			System.out.println(query);
			deleteAppt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// Adds a lab appointment to a users schedule. 
	public void add_lab_appointment(int user_id, int appointment_day, boolean appointment_time_1, boolean appointment_time_2, boolean appointment_time_3, boolean appointment_time_4, boolean appointment_time_5) {
		try {
			Connection con = getConnection();
			String query = String.format("INSERT INTO lab_appointments (user_id , lab_day, lab_time1, lab_time2, lab_time3,lab_time4, lab_time5) VALUES (%d, %d, %b, %b, %b, %b, %b)" , user_id, appointment_day, appointment_time_1,  appointment_time_2, appointment_time_3, appointment_time_4,appointment_time_5);
			PreparedStatement insertinfo = con.prepareStatement(query);
			insertinfo.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// Adss a referral to users info. 
	public void addReferral(int referralNo, int patientId, String reason, String type) {
		try {
			Connection con = getConnection();
			String query = String.format("insert into referral (referralCode, patientID, reason, type) values (%d, %d, \"%s\", \"%s\")", referralNo, patientId, reason, type); 
			System.out.println(query);
			PreparedStatement insertinfo = con.prepareStatement(query);
			insertinfo.executeUpdate();
			

		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// Check if a given referal code aligns with the patients info. 
	public boolean checkReferralCode(int referralNo) {
        try { 
            Connection con = getConnection();
            String query = String.format("select count(*) from referral where referralCode=%d", referralNo);
            System.out.println(query);
            PreparedStatement checkCode = con.prepareStatement(query);
            ResultSet result = checkCode.executeQuery();
            int n = 0;
            if ( result.next() ) {
            	n = result.getInt(1);
            }
            
            //	if referral code exists in the database
            if (n > 0) { return true; }
                       	
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        //	if referral code does not exist in the database
        return false;
    }

	/**
	 * Cross reference patient ID and referral number
	 * to check if a given referral number corresponds to a patient
	 * @param referralNo
	 * @param patientId
	 * @return
	 */
	public boolean checkIdForReferral(int referralNo, int patientId) {
        try { 
            Connection con = getConnection();
            String query = String.format("select count(*) from referral where referralCode=%d and patientID=%d", referralNo, patientId);
            System.out.println(query);
            PreparedStatement checkCode = con.prepareStatement(query);
            ResultSet result = checkCode.executeQuery();
            int n = 0;
            if ( result.next() ) {
            	n = result.getInt(1);
            }
            
            //	if referral code exists for this user
            if (n > 0) { return true; }
                       	
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        //	if referral code does not exist for this user
        return false;
	}
	// Returns the appointment IDs in an arraylist for a given user. 
	public ArrayList<Integer> appointment_id(int user_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT appointmentid FROM appointments WHERE user_id = %d", user_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			ArrayList<Integer> appointment_ids = new ArrayList<Integer>();
			while(result.next()) {
				appointment_ids.add(result.getInt("appointmentid"));
			}
			return appointment_ids;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	// Returns the appointment IDs in an arraylist for a given doctor.
	public ArrayList<Integer> appointment_id_doctor(int user_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT appointmentid FROM appointments WHERE doctor_id = %d", user_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			ArrayList<Integer> appointment_ids = new ArrayList<Integer>();
			while(result.next()) {
				appointment_ids.add(result.getInt("appointmentid"));
			}
			return appointment_ids;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// Returns the doctor that is specified for an appointment. 
	public int get_doctor_id(int appointment_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT doctor_id FROM appointments WHERE appointmentid = %d", appointment_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			result.next();
			int doc_id = result.getInt("doctor_id");
			
			return doc_id;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// Returns the patient that is specified for an appointment. 
	public int get_patient_id(int appointment_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT user_id FROM appointments WHERE appointmentid = %d", appointment_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			result.next();
			int user_id = result.getInt("user_id");
			
			return user_id;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// Returns the day that the appointment is being held on. 
	public int get_appointment_day(int appointment_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT appointment_day FROM appointments WHERE appointmentid = %d", appointment_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			result.next();
			int appointment_day = result.getInt("appointment_day");
			
			return appointment_day;
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// Returns the appointment time for an appointment. 
	public String get_appointment_time(int appointment_id) {
		try {
			Connection con = getConnection();
			String query = String.format("SELECT appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5 FROM appointments WHERE appointmentid = %d", appointment_id);
			PreparedStatement getappointmentid = con.prepareStatement(query);
			ResultSet result = getappointmentid.executeQuery();
			result.next();
			String the_right_time="";
			for(int i =1; i <6; i++) {
				String the_appointment = "appointment_time_" + i;
				boolean appointment_time = result.getBoolean(the_appointment);
				if(appointment_time == true) {
					the_right_time = the_appointment;
				}
			}
			if(the_right_time.contentEquals("appointment_time_1")) {
				return "9:00 AM";
			}
			else if(the_right_time.contentEquals("appointment_time_2")) {
				return "10:00 AM";
			}
			else if(the_right_time.contentEquals("appointment_time_3")) {
				return "11:00 AM";
			}
			else if(the_right_time.contentEquals("appointment_time_4")) {
				return "12:00 PM";
			}
			else if(the_right_time.contentEquals("13:00 PM")) {
				return "13:00 PM";
			}
			else {
				return "none";
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	
}

