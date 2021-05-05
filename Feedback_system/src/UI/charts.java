package UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import questioncontroller.questionscontroller;
import model.chartanalytics;
import model.player;

import java.awt.Choice;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import java.awt.event.ItemListener;
import java.net.URL;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * 
 * This GUI display the chart image which is generated by the quickchart.io web service.
 * This GUI looking for the remote object reference through the questionscontroller.java to send and receive data from the server.
 * 
 * Get little bit of guidance from a friend when display the bar chart image in this GUI.
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class charts extends JFrame {

	private static final long serialVersionUID = 283595486056632426L;
	private JPanel contentPane;
	

	questionscontroller questionnaireController = new questionscontroller();
	private int selectedplayerId = 1;
	private String link;
	private Image image;
	private URL url;
	private List<player> playerList;
	private List<chartanalytics> getAnalyticsList = new ArrayList<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					charts frame = new charts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor creates the frame.
	 * 
	 * @throws Exception
	 */
	public charts() throws Exception {

		/**
		 * Request the player list from the questionscontroller.java
		 */
		playerList = questionnaireController.playerList();

		/**
		 * By default this will display the bar chart image of the first player of the list ("Kusal Mendes")
		 * Request the analytics data for player from the server through the interface(questionscontroller.java) of the client side
		 */
		
		try {
			getAnalyticsList = questionnaireController.getAnalytics(selectedplayerId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1043, 677);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(7, 7, 7, 7));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(20, 54, 1000, 573);
		contentPane.add(lblNewLabel_1);

		/**
		 * By default this will display the bar chart image of the first player of the list ("Kusal Mendes")
		 * Request the bar chart image of the first player according to the analytics data and then display that generated image.
		 */
		try {
			link = questionnaireController.chartConfiguration("bar", getAnalyticsList, 0);
			url = new URL(link);
			image = ImageIO.read(url);
			
			lblNewLabel_1.setIcon(new ImageIcon(image));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		

		Choice choice = new Choice();
		choice.setFont(new Font("Arial", Font.BOLD, 14));
		/**
		 * This is used to listen the item events of the drop down list
		 */
		choice.addItemListener(new ItemListener() {
			/**
			 * Invoked when an item has been selected or deselected by the user.
			 */
			public void itemStateChanged(ItemEvent e) {
				/**
				 * When admin select a player from the drop down list then this GUI request to generate the relevant chart image from the
				 * server via the questionscontroller.java(interface of the client side)
				 * It get the analytics data for the selected player and then request to generate the relevant bar chart image according
				 * to that data of the player.
				 */
				selectedplayerId = choice.getSelectedIndex() + 1;
				try {
					getAnalyticsList = questionnaireController.getAnalytics(selectedplayerId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				try {
					link = questionnaireController.chartConfiguration("bar", getAnalyticsList, 0);
					url = new URL(link);
					image = ImageIO.read(url);
					lblNewLabel_1.setIcon(new ImageIcon(image));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		choice.setBounds(91, 20, 203, 20);
		for (int i = 0; i < playerList.size(); i++) {
			choice.add(playerList.get(i).getName());
		}
		contentPane.add(choice);

		JLabel lblNewLabel = new JLabel("Player");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 20, 65, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_12_1 = new JLabel("");
		lblNewLabel_12_1.setBackground(SystemColor.menu);
		lblNewLabel_12_1.setBounds(1003, 20, 42, 35);
		contentPane.add(lblNewLabel_12_1);

		JLabel lblC_1 = new JLabel("Questions");
		lblC_1.setForeground(Color.WHITE);
		lblC_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblC_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblC_1.setBounds(313, 20, 106, 23);
		contentPane.add(lblC_1);

		lblC_1.setVisible(true);
		

		/**
		 * Drop down list to display questions. Then admin knows what is the question that is relevant to that question number
		 * which indicates in the chart.
		 */
		Choice choice_1 = new Choice();
		choice_1.setFont(new Font("Arial", Font.BOLD, 14));
		
		choice_1.setBounds(425, 20, 407, 23);
		choice_1.add("1.	How well he completed the 2KM run?");
		choice_1.add("2.	How well he completed 15 pushups?");
		choice_1.add("3.	How well he completed the warmup exercises?");
		choice_1.add("4.	How he looks after the exercises?");
		choice_1.add("5.	How well he done the stretching exercises?");
		choice_1.add("6.	How is his diet plan during training period?");
		choice_1.add("7.	How is his immune in this period?");



		contentPane.add(choice_1);

		choice_1.setVisible(true);
		
	
		/**
		 * Home button to go back to the homescreen.java
		 */
		JButton btnNewButton_1 = new JButton("Home");
		/**
		 * ActionListener is used to respond the button click events. Object created with this class is registered with a 
		 * component using addActionListener method
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs. 
			 */
			public void actionPerformed(ActionEvent e) {
				homescreen hs = new homescreen();
				hs.frame.setVisible(true);
				charts.this.dispose();
			}
		});
		btnNewButton_1.setBounds(890, 20, 93, 23);
		contentPane.add(btnNewButton_1);


	}
}
