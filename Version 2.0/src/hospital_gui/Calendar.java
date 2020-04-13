/**
 * Class to create calendar GUI
 * 
 * @author Nick
 * @version 3.0
 * @since 3.0
 */

package hospital_gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Calendar {

	protected Shell shell;
	private int user_id;
	private String role;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Calendar window = new Calendar(1,"doctor");
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public Calendar(int user_id, String role) {
		this.user_id = user_id;
		this.role = role;
	}
	
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.forceActive();
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
		shell.setSize(798, 742);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(20, 10, 151, 32);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		lblNewLabel.setText(" March, 2020");
		
		Label lblMonday = new Label(shell, SWT.NONE);
		lblMonday.setBounds(30, 53, 76, 30);
		lblMonday.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblMonday.setText("Monday");
		
		Label lblTuesday = new Label(shell, SWT.NONE);
		lblTuesday.setBounds(180, 53, 76, 30);
		lblTuesday.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblTuesday.setText("Tuesday");
		
		Label lblWednesday = new Label(shell, SWT.NONE);
		lblWednesday.setBounds(330, 53, 107, 30);
		lblWednesday.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblWednesday.setText("Wednesday");
		
		Label lblThursday = new Label(shell, SWT.NONE);
		lblThursday.setBounds(511, 53, 84, 30);
		lblThursday.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblThursday.setText("Thursday");
		
		Label lblFriday = new Label(shell, SWT.NONE);
		lblFriday.setBounds(669, 53, 55, 30);
		lblFriday.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblFriday.setText("Friday");
		
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Booking march2 = new Booking(user_id,role);
				march2.setDay(2);
				march2.open();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton.setBounds(28, 108, 76, 51);
		btnNewButton.setText("2");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Booking march2 = new Booking(user_id,role);
				march2.setDay(3);
				march2.open();
			}
		});
		btnNewButton_1.setText("3");
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_1.setBounds(180, 108, 76, 51);
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Booking march2 = new Booking(user_id,role);
				march2.setDay(4);
				march2.open();
			}
		});
		btnNewButton_2.setText("4");
		btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_2.setBounds(346, 108, 76, 51);
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Booking march2 = new Booking(user_id,role);
				march2.setDay(5);
				march2.open();
			}
		});
		btnNewButton_3.setText("5");
		btnNewButton_3.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_3.setBounds(511, 108, 76, 51);
		
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Booking march2 = new Booking(user_id,role);
				march2.setDay(6);
				march2.open();
			}
		});
		btnNewButton_4.setText("6");
		btnNewButton_4.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_4.setBounds(669, 108, 76, 51);
		
		Button btnNewButton_5 = new Button(shell, SWT.NONE);
		btnNewButton_5.setText("9");
		btnNewButton_5.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_5.setBounds(30, 220, 76, 51);
		
		Button btnNewButton_6 = new Button(shell, SWT.NONE);
		btnNewButton_6.setText("10");
		btnNewButton_6.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_6.setBounds(180, 220, 76, 51);
		
		Button btnNewButton_7 = new Button(shell, SWT.NONE);
		btnNewButton_7.setText("11");
		btnNewButton_7.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_7.setBounds(346, 220, 76, 51);
		
		Button btnNewButton_8 = new Button(shell, SWT.NONE);
		btnNewButton_8.setText("12");
		btnNewButton_8.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_8.setBounds(511, 220, 76, 51);
		
		Button btnNewButton_9 = new Button(shell, SWT.NONE);
		btnNewButton_9.setText("13");
		btnNewButton_9.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_9.setBounds(669, 220, 76, 51);
		
		Button btnNewButton_10 = new Button(shell, SWT.NONE);
		btnNewButton_10.setText("16");
		btnNewButton_10.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_10.setBounds(30, 343, 76, 51);
		
		Button btnNewButton_11 = new Button(shell, SWT.NONE);
		btnNewButton_11.setText("17");
		btnNewButton_11.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_11.setBounds(180, 343, 76, 51);
		
		Button btnNewButton_12 = new Button(shell, SWT.NONE);
		btnNewButton_12.setText("18");
		btnNewButton_12.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_12.setBounds(346, 343, 76, 51);
		
		Button btnNewButton_13 = new Button(shell, SWT.NONE);
		btnNewButton_13.setText("19");
		btnNewButton_13.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_13.setBounds(511, 343, 76, 51);
		
		Button btnNewButton_14 = new Button(shell, SWT.NONE);
		btnNewButton_14.setText("20");
		btnNewButton_14.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_14.setBounds(669, 343, 76, 51);
		
		Button btnNewButton_15 = new Button(shell, SWT.NONE);
		btnNewButton_15.setText("23");
		btnNewButton_15.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_15.setBounds(30, 475, 76, 51);
		
		Button btnNewButton_16 = new Button(shell, SWT.NONE);
		btnNewButton_16.setText("24");
		btnNewButton_16.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_16.setBounds(180, 475, 76, 51);
		
		Button btnNewButton_17 = new Button(shell, SWT.NONE);
		btnNewButton_17.setText("25");
		btnNewButton_17.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_17.setBounds(346, 475, 76, 51);
		
		Button btnNewButton_18 = new Button(shell, SWT.NONE);
		btnNewButton_18.setText("26");
		btnNewButton_18.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_18.setBounds(511, 475, 76, 51);
		
		Button btnNewButton_19 = new Button(shell, SWT.NONE);
		btnNewButton_19.setText("27");
		btnNewButton_19.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_19.setBounds(669, 475, 76, 51);
		
		Button btnNewButton_20 = new Button(shell, SWT.NONE);
		btnNewButton_20.setText("30");
		btnNewButton_20.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_20.setBounds(30, 594, 76, 51);
		
		Button btnNewButton_21 = new Button(shell, SWT.NONE);
		btnNewButton_21.setText("31");
		btnNewButton_21.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnNewButton_21.setBounds(180, 594, 76, 51);
		
		Button btnBackToMain = new Button(shell, SWT.NONE);
		btnBackToMain.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnBackToMain.setBounds(697, 668, 75, 25);
		btnBackToMain.setText("Back to Main");

	}
}
