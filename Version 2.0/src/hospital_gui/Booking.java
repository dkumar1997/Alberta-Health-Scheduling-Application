package hospital_gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.layout.GridData;

public class Booking {

	private int day;
	private int user_id;
	private String role;
	private ArrayList<String> times = new ArrayList<String>();
	private JTextField referralNo_txt; 
	
	protected Shell shell;
	SQLQUERIES commands = new SQLQUERIES();
	ArrayList<String> doctors_speciality = new ArrayList<String>();
	
	
	List doctor_list;
	List appointment_list;
	
	public Booking(int user_id, String role) {
		this.user_id = user_id;
		this.role = role;
	}
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Booking window = new Booking(3,"patient");
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
		shell.setSize(516, 402);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblNewLabel.setBounds(10, 10, 104, 40);
		String s = "March ";
		String t = Integer.toString(getDay());				
		s = s.concat(t);
		lblNewLabel.setText(s);
		
		Label lblDoctor = new Label(shell, SWT.NONE);
		lblDoctor.setBounds(224, 70, 55, 15);
		lblDoctor.setText("Doctor");
		
		Label lblAvailableTimes = new Label(shell, SWT.NONE);
		lblAvailableTimes.setBounds(354, 70, 94, 15);
		lblAvailableTimes.setText("Available Times");
		
		Label lblDepartment = new Label(shell, SWT.NONE);
		lblDepartment.setBounds(50, 70, 73, 15);
		lblDepartment.setText("Department");
		
		appointment_list = new List(shell, SWT.BORDER);
		appointment_list.setBounds(344, 91, 104, 111);
		
		doctor_list = new List(shell, SWT.BORDER);
		doctor_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String[] doctor_name = doctor_list.getSelection();
				String[] doctor_split = doctor_name[0].split(" ");
				String first_name = doctor_split[0];
				String last_name = doctor_split[1];
				int doctor_id = commands.getdoctorid(first_name, last_name);
				
				for(int i = 1; i < 6; i++) {
					
					if(!(commands.get_appointment_available(day, ("appointment_time_" + i), doctor_id))) {
						
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
				
				
				
			}
		});
		doctor_list.setBounds(190, 91, 104, 111);
		
		
		
		List list = new List(shell, SWT.BORDER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String[] selected = list.getSelection();
				String speciality = selected[0];
				System.out.println(speciality);
				doctors_speciality = commands.getspecificdoctor("doctor", speciality);
				String[] array_conversion = new String[doctors_speciality.size()];
				for(int i =0; i < doctors_speciality.size(); i++) {
					array_conversion[i] = doctors_speciality.get(i);
				}
				doctor_list.setItems(array_conversion);
				
			}
		});
		
		list.setItems(new String[] {"General", "Emergency"});		
		list.setBounds(38, 91, 110, 111);
		
		
		
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String appointment_time = appointment_list.getSelection()[0];
				String[] doctor = doctor_list.getSelection()[0].split(" ");
				int doctor_id = commands.getdoctorid(doctor[0],doctor[1]);
				boolean appointment_time_1 = false;
				boolean appointment_time_2 = false;
				boolean appointment_time_3 = false;
				boolean appointment_time_4 = false;
				boolean appointment_time_5 = false;
				
				if(appointment_time.contentEquals("9:00 AM")) {
					appointment_time_1 = true;
				}
				else if(appointment_time.contentEquals("10:00 AM")) {
					appointment_time_2 = true;
				}
				else if(appointment_time.contentEquals("11:00 AM")) {
					appointment_time_3 = true;
				}
				else if(appointment_time.contentEquals("12:00 PM")) {
					appointment_time_4 = true;
				}
				else if(appointment_time.contentEquals("13:00 PM")) {
					appointment_time_5 = true;
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
				commands.add_appointment(user_id, doctor_id, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5);
				
				shell.close();
				
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton.setBounds(150, 267, 193, 60);
		btnNewButton.setText("Book Appointment");
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(90, 231, 157, 16);
		btnCheckButton.setText("I have a specialist referal.");
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Label ref_lbl = new Label(shell, SWT.SINGLE | SWT.BORDER);
				ref_lbl.setBounds(350,231,100,25);
				ref_lbl.setText("Enter referral num");
				
				Text enterReferral = new Text (shell, SWT.SINGLE | SWT.BORDER);
				enterReferral.setBounds(350,250,75,50);
			    
				Button btnEnterButton = new Button(shell, SWT.NONE);
                btnEnterButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
                btnEnterButton.setBounds(350, 300, 100, 25);
                btnEnterButton.setText("ENTER");
                btnEnterButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        Integer referralNum = Integer.parseInt(enterReferral.getText());
                        System.out.print(referralNum.toString());
                        // does the provided referral number exist in the database?
                        if(commands.checkReferralCode(referralNum)){
                        	// if it exists, does it correspond to current patient id?
                        	if(commands.checkIdForReferral(referralNum, user_id)) {
                        		//show specialists
                        		list.setItems(new String[] {"Cardiologist", "Neurologist", "Nephrologist", "General", "Emergency"});
                        	}
                        }
                        else {System.out.println("Referral number is incorrect.");}
                        
                    }
                });
			    
			}
		});
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
}
