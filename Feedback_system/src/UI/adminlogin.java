package UI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.admin;
import questioncontroller.questionscontroller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * 
 * This is the admin login GUI. This used by the client side to login to the system and view charts.
 * This GUI looking for the remote object reference through the questionscontroller.java to send and receive data from the server.
 * 
 * Get little bit of guidance from a friend when creating this login mechanism which check user inputs with the RMI server
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class adminlogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtAdminLogin;
	private JTextField username;
	private JPasswordField passwordField;
	
	private static final long serialVersionUID = -2763124822272080102L;
	questionscontroller qcontroller = new questionscontroller();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin loginframe = new adminlogin();
					loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 408);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAdminLogin = new JTextField();
		txtAdminLogin.setFont(new Font("Arial", Font.BOLD, 30));
		txtAdminLogin.setBackground(Color.BLACK);
		txtAdminLogin.setForeground(Color.WHITE);
		txtAdminLogin.setText("                     Admin Login");
		txtAdminLogin.setEditable(false);
		txtAdminLogin.setBounds(0, 0, 498, 72);
		contentPane.add(txtAdminLogin);
		txtAdminLogin.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(67, 136, 120, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_1.setBounds(67, 203, 130, 30);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		username.setBounds(236, 134, 153, 32);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		/**
		 * ActionListener is used to respond the button click events. Object created with this class is registered with a 
		 * component using addActionListener method
		 */
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs. 
			 */
			public void actionPerformed(ActionEvent e) {
			
				try {
					/**
					 * This GUI get the username and password from the textfields and send them back to server for it's correctness via
					 * the questionscontroller.java (interface).
					 * Then server checks the correctness and send  as a boolean value to the client GUI.
					 * If the credentials are correct then it will allow to access charts.java
					 */
					admin adminlog = new admin(1, username.getText(), passwordField.getText());
					if (qcontroller.signIn(adminlog)) {
						charts Charts = new charts();
						Charts.setVisible(true);
						adminlogin.this.dispose();
					} else {
						/**
						 * Provides a hint as to whether or not newly created JDialogs should have their Window decorations 
						 * provided by the current look and feel.
						 */
						JDialog.setDefaultLookAndFeelDecorated(true);
						int response = JOptionPane.showConfirmDialog(null,
								"Invalid credentials, Do you want to try again?", "warning!", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.NO_OPTION) {
						} else if (response == JOptionPane.YES_OPTION) {
							username.setText(null);
							passwordField.setText(null);
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(174, 282, 120, 39);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(236, 207, 153, 30);
		contentPane.add(passwordField);
	}
}
