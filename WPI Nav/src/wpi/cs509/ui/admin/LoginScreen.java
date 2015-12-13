package wpi.cs509.ui.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import wpi.cs509.dataManager.DataManager;
import wpi.cs509.ui.components.ImagePanel;

public class LoginScreen {

	private JFrame frame;
	private JLabel admin, password;
	private JTextField adField,pwField;
	private JButton enterbtn, cancelbtn;
	private ImagePanel image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.decode("#F1F1F1"));
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		image = new ImagePanel("maps//wpi-logo.gif", 420, 50);
		image.setBounds(20, 20, 420, 50);
		frame.add(image);
		
		admin = new JLabel("Admin");
		admin.setBounds(50, 100, 150, 30);
		frame.add(admin);
		
		adField = new JTextField();
		adField.setBounds(120, 100, 250, 30);
		frame.add(adField);
		
		password = new JLabel("Password");
		password.setBounds(50, 150, 150, 30);
		frame.add(password);
		
		pwField = new JPasswordField();
		pwField.setBounds(120, 150, 250, 30);
		frame.add(pwField);
		
		enterbtn = new JButton("Enter");
		enterbtn.setBounds(130, 210, 100, 30);
		frame.add(enterbtn);
		enterbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(DataManager.AdminLogin(getAdmintxt(), getPwtxt()).toString() == "true"){
					new AdminScreen();
					frame.dispose();
				}
				else if(DataManager.AdminLogin(getAdmintxt(), getPwtxt()).toString() == "false"){
					JOptionPane.showMessageDialog(frame, "Admin Login Failed!");
				}
			}
		});
		
		cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(260, 210, 100, 30);
		frame.add(cancelbtn);
		cancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		cancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
	}

	public String getPwtxt() {
		return pwField.getText();
	}

	public String getAdmintxt() {
		return adField.getText();
	}

}
