/**
 * Class to handle booking lab appointments
 * 
 * @author Nick
 * @version 3.0
 * @since 3.0
 */
package hospital_gui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class Lab_Booking {

	private int day;
	
	protected Shell shell;
	SQLQUERIES commands = new SQLQUERIES();
	ArrayList<String> doctors_speciality = new ArrayList<String>();


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Lab_Booking window = new Lab_Booking();
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
		
		
		List list_1_1 = new List(shell, SWT.BORDER);
		list_1_1.setBounds(190, 94, 104, 111);
		
		
		
		Label lblAvailableTimes = new Label(shell, SWT.NONE);
		lblAvailableTimes.setBounds(200, 73, 94, 15);
		lblAvailableTimes.setText("Available Times");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton.setBounds(134, 267, 225, 60);
		btnNewButton.setText("Book Lab Appointment");
		
		/**
		 * Currently patients will just confirm if they have a referral
		 */
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(151, 230, 192, 16);
		btnCheckButton.setText("Confirm you have a referal");
		
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
