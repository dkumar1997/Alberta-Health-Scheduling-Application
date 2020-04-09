package hospital_gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Booking {

	private int day;
	
	protected Shell shell;
	SQLQUERIES commands = new SQLQUERIES();
	ArrayList<String> doctors_speciality = new ArrayList<String>();
	
	List doctor_list;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Booking window = new Booking();
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
		
		doctor_list = new List(shell, SWT.BORDER);
		doctor_list.setBounds(190, 91, 104, 111);
		
		
		
		List list = new List(shell, SWT.BORDER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String[] selected = list.getSelection();
				String speciality = selected[0];
				System.out.println(speciality);
				doctors_speciality = commands.getspecificdoctor("doctor", speciality);
				for(String i: doctors_speciality){
					System.out.println(i);
				}
				String[] array_conversion = new String[doctors_speciality.size()];
				for(int i =0; i < doctors_speciality.size(); i++) {
					array_conversion[i] = doctors_speciality.get(i);
				}
				for(String i: array_conversion) {
					System.out.println(i + " this is updated");
				}
				
				
				doctor_list.setItems(array_conversion);
				
			}
		});
		
		list.setItems(new String[] {"Cardiologist", "Neurologist", "Nephrologist", "General", "Emergency"});
		list.setBounds(38, 91, 110, 111);
		
		
		Label lblDepartment = new Label(shell, SWT.NONE);
		lblDepartment.setBounds(50, 70, 73, 15);
		lblDepartment.setText("Department");
		
		
		List list_1_1 = new List(shell, SWT.BORDER);
		list_1_1.setBounds(344, 91, 104, 111);
		
		
		
		Label lblAvailableTimes = new Label(shell, SWT.NONE);
		lblAvailableTimes.setBounds(354, 70, 94, 15);
		lblAvailableTimes.setText("Available Times");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton.setBounds(150, 267, 193, 60);
		btnNewButton.setText("Book Appointment");
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(190, 231, 157, 16);
		btnCheckButton.setText("Specialist require referral");
	}
	
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
}
