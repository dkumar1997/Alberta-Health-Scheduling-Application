package hospital_gui;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





public class LoginPage {

	private JFrame hospital_login;
	private JTextField username_intake;
	private JTextField password_intake;
	private SQLQUERIES sqlcommands = new SQLQUERIES();
	

	

	
	//Launch the application.
	 public static void main(String[] args) {
		 LoginPage starting= new LoginPage();
		 starting.launch();
		 
	 }



	
	//Create the application.
	 
	
	public void launch(){		
		login();
		hospital_login.setVisible(true);
		
		
		
	}
	
	//all functions to do with the database

	
	private void login() {
		
		hospital_login = new JFrame();
		hospital_login.setTitle("hospitallogin");
		hospital_login.getContentPane().setBackground(new Color(0, 139, 139));
		
		
		hospital_login.setUndecorated(true);
		
		hospital_login.setBounds(100, 100, 800, 300);
		hospital_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hospital_login.getContentPane().setLayout(null);
		
		JLabel userlabel = new JLabel("Username:");
		userlabel.setForeground(new Color(255, 255, 255));
		userlabel.setBounds(406, 30, 88, 39);
		hospital_login.getContentPane().add(userlabel);
		
		JLabel passlabel = new JLabel("Password:");
		passlabel.setForeground(new Color(255, 255, 255));
		passlabel.setBounds(406, 82, 88, 39);
		hospital_login.getContentPane().add(passlabel);
		
		username_intake = new JTextField();
		username_intake.setBounds(483, 29, 250, 40);
		username_intake.setBackground(Color.WHITE);
		hospital_login.getContentPane().add(username_intake);
		username_intake.setColumns(10);
		
		password_intake = new JTextField();
		password_intake.setBounds(483, 81, 250, 40);
		password_intake.setBackground(Color.WHITE);
		hospital_login.getContentPane().add(password_intake);
		password_intake.setColumns(10);
		
		// login button
		// this is a custom button for sign up using j panel
		JPanel login_button = new JPanel();
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username_value = username_intake.getText();
				String password_value = password_intake.getText();
				if(sqlcommands.checkaccount(username_value,password_value)) {
					JOptionPane.showMessageDialog(null, "You have successfully logged in");
					String role = sqlcommands.getrole(username_value,password_value);
					MainPage theMain = new MainPage();
					theMain.launch(role);
					hospital_login.dispose();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "This account does not exist or invalid input");
				}	
			}
		});
		login_button.setBackground(new Color(47, 79, 79));
		login_button.setBounds(483, 141, 250, 50);
		hospital_login.getContentPane().add(login_button);
		login_button.setLayout(null);
		
		JLabel loginbuttonlabel = new JLabel("LOGIN");
		loginbuttonlabel.setForeground(new Color(255, 255, 255));
		loginbuttonlabel.setFont(new Font("Arial", Font.BOLD, 14));
		loginbuttonlabel.setBounds(101, 6, 95, 38);
		login_button.add(loginbuttonlabel);
		
		//exit the login screen button
		//this is a custom close built j label
		JLabel exit_label = new JLabel("X");
		exit_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION)== 0){
					hospital_login.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit_label.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent e) {
				exit_label.setForeground(Color.WHITE);
			}
		});
		exit_label.setForeground(new Color(255, 255, 255));
		exit_label.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		exit_label.setBounds(773, 6, 21, 16);
		hospital_login.getContentPane().add(exit_label);
		
		//button for making a new account
		// this is a custom sign up button made with a j panel
		JPanel signup_button = new JPanel();
		signup_button.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String role = "patient";
				Add_doctor newadd = new Add_doctor();
				newadd.launch(role);
				
				
				
			}
		});
		signup_button.setLayout(null);
		signup_button.setBackground(new Color(47, 79, 79));
		signup_button.setBounds(483, 202, 250, 50);
		hospital_login.getContentPane().add(signup_button);
		
		JLabel sign_uplbl = new JLabel("SIGN UP");
		sign_uplbl.setForeground(Color.WHITE);
		sign_uplbl.setFont(new Font("Arial", Font.BOLD, 14));
		sign_uplbl.setBounds(93, 6, 95, 38);
		signup_button.add(sign_uplbl);
		
		JLabel health_image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("alberta_health.jpg")).getImage();
		health_image.setIcon(new ImageIcon(img));
		health_image.setBounds(0, 0, 385, 300);
		hospital_login.getContentPane().add(health_image);			
		
	}
}
