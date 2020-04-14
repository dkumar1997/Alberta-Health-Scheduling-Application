/**
 * GUI class that displays a form to let doctors add or remove current appointments
 * 	from their schedule.
 * 
 * To modify an existing shift, delete the desired shift and add a new one.
 * 
 * @author stefan
 * @version 3.0
 * @since 3.0
 */

package hospital_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ModifyDoctorSchedule {
	// Instance Variables
	private JFrame frame;
	private int doctorId;
	private JTextField modify_txt;
	private JTextField patientid_txt;
	private JTextField day_txt;
	private JTextField time_txt;
	private SQLQUERIES commands = new SQLQUERIES();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public void launch(int doctorId) {
		this.doctorId=doctorId;
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.green);
		frame.setBounds(100, 100, 350, 350);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel modify_lbl = new JLabel("Shift change: ");
		modify_lbl.setBounds(31, 31, 84, 16);
		frame.getContentPane().add(modify_lbl);
		
		JLabel patientid_lbl = new JLabel("Patient ID");
		patientid_lbl.setBounds(31, 75, 87, 16);
		frame.getContentPane().add(patientid_lbl);
		
		patientid_txt = new JTextField();
		patientid_txt.setBounds(130, 75, 87, 16);
		frame.getContentPane().add(patientid_txt);
		patientid_txt.setColumns(250);
		
		JLabel day_lbl = new JLabel("Day");
		day_lbl.setBounds(31, 125, 87, 16);
		frame.getContentPane().add(day_lbl);
		
		day_txt = new JTextField();
		day_txt.setBounds(130, 125, 87, 16);
		frame.getContentPane().add(day_txt);
		day_txt.setColumns(250);
		
		JLabel time_lbl = new JLabel("Time");
		time_lbl.setBounds(31, 175, 87, 16);
		frame.getContentPane().add(time_lbl);
		
		JComboBox time_txt = new JComboBox();
		time_txt.setModel(new DefaultComboBoxModel(new String[] {"9", "10","11","12"}));
		time_txt.setBounds(130,175,84,16);
		frame.getContentPane().add(time_txt);
		
		
		
		JComboBox modify_txt = new JComboBox();
		modify_txt.setModel(new DefaultComboBoxModel(new String[] {"Add", "Delete"}));
		modify_txt.setBounds(130, 31, 84, 16);
		frame.getContentPane().add(modify_txt);
		
				
		// Submit button that will cross refernce all the required field and set up the schedule. 
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = modify_txt.getSelectedItem().toString();
				int patientId = Integer.parseInt(patientid_txt.getText());
				int appointment_time = Integer.parseInt(time_txt.getSelectedItem().toString());
				int day = Integer.parseInt(day_txt.getText());
				
				boolean appointment_time_1 = false;
				boolean appointment_time_2 = false;
				boolean appointment_time_3 = false;
				boolean appointment_time_4 = false;
				boolean appointment_time_5 = false;
				
				if(appointment_time==9) {
					appointment_time_1 = true;
				}
				else if(appointment_time==10) {
					appointment_time_2 = true;
				}
				else if(appointment_time==11) {
					appointment_time_3 = true;
				}
				else if(appointment_time==12) {
					 appointment_time_4 = true;
				}
				else if(appointment_time==1) {
					appointment_time_5 = true;
				}
				
						
				switch (choice) {
				case "Add":
					System.out.println("Add");
					System.out.println(commands.check_appointment(patientId, doctorId, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5));
					
					if (commands.check_appointment(patientId, doctorId, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5)) {
						System.out.println("Appointment already exists");
						break;
					}
					else {
						commands.add_appointment(patientId, doctorId, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5);
						break;
					}	
					
				case "Delete":
					System.out.println("Delete");
					if (commands.check_appointment(patientId, doctorId, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5)) {
						System.out.println("Appointment does not exist");
						break;
					} else {
						commands.delete_appointment(patientId, doctorId, day, appointment_time_1, appointment_time_2, appointment_time_3, appointment_time_4, appointment_time_5);
						break;
					}
				
				}
				frame.dispose();
				
			}
			});
		submit_btn.setBounds(119, 250, 117, 29);
		frame.getContentPane().add(submit_btn);
		
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this referral form?", "Confirmation", JOptionPane.YES_NO_OPTION)== 0){
					frame.dispose();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				exitLabel.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent e) {
				exitLabel.setForeground(Color.WHITE);
			}
			
		});
		exitLabel.setBounds(330, 10, 15, 29);
		frame.add(exitLabel);
				
	}
	
	
	
}
