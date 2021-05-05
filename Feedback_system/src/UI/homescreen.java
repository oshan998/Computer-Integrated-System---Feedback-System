package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * 
 * This is the home screen of the feedback system. By this GUI user can access the questionnaire or the charts.
 * This GUI has separate buttons which navigate user to different GUI's according to their need.
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802 
 */
public class homescreen extends JFrame {

	JFrame frame;
	private JTextPane txtpnSriLankaCricket;
	private JTextPane txtpnFeedbackSystemFor;
	private static final long serialVersionUID = 7509028678520003425L;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homescreen window = new homescreen();
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
	public homescreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 664, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Button for display adminlogin.java GUI
		 */
		JButton btnNewButton_1 = new JButton("Admin Login");
		/**
		 * ActionListener is used to respond the button click events. Object created with this class is registered with a 
		 * component using addActionListener method
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs. 
			 */
			public void actionPerformed(ActionEvent e) {
				adminlogin loginframe = new adminlogin();
				loginframe.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_1.setBounds(483, 27, 126, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		txtpnSriLankaCricket = new JTextPane();
		txtpnSriLankaCricket.setEditable(false);
		txtpnSriLankaCricket.setFont(new Font("Arial", Font.BOLD, 30));
		txtpnSriLankaCricket.setForeground(Color.WHITE);
		txtpnSriLankaCricket.setBackground(Color.DARK_GRAY);
		txtpnSriLankaCricket.setText("Sri Lanka Cricket");
		txtpnSriLankaCricket.setBounds(52, 87, 299, 35);
		frame.getContentPane().add(txtpnSriLankaCricket);
		
		txtpnFeedbackSystemFor = new JTextPane();
		txtpnFeedbackSystemFor.setEditable(false);
		txtpnFeedbackSystemFor.setFont(new Font("Arial", Font.BOLD, 16));
		txtpnFeedbackSystemFor.setBackground(Color.DARK_GRAY);
		txtpnFeedbackSystemFor.setForeground(Color.WHITE);
		txtpnFeedbackSystemFor.setText("Feedback system for players \r\nfitness analysis");
		txtpnFeedbackSystemFor.setBounds(52, 128, 251, 50);
		frame.getContentPane().add(txtpnFeedbackSystemFor);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(30);
		flowLayout.setHgap(40);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 311, 648, 86);
		frame.getContentPane().add(panel);
		
		/**
		 * Button for start and display the questions of the system.
		 */
		JButton btnNewButton = new JButton("Start Questionnaire");
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		/**
		 * ActionListener is used to respond the button click events. Object created with this class is registered with a 
		 * component using addActionListener method
		 */
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs. 
			 */
			public void actionPerformed(ActionEvent e) {
				questionnaireform form;
				try {
					form = new questionnaireform();
					form.setVisible(true);
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
}



