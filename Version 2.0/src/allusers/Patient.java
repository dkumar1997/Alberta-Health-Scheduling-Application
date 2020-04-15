/**
 * Basic functionalities for users of account type patient
 * Currently does nothing.
 * 
 * @author Dheeraj, Abel
 * @version 3.0
 * @since 2.0
 */

package allusers;

import java.util.ArrayList;

public class Patient {
	
	 		/** getter method that will return a doctors schedule allowing nurses to view times the doctor is working, in appointments
			 * or whenever they are free.
			 */
			public ArrayList<ArrayList<Patient>> getdocschedule(Doctor doctor){
				return doctor.schedule.getSchedule();
			}
	
}
