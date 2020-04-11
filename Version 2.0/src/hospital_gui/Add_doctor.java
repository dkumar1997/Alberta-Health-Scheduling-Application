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

public class Add_doctor {

	private JFrame frame;
	private JTextField first_txt;
	private JTextField username_txt;
	private JTextField password_text;
	public String[] requirements = new String[4];
	private JTextField last_txt;
	private JTextField email_txt;
	private JTextField address_txt;
	private JTextField phone_txt;
	private JTextField dob_txt;
	private SQLQUERIES commands = new SQLQUERIES();
	private String role;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public void launch(String role) {
		this.role = role;
		initialize();
		frame.setVisible(true);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 350, 550);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel first_lbl = new JLabel("First Name:");
		first_lbl.setBounds(31, 54, 87, 36);
		frame.getContentPane().add(first_lbl);
		
		first_txt = new JTextField();
		first_txt.setBounds(130, 57, 184, 31);
		frame.getContentPane().add(first_txt);
		first_txt.setColumns(10);
		
		
		JLabel username_lbl = new JLabel("Username: ");
		username_lbl.setBounds(31, 362, 84, 16);
		frame.getContentPane().add(username_lbl);
		
		username_txt = new JTextField();
		username_txt.setColumns(10);
		username_txt.setBounds(130, 355, 184, 31);
		frame.getContentPane().add(username_txt);
		
		
		JLabel password_lbl = new JLabel("Password: ");
		password_lbl.setBounds(31, 406, 67, 16);
		frame.getContentPane().add(password_lbl);
		
		password_text = new JTextField();
		password_text.setColumns(10);
		password_text.setBounds(130, 399, 184, 31);
		frame.getContentPane().add(password_text);
		
		
		JLabel speciality_lbl = new JLabel("Speciality: ");
		speciality_lbl.setBounds(31, 449, 84, 16);
		frame.getContentPane().add(speciality_lbl);
		
		JComboBox speciality_txt = new JComboBox();
		speciality_txt.setModel(new DefaultComboBoxModel(new String[] {"Cardiologist", "Neurologist", "Nephrologist"}));
		speciality_txt.setBounds(130, 443, 184, 31);
		frame.getContentPane().add(speciality_txt);
		
		
		JLabel nurse_lbl = new JLabel("For patients please choose what kind of doctor to see ");
		nurse_lbl.setBounds(0, 477, 350, 31);
		frame.getContentPane().add(nurse_lbl);
		
		
		JLabel sign_up_lbl = new JLabel("Sign Up");
		sign_up_lbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		sign_up_lbl.setBounds(130, 6, 117, 36);
		frame.getContentPane().add(sign_up_lbl);
		
		JLabel last_lbl = new JLabel("Last Name: ");
		last_lbl.setBounds(31, 114, 84, 16);
		frame.getContentPane().add(last_lbl);
		
		last_txt = new JTextField();
		last_txt.setBounds(130, 109, 184, 31);
		frame.getContentPane().add(last_txt);
		last_txt.setColumns(10);
		
		JLabel email_lbl = new JLabel("Email:");
		email_lbl.setBounds(31, 166, 61, 16);
		frame.getContentPane().add(email_lbl);
		
		email_txt = new JTextField();
		email_txt.setBounds(130, 159, 184, 31);
		frame.getContentPane().add(email_txt);
		email_txt.setColumns(10);
		
		JLabel address_lbl = new JLabel("Address:");
		address_lbl.setBounds(31, 212, 61, 16);
		frame.getContentPane().add(address_lbl);
		
		address_txt = new JTextField();
		address_txt.setBounds(130, 205, 184, 31);
		frame.getContentPane().add(address_txt);
		address_txt.setColumns(10);
		
		JLabel phn_lbl = new JLabel("Phone #:");
		phn_lbl.setBounds(31, 262, 61, 16);
		frame.getContentPane().add(phn_lbl);
		
		phone_txt = new JTextField();
		phone_txt.setBounds(130, 255, 184, 31);
		frame.getContentPane().add(phone_txt);
		phone_txt.setColumns(10);
		
		JLabel dob_lbl = new JLabel("DOB:");
		dob_lbl.setBounds(31, 309, 61, 16);
		frame.getContentPane().add(dob_lbl);
		
		dob_txt = new JTextField();
		dob_txt.setBounds(130, 304, 184, 31);
		frame.getContentPane().add(dob_txt);
		dob_txt.setColumns(10);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = first_txt.getText();
				String lastname = last_txt.getText();
				String username = username_txt.getText();
				String password = password_text.getText();
				String email = email_txt.getText();
				String address = address_txt.getText();
				String phone = phone_txt.getText();
				String dob = dob_txt.getText();
				String speciality = speciality_txt.getSelectedItem().toString();
				if(firstname.contentEquals("") || lastname.contentEquals("") || username.contentEquals("") || password.contentEquals("") || email.contentEquals("") || address.contentEquals("") || phone.contentEquals("") || dob.contentEquals("") ) {
					JOptionPane.showMessageDialog(null,"You did not fill out all the information");
					frame.dispose();
					return;
				}
				
				if(commands.checkuser(username)) {
					commands.setinfo(firstname, lastname, email, address, phone, dob, speciality,role);
					commands.enterinfo(username, password,role);
					JOptionPane.showMessageDialog(null,"You have successfully signed up");
					
				}
				else {
					JOptionPane.showMessageDialog(null,"This account already exists or invalid input");
				}
				
				frame.dispose();
				
			}
		});
		submit_btn.setBounds(119, 515, 117, 29);
		frame.getContentPane().add(submit_btn);
		
		
	}
	

}
