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

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Calendar {
	// Private Instance variables.
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
		

		
		int weekend[]= {1,7,8,14,15,21,22,28,29};
		
		int s = 0;	
		int x = 0;
		int y = 120;
		for (int i = 1; i <= 31; i++) {			
			int day = i;
			
			if(0<=Arrays.binarySearch(weekend, day)){//if weekend don't add button		
				
			}else {
			
				Button btnNewButton_3 = new Button(shell, SWT.NONE);
				btnNewButton_3.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Booking march = new Booking(user_id,role);
						march.setDay(day);
						march.open();
	
						
					}
				});
			
				btnNewButton_3.setText(Integer.toString(day));
				btnNewButton_3.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
				x = 28 + (152 * s);
				btnNewButton_3.setBounds(x, y, 76, 51);			
				s++;	
			}
			if(day==7 || day==14 || day== 21 || day ==28) {//new week/row
				y=y+120;
				s=0;
			}
			
		}
		
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
