package hospital_gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;




public class MainPage {
	
	private Image heart = new ImageIcon(MainPage.class.getResource("heart.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image image_24 = new ImageIcon(MainPage.class.getResource("24.jpg")).getImage().getScaledInstance(108, 86, Image.SCALE_SMOOTH);
	private Image brain = new ImageIcon(MainPage.class.getResource("brain.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image kidney = new ImageIcon(MainPage.class.getResource("kidney.jpeg")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Image calendar = new ImageIcon(MainPage.class.getResource("calendar.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image adminpic = new ImageIcon(MainPage.class.getResource("admin.png")).getImage().getScaledInstance(70,70 , Image.SCALE_SMOOTH);
	private Image labpic = new ImageIcon(MainPage.class.getResource("lab.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image stats = new ImageIcon(MainPage.class.getResource("stats.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image profile = new ImageIcon(MainPage.class.getResource("profile.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	private Image clients = new ImageIcon(MainPage.class.getResource("clients.png")).getImage().getScaledInstance(70, 70,Image.SCALE_SMOOTH);
	
	private String role;
	private int user_id;
	private JFrame mainPage;
	private SQLQUERIES commands = new SQLQUERIES();
	
	
	private JLayeredPane layeredPane = new JLayeredPane();
	private Add_doctor adding_personal = new Add_doctor();
	
			

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		MainPage mainpage = new MainPage();
		
		mainpage.launch("nurse", 1);
		
	}
	
	public void launch(String role, int user_id) {
		
		this.role = role;
		this.user_id = user_id;
		initialize();
		mainPage.setVisible(true);
		
	}
	public void switchscreen(JPanel screen) {
		layeredPane.removeAll();
		layeredPane.add(screen);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}


	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		mainPage = new JFrame();
		mainPage.getContentPane().setBackground(new Color(255, 255, 224));
		
		JPanel main_panel = new JPanel();
		main_panel.setBackground(new Color(255, 192, 203));
		main_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainPage.getContentPane().add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(null);
		
		JPanel specialties_panel = new JPanel();
		specialties_panel.setBounds(0, 0, 800, 86);
		specialties_panel.setBackground(new Color(105, 105, 105));
		main_panel.add(specialties_panel);
		specialties_panel.setLayout(null);
		
		JLabel orange_corner_square = new JLabel("");
		orange_corner_square.setBackground(new Color(250, 128, 114));
		orange_corner_square.setBounds(0, 0, 108, 86);
		orange_corner_square.setIcon(new ImageIcon(image_24));
		specialties_panel.add(orange_corner_square);
		
		
		
	
		
		// ----------------------- This is where we are working on the exit label -------------------
		JLabel exitLabel = new JLabel("X");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION)== 0){
					mainPage.dispose();
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
		exitLabel.setBounds(785, 0, 15, 29);
		specialties_panel.add(exitLabel);
		
		
		
		JLabel the_heart = new JLabel("");
		the_heart.setBounds(207, 6, 108, 80);
		specialties_panel.add(the_heart);
		the_heart.setIcon(new ImageIcon(heart));
		
		JLabel the_brain = new JLabel("");
		the_brain.setBounds(394, 0, 108, 74);
		specialties_panel.add(the_brain);
		the_brain.setIcon(new ImageIcon(brain));
		
		JLabel the_kidney = new JLabel("");
		the_kidney.setBounds(583, 7, 108, 67);
		specialties_panel.add(the_kidney);
		the_kidney.setIcon(new ImageIcon(kidney));
		
		
		
		
		
		// ----------------------- This is where we are working on the profile label -------------------
		JLabel profile_lbl = new JLabel("");
		profile_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel profile_panel = new JPanel();
				profile_panel.setBackground(Color.PINK);
				layeredPane.add(profile_panel, "name_1347577344803451");
				profile_panel.setLayout(null);
				
				JLabel name_sign = new JLabel("Name:");
				name_sign.setBounds(33, 35, 54, 28);
				profile_panel.add(name_sign);
				
				JLabel email_sign = new JLabel("Email:");
				email_sign.setBounds(33, 75, 61, 28);
				profile_panel.add(email_sign);
				
				JLabel address_sign = new JLabel("Address: ");
				address_sign.setBounds(33, 115, 61, 16);
				profile_panel.add(address_sign);
				
				JLabel phone_sign = new JLabel("Phone#:");
				phone_sign.setBounds(33, 143, 77, 28);
				profile_panel.add(phone_sign);
				
				JLabel dob_sign = new JLabel("DOB: ");
				dob_sign.setBounds(33, 183, 61, 22);
				profile_panel.add(dob_sign);
				
				JLabel special_sign = new JLabel("Speciality: ");
				special_sign.setBounds(33, 217, 77, 28);
				profile_panel.add(special_sign);
				
				JLabel appointments_sign = new JLabel("Appointments: ");
				appointments_sign.setBounds(33, 257, 109, 28);
				profile_panel.add(appointments_sign);
				
				
				String name = commands.getinfo(user_id, "first_name") + " " + commands.getinfo(user_id, "last_name");
				JLabel name_lbl = new JLabel(name);
				name_lbl.setBounds(99, 38, 164, 22);
				profile_panel.add(name_lbl);	
				
				String email = commands.getinfo(user_id, "email");
				JLabel email_lbl = new JLabel(email);
				email_lbl.setBounds(102, 78, 173, 22);
				profile_panel.add(email_lbl);
				
				String address = commands.getinfo(user_id, "address");
				JLabel address_lbl = new JLabel(address);
				address_lbl.setBounds(99, 109, 188, 28);
				profile_panel.add(address_lbl);
				
				String phone = commands.getinfo(user_id, "phone_number");
				JLabel phone_lbl = new JLabel(phone);
				phone_lbl.setBounds(99, 143, 188, 28);
				profile_panel.add(phone_lbl);
				
				String dob = commands.getinfo(user_id, "DOB");
				JLabel dob_lbl = new JLabel(dob);
				dob_lbl.setBounds(99, 180, 188, 28);
				profile_panel.add(dob_lbl);
				
				String speciality = commands.getinfo(user_id, "speciality");
				JLabel special_lbl = new JLabel(speciality);
				special_lbl.setBounds(111, 217, 164, 28);
				profile_panel.add(special_lbl);
				
				
				JLabel appointments_lbl = new JLabel("New label");
				appointments_lbl.setBounds(33, 297, 223, 194);
				profile_panel.add(appointments_lbl);
				
				JLabel patientinfo_sign = new JLabel("Patient Info: ");
				patientinfo_sign.setBounds(309, 41, 109, 28);
				profile_panel.add(patientinfo_sign);
				
				JLabel lblNewLabel = new JLabel("<html>"  + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasdjfhsdjkflhsadjklfhdsfjlhsdflkjshflajshfasdljkfhsdjlfhsdjfkhsdfljsdkfhalsdfhsadklfjhsdflshdfjlasdhfjalsdhfjkdhfajskdghdfajkghkjaerhfjklaehgkjahfjkadshgasjkgh" + "</html>");
				lblNewLabel.setBounds(309, 94, 364, 395);
				profile_panel.add(lblNewLabel);
				
				switchscreen(profile_panel);
				
			}
		});
		profile_lbl.setBounds(750, 41, 50, 45);
		specialties_panel.add(profile_lbl);
		profile_lbl.setIcon(new ImageIcon(profile));
		
		
		JPanel option_panel = new JPanel();
		option_panel.setBounds(0, 86, 108, 514);
		option_panel.setBackground(new Color(105, 105, 105));
		main_panel.add(option_panel);
		option_panel.setLayout(null);
		
		JLabel the_calendar = new JLabel("");
		the_calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Calendar window = new Calendar(user_id, role);
					window.open();	
				} 
				catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		the_calendar.setBounds(12, 42, 96, 82);
		option_panel.add(the_calendar);
		the_calendar.setIcon(new ImageIcon(calendar));
		
		JLabel lab_pic = new JLabel("");
		lab_pic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Lab_Calendar window = new Lab_Calendar();
					window.open();	
				} 
				catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		lab_pic.setBounds(21, 172, 87, 101);
		option_panel.add(lab_pic);
		lab_pic.setIcon(new ImageIcon(labpic));
		
		if(role.contentEquals("doctor")||role.contentEquals("nurse")) {
			
			
			JLabel client_info = new JLabel("");
			client_info.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int client_id = -1;
					try {
						String name_of_client = JOptionPane.showInputDialog("Which clients info would you like? Please provide first name followed by space then last name");
						String[] namesplit = name_of_client.split(" ");
						client_id = commands.getdoctorid(namesplit[0], namesplit[1]);
					}
					catch(Exception ext) {
						System.out.println(ext);
					}
					if(client_id > 0) {
						
						JPanel clientinfo_pnl = new JPanel();
						clientinfo_pnl.setBackground(Color.PINK);
						layeredPane.add(clientinfo_pnl, "name_1620213546077921");
						
						clientinfo_pnl.setLayout(null);
		
						JLabel name_sign = new JLabel("Name:");
						name_sign.setBounds(33, 35, 54, 28);
						clientinfo_pnl.add(name_sign);
		
						JLabel email_sign = new JLabel("Email:");
						email_sign.setBounds(33, 75, 61, 28);
						clientinfo_pnl.add(email_sign);
		
						JLabel address_sign = new JLabel("Address: ");
						address_sign.setBounds(33, 115, 61, 16);
						clientinfo_pnl.add(address_sign);
		
						JLabel phone_sign = new JLabel("Phone#:");
						phone_sign.setBounds(33, 143, 77, 28);
						clientinfo_pnl.add(phone_sign);
		
						JLabel dob_sign = new JLabel("DOB: ");
						dob_sign.setBounds(33, 183, 61, 22);
						clientinfo_pnl.add(dob_sign);
		
						JLabel special_sign = new JLabel("Speciality: ");
						special_sign.setBounds(33, 217, 77, 28);
						clientinfo_pnl.add(special_sign);
		
						JLabel appointments_sign = new JLabel("Appointments: ");
						appointments_sign.setBounds(33, 257, 109, 28);
						clientinfo_pnl.add(appointments_sign);
		
		
						String name = commands.getinfo(client_id, "first_name") + " " + commands.getinfo(user_id, "last_name");
						JLabel name_lbl = new JLabel(name);
						name_lbl.setBounds(99, 38, 164, 22);
						clientinfo_pnl.add(name_lbl);	
		
						String email = commands.getinfo(client_id, "email");
						JLabel email_lbl = new JLabel(email);
						email_lbl.setBounds(102, 78, 173, 22);
						clientinfo_pnl.add(email_lbl);
		
						String address = commands.getinfo(client_id, "address");
						JLabel address_lbl = new JLabel(address);
						address_lbl.setBounds(99, 109, 188, 28);
						clientinfo_pnl.add(address_lbl);
		
						String phone = commands.getinfo(client_id, "phone_number");
						JLabel phone_lbl = new JLabel(phone);
						phone_lbl.setBounds(99, 143, 188, 28);
						clientinfo_pnl.add(phone_lbl);
		
						String dob = commands.getinfo(client_id, "DOB");
						JLabel dob_lbl = new JLabel(dob);
						dob_lbl.setBounds(99, 180, 188, 28);
						clientinfo_pnl.add(dob_lbl);
		
						String speciality = commands.getinfo(client_id, "speciality");
						JLabel special_lbl = new JLabel(speciality);
						special_lbl.setBounds(111, 217, 164, 28);
						clientinfo_pnl.add(special_lbl);
		
		
						JLabel appointments_lbl = new JLabel("New label");
						appointments_lbl.setBounds(33, 297, 223, 194);
						clientinfo_pnl.add(appointments_lbl);
		
						JLabel patientinfo_sign = new JLabel("Patient Info: ");
						patientinfo_sign.setBounds(309, 41, 109, 28);
						clientinfo_pnl.add(patientinfo_sign);
		
						JLabel lblNewLabel = new JLabel("<html>"  + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasdjfhsdjkflhsadjklfhdsfjlhsdflkjshflajshfasdljkfhsdjlfhsdjfkhsdfljsdkfhalsdfhsadklfjhsdflshdfjlasdhfjalsdhfjkdhfajskdghdfajkghkjaerhfjklaehgkjahfjkadshgasjkgh" + "</html>");
						lblNewLabel.setBounds(309, 94, 364, 395);
						clientinfo_pnl.add(lblNewLabel);
						
						JButton notes_button = new JButton("Add notes");
						notes_button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JTextArea textarea = new JTextArea(5,30);
								String text = textarea.getText();
								
							}
						});
						notes_button.setBounds(570, 6, 117, 29);
						clientinfo_pnl.add(notes_button);
		
						switchscreen(clientinfo_pnl);
					}
					else {
						JOptionPane.showMessageDialog(null, "Not a valid client please try again by pressing the button");
					}
					
				}
			});
			client_info.setBounds(21, 388, 79, 89);
			option_panel.add(client_info);
			client_info.setIcon(new ImageIcon(clients));
		}
		
		
		
		
		
		
		// ----------------------- This is where we are working on the stats label -------------------
		
		if(role.contentEquals("admin") || role.contentEquals("nurse") || role.contentEquals("doctor")){
			JLabel stats_pic = new JLabel("");
			stats_pic.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel statistics = new JPanel();
					statistics.setBackground(Color.PINK);
					layeredPane.add(statistics, "name_1347572690291341");
					statistics.setLayout(null);
					
					int totalpatient = commands.getmecount("patient");
					JLabel total_patient = new JLabel("Total # of Patients: " + totalpatient);
					total_patient.setBounds(48, 41, 150, 16);
					statistics.add(total_patient);
					
					int totaldocs = commands.getmecount("doctor");
					JLabel total_docs = new JLabel("Total # of Doctors: " + totaldocs);
					total_docs.setBounds(268, 41, 150, 16);
					statistics.add(total_docs);
					
					
					int totalnurses = commands.getmecount("nurse");
					JLabel total_nurses = new JLabel("Total # of Nurses: " + totalnurses);
					total_nurses.setBounds(491, 41, 150, 16);
					statistics.add(total_nurses);
					
					int pheart = commands.getmecount("patient", "Cardiologist");
					JLabel patient_in_heart = new JLabel("Patients in Cardiology: " + pheart);
					patient_in_heart.setBounds(48, 69, 160, 16);
					statistics.add(patient_in_heart);
					
					int pneuro = commands.getmecount("patient", "Neurologist");
					JLabel patient_in_neuro = new JLabel("Patients in Neuorolgy: " + pneuro);
					patient_in_neuro.setBounds(48, 97, 160, 16);
					statistics.add(patient_in_neuro);
					
					int pkidney = commands.getmecount("patient", "Nephrologist");
					JLabel patients_in_kidney = new JLabel("Patients in Nephrology: " + pkidney);
					patients_in_kidney.setBounds(48, 125, 160, 16);
					statistics.add(patients_in_kidney);
					
					int pemerg = commands.getmecount("patient", "Emergency");
					JLabel patients_in_emergency = new JLabel("Patients in Emergency: " + pemerg);
					patients_in_emergency.setBounds(48, 153, 160, 16);
					statistics.add(patients_in_emergency);
					
					int dheart = commands.getmecount("doctor", "Cardiologist");
					JLabel doctors_in_heart = new JLabel("Doctors in Cardiology: " + dheart);
					doctors_in_heart.setBounds(268, 69, 160, 16);
					statistics.add(doctors_in_heart);
					
					int dneuro = commands.getmecount("doctor", "Neurologist");
					JLabel doctor_in_neuro = new JLabel("Doctors in Neuorolgy: " + dneuro);
					doctor_in_neuro.setBounds(268, 97, 160, 16);
					statistics.add(doctor_in_neuro);
					
					int dkidney = commands.getmecount("doctor", "Nephrologist");
					JLabel doctors_in_kidney = new JLabel("Doctors in Nephrology: " + dkidney);
					doctors_in_kidney.setBounds(268, 125, 160, 16);
					statistics.add(doctors_in_kidney);
					
					int demerg = commands.getmecount("doctor", "Emergency");
					JLabel doctors_in_emergency = new JLabel("Doctors in Emergency: " + demerg);
					doctors_in_emergency.setBounds(268, 153, 160, 16);
					statistics.add(doctors_in_emergency);
					
					int pgeneral = commands.getmecount("patient", "General");
					JLabel patient_general = new JLabel("Patients in General: " + pgeneral);
					patient_general.setBounds(48, 181, 160, 16);
					statistics.add(patient_general);
					
					int dgeneral = commands.getmecount("doctor", "General");
					JLabel doctor_general = new JLabel("Doctors in General: " + dgeneral);
					doctor_general.setBounds(268, 181, 160, 16);
					statistics.add(doctor_general);
					
					switchscreen(statistics);
				}
			});
			stats_pic.setBounds(22, 295, 86, 82);
			option_panel.add(stats_pic);
			stats_pic.setIcon(new ImageIcon(stats));
		}
		
		layeredPane.setBounds(107, 85, 693, 515);
		main_panel.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		// Panel to use later
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_1629527414904428");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.setBounds(570, 6, 117, 29);
		panel.add(btnNewButton);
		
		
		

		
		
		
		
		
		

		// ----------------------- This is where we are working on the admin label -------------------		
		if(role.contentEquals("admin")) {
			JLabel admin_button = new JLabel("");
			admin_button.addMouseListener(new MouseAdapter() {	
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel admin_panel = new JPanel();
					admin_panel.setBackground(Color.PINK);
					layeredPane.add(admin_panel, "name_1347495746463751");
					admin_panel.setLayout(null);
					
					JPanel adddoctor_panel = new JPanel();
					adddoctor_panel.setBackground(new Color(205, 92, 92));
					adddoctor_panel.setBounds(38, 23, 230, 53);
					adddoctor_panel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							String role1 = "doctor";
							adding_personal.launch(role1);
						}
					});
					admin_panel.add(adddoctor_panel);
					adddoctor_panel.setLayout(null);
					
					JLabel add_doctor = new JLabel("Add Doctor");
					add_doctor.setBounds(83, 6, 98, 41);
					adddoctor_panel.add(add_doctor);
					
					JPanel removedoctor_panel = new JPanel();
					removedoctor_panel.setBackground(new Color(205, 92, 92));
					removedoctor_panel.setBounds(38, 86, 230, 53);
					removedoctor_panel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							String doctor= JOptionPane.showInputDialog("Please enter first name followed by space then last name of the personal you want to remove: ");
							String[] firstlast = doctor.split(" ");
							System.out.println(firstlast[0] + " " +  firstlast[1]);
							if((commands.getdoctorid(firstlast[0], firstlast[1])) == 0) {
								JOptionPane.showMessageDialog(null, "Could not find this person in the database");
							}
							else {
								int id = commands.getdoctorid(firstlast[0],firstlast[1]);
								commands.deletedoctor(id);
								JOptionPane.showMessageDialog(null, firstlast[0] +" "+ firstlast[1] +" has been deleted");	
							}
							
							
						}
					});
					admin_panel.add(removedoctor_panel);
					removedoctor_panel.setLayout(null);
					
					JLabel remove_doctor = new JLabel("Remove Personal");
					remove_doctor.setBounds(63, 6, 120, 41);
					removedoctor_panel.add(remove_doctor);
					
					JPanel addnurse_panel = new JPanel();
					addnurse_panel.setBackground(new Color(205, 92, 92));
					addnurse_panel.setBounds(329, 23, 230, 53);
					admin_panel.add(addnurse_panel);
					addnurse_panel.setLayout(null);
					
					JLabel add_nurse = new JLabel("Add Nurse");
					add_nurse.setBounds(83, 6, 98, 41);
					add_nurse.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							String role1 = "nurse";
							adding_personal.launch(role1);
						}
					});
					addnurse_panel.add(add_nurse);
					
					JPanel patient_panel = new JPanel();
					patient_panel.setBackground(new Color(205, 92, 92));
					patient_panel.setBounds(329, 86, 230, 53);
					admin_panel.add(patient_panel);
					patient_panel.setLayout(null);
					
					JLabel add_patient = new JLabel("Add Patient");
					add_patient.setBounds(83, 6, 98, 41);
					add_patient.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							String role1= "patient";
							adding_personal.launch(role1);
						}
					});
					patient_panel.add(add_patient);
					
					
					switchscreen(admin_panel);
				}
			});
			admin_button.setBounds(16, 416, 86, 96);
			option_panel.add(admin_button);
			admin_button.setIcon(new ImageIcon(adminpic));
		}
	
		
		
		mainPage.setBounds(100, 100, 800, 600);
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPage.setUndecorated(true);
		
		
	}
}
