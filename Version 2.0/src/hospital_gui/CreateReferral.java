/**
 * Class for doctor to create a referral for a patient
 * 
 * @author Stefan
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

public class CreateReferral {

	private JFrame frame;
	private String fName;
	private String lName;
	private JTextField reason_txt;
	private JTextField speciality_txt;
	private SQLQUERIES commands = new SQLQUERIES();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public void launch(String firstName, String lastName) {
		this.fName=firstName;
		this.lName=lastName;
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
		
		JLabel reason_lbl = new JLabel("Reason");
		reason_lbl.setBounds(31, 54, 87, 87);
		frame.getContentPane().add(reason_lbl);
		
		reason_txt = new JTextField();
		reason_txt.setBounds(130, 57, 184, 50);
		frame.getContentPane().add(reason_txt);
		reason_txt.setColumns(250);
		
		JLabel speciality_lbl = new JLabel("Speciality: ");
		speciality_lbl.setBounds(31, 180, 84, 16);
		frame.getContentPane().add(speciality_lbl);
		
		JComboBox speciality_txt = new JComboBox();
		speciality_txt.setModel(new DefaultComboBoxModel(new String[] {"Cardiologist", "Neurologist", "Nephrologist", "Lab"}));
		speciality_txt.setBounds(130, 180, 184, 31);
		frame.getContentPane().add(speciality_txt);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reason = reason_txt.getText();
				String speciality = speciality_txt.getSelectedItem().toString();
				if(reason.contentEquals("")) {
					JOptionPane.showMessageDialog(null,"Please specify a reason for referral");
					frame.dispose();
					return;
				}
				else {
					int i = new Random().nextInt(900000) + 100000; //make a random 6 digit referral code
					//check that the referral code does not exist in the referral table already
					//	if it does, make a new number
					while(commands.checkReferralCode(i)) {
						i = new Random().nextInt(900000) + 100000;
					}
					int patientId = commands.getPatientid(fName, lName);
					//add the referral
					commands.addReferral(i, patientId, reason, speciality);
					JOptionPane.showMessageDialog(null,"Successfully created a referral for patient " 
					+ fName + " " + lName + " with referral code " + i);
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
