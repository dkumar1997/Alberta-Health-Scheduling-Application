package hospital_gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Lab_Booking {

	private int day;
	private int user_id;
	
	
	protected Shell shell;
	SQLQUERIES commands = new SQLQUERIES();
	ArrayList<String> doctors_speciality = new ArrayList<String>();
	private ArrayList<String> times = new ArrayList<String>();

	
	List appointment_list;
	
	
	public Lab_Booking(int user_id) {
		this.user_id = user_id;
		
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Lab_Booking window = new Lab_Booking(3);

		

			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(517, 402);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblNewLabel.setBounds(10, 10, 104, 40);
		String s = "March ";
		String t = Integer.toString(getDay());				
		s = s.concat(t);
		lblNewLabel.setText(s);
		
		
		appointment_list = new List(shell, SWT.BORDER);
		appointment_list.setBounds(190, 94, 104, 111);
		
			Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(151, 230, 192, 16);
		btnCheckButton.setText("Confirm you have a referal");
		
		Label lblAvailableTimes = new Label(shell, SWT.NONE);
		lblAvailableTimes.setBounds(200, 73, 94, 15);
		lblAvailableTimes.setText("Available Times");
		
		
		
	//	int doctor_id = commands.getdoctorid(first_name, last_name);
		
		for(int i = 1; i < 6; i++) {
			
			if(!(commands.get_lab_availability(day, ("lab_time" + i)))) {
				
				if(i+8 < 12) {
					times.add((i+8) + ":00 AM");
				}
				else {
					times.add((i+8) + ":00 PM");
				}
			}
		}
		
		String[] times_list = new String[times.size()];
		for(int i = 0; i < times.size(); i++) {
			times_list[i]= times.get(i);
		}
		times.clear();
		appointment_list.setItems(times_list);
		
	
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		
				if (!btnCheckButton.getSelection()) {
					System.out.println("not checked");
				}
				String lab_time = appointment_list.getSelection()[0];
				
				boolean lab_time_1 = false;
				boolean lab_time_2 = false;
				boolean lab_time_3 = false;
				boolean lab_time_4 = false;
				boolean lab_time_5 = false;
				
				if(lab_time.contentEquals("9:00 AM")) {
					lab_time_1 = true;
				}
				else if(lab_time.contentEquals("10:00 AM")) {
					lab_time_2 = true;
				}
				else if(lab_time.contentEquals("11:00 AM")) {
					lab_time_3 = true;
				}
				else if(lab_time.contentEquals("12:00 PM")) {
					lab_time_4 = true;
				}
				else if(lab_time.contentEquals("13:00 PM")) {
					lab_time_5 = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Please choose a valid time and doctor");
				}
				/*
				System.out.println(appointment_time);
				System.out.println(doctor_id);
				System.out.println("1:" + appointment_time_1);
				System.out.println("2:" +appointment_time_2);
				System.out.println("3:" +appointment_time_3);
				System.out.println("4:" +appointment_time_4);
				System.out.println("5:" +appointment_time_5);
				*/
				commands.add_lab_appointment(user_id, day, lab_time_1, lab_time_2, lab_time_3, lab_time_4,lab_time_5);
	
			}
			
	
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton.setBounds(134, 267, 225, 60);
		btnNewButton.setText("Book Lab Appointment");
		
	
		
		Label lblLabBooking = new Label(shell, SWT.NONE);
		lblLabBooking.setText("Lab Booking");
		lblLabBooking.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblLabBooking.setBounds(176, 10, 132, 40);
	}
	
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
}
