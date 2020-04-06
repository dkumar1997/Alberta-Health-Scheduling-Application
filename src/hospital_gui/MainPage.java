package hospital_gui;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import allusers.*;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTable;



public class MainPage {
	
	private Image heart = new ImageIcon(MainPage.class.getResource("/heart.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image image_24 = new ImageIcon(MainPage.class.getResource("/24.jpg")).getImage().getScaledInstance(108, 86, Image.SCALE_SMOOTH);
	private Image brain = new ImageIcon(MainPage.class.getResource("/brain.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image kidney = new ImageIcon(MainPage.class.getResource("/kidney.jpeg")).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
	private Image calendar = new ImageIcon(MainPage.class.getResource("/calendar.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image adminpic = new ImageIcon(MainPage.class.getResource("/admin.png")).getImage().getScaledInstance(70,70 , Image.SCALE_SMOOTH);
	private Image labpic = new ImageIcon(MainPage.class.getResource("/lab.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private Image stats = new ImageIcon(MainPage.class.getResource("/stats.png")).getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private String role;
	private JFrame mainPage;
	private SQLQUERIES commands = new SQLQUERIES();
	
	private JLayeredPane layeredPane = new JLayeredPane();
	private Add_doctor adding_personal = new Add_doctor();
	
			

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		MainPage mainpage = new MainPage();
		mainpage.launch("admin");
	}
	
	public void launch(String role) {
		this.role = role;
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
					Calendar window = new Calendar();
					window.open();
					
				} catch (Exception r) {
					r.printStackTrace();
				}
			}
		});
		the_calendar.setBounds(12, 42, 96, 82);
		option_panel.add(the_calendar);
		the_calendar.setIcon(new ImageIcon(calendar));
		
		JLabel lab_pic = new JLabel("");
		lab_pic.setBounds(21, 172, 87, 101);
		option_panel.add(lab_pic);
		lab_pic.setIcon(new ImageIcon(labpic));
		
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
		
		

		
		
		
		
		JPanel panel_2 = new JPanel();
		layeredPane.add(panel_2, "name_1347577344803451");
		
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
