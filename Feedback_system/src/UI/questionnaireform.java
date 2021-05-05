package UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import questioncontroller.questionscontroller;
import model.chartanalytics;
import model.player;
import model.questionnaire;
import model.answers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 
 * This GUI display the questions of the system and it allow user to submit the answers to the system.
 * This GUI looking for the remote object reference through the questionscontroller.java to send and receive data from the server.
 * 
 * Get little bit of guidance from a friend when building the algorithm for the Finish button and Next button
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */

public class questionnaireform extends JFrame {

	private static final long serialVersionUID = 3521136443067340974L;
	private JPanel contentPane;

	private static int position = 0;
	private String answer = null;
	private int selectedplayerId = 1;
	private static questionnaire currentQuestion;
	private static int currentQuestionId;

	private int answerId;

	/**
	 * Answers which are provided keep in memory as a list until user submit. After submission it will update the answer count
	 * in the database through the server
	 */
	private List<answers> selectedAnswers = new ArrayList<>();
	private List<chartanalytics> getAnalyticsList = new ArrayList<>();
	private List<player> playerList;
	private List<questionnaire> questionList;
	questionscontroller questionnaireController = new questionscontroller();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					questionnaireform qframe = new questionnaireform();
					qframe.setVisible(true);
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
	public questionnaireform() throws Exception {

		playerList = questionnaireController.playerList();
		questionList = questionnaireController.getQuestions();
		currentQuestion = questionList.get(position);
		currentQuestionId = questionList.get(position).getQuestionId();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(78, 10, 616, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Select Player : ");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(8, 16, 133, 19);
		panel_1.add(lblNewLabel);

		

		Choice choice = new Choice();
		
		
		
		/**
		 * This is used to listen the item events of the drop down list
		 */
		choice.addItemListener(new ItemListener() {
			/**
			 * Invoked when an item has been selected or deselected by the user.
			 */
			public void itemStateChanged(ItemEvent e) {
				selectedplayerId = choice.getSelectedIndex() + 1;
			}
		});
		choice.setFont(new Font("Bahnschrift", Font.PLAIN, 16));

		for (int i = 0; i < playerList.size(); i++) {
			choice.add(playerList.get(i).getName());
		}

		choice.setBounds(152, 10, 184, 25);
		choice.setBackground(new Color(255, 255, 255));
		panel_1.add(choice);

		JLabel lblNewLabel_2 = new JLabel(currentQuestion.getQuestion());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel_2.setBounds(18, 88, 760, 61);
		contentPane.add(lblNewLabel_2);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(85, 191, 609, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		/**
		 * Add a image to the label which represent answer
		 */
		JLabel excellentImg = new JLabel("");
		excellentImg.setBounds(10, 5, 64, 64);
		excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellentB.png")));
		panel.add(excellentImg);
		/**
		 * Add a image to the label which represent answer
		 */
		JLabel goodImg = new JLabel("");
		goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/goodB.png")));
		goodImg.setBounds(180, 5, 64, 64);
		panel.add(goodImg);
		/**
		 * Add a image to the label which represent answer
		 */
		JLabel averageImg = new JLabel("");
		averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/averageB.png")));
		averageImg.setBounds(350, 5, 64, 64);
		panel.add(averageImg);
		/**
		 * Add a image to the label which represent answer
		 */
		JLabel badImg = new JLabel("");
		badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/badB.png")));
		badImg.setBounds(520, 5, 64, 64);
		panel.add(badImg);
		/**
		 * get the answer for each label
		 */
		JLabel lblNewLabel_4 = new JLabel(currentQuestion.getAnswer1());
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 88, 78, 20);
		lblNewLabel_4.setForeground(Color.WHITE);
		panel.add(lblNewLabel_4);

		/**
		 * get the answer for each label
		 */
		JLabel lblNewLabel_5 = new JLabel(currentQuestion.getAnswer2());
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setBounds(178, 88, 78, 20);
		lblNewLabel_5.setForeground(Color.WHITE);
		panel.add(lblNewLabel_5);

		/**
		 * get the answer for each label
		 */
		JLabel lblNewLabel_6 = new JLabel(currentQuestion.getAnswer3());
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_6.setBounds(335, 88, 94, 20);
		lblNewLabel_6.setForeground(Color.WHITE);
		panel.add(lblNewLabel_6);

		/**
		 * get the answer for each label
		 */
		JLabel lblNewLabel_7 = new JLabel(currentQuestion.getAnswer4());
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_7.setBounds(540, 88, 88, 20);
		lblNewLabel_7.setForeground(Color.WHITE);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_3 = new JLabel("");
		/**
		 * MouseListener used to listen mouse click events
		 */
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 */
			public void mouseClicked(MouseEvent e) {
				position = 0;
				answer = null;
				currentQuestion = null;
				questionList.clear();
				homescreen homePage = new homescreen();
				homePage.setVisible(true);
				questionnaireform.this.dispose();
			}
		});
		

		JLabel lblNewLabel_8 = new JLabel("01");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_8.setBackground(new Color(0, 204, 153));
		lblNewLabel_8.setBounds(360, 423, 57, 30);
		contentPane.add(lblNewLabel_8);

		JButton btnNewButton_2 = new JButton("Finish");
		btnNewButton_2.setVisible(false);
	
		/**
		 * ActionListener is used to respond the button click events. Object created with this class is registered with a 
		 * component using addActionListener method
		 */
		btnNewButton_2.addActionListener(new ActionListener() {
			/**
			 * Invoked when an action occurs. 
			 */
			public void actionPerformed(ActionEvent e) {

				/**
				 * Get the analytics 
				 */
				try {
					getAnalyticsList = questionnaireController.getAnalytics(selectedplayerId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				/**
				 * It will store the question ID and the answer ID of the each question user has answered as an arraylist.
				 */
				answers selectedAnswer = new answers(currentQuestionId, answerId);
				selectedAnswers.add(selectedAnswer);

				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Submit the feedback data of cricketers to the system?", "Submit Confirmation Notice",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
				} else if (response == JOptionPane.YES_OPTION) {

					/**
					 * If user has selected Yes from the confirm message then this happen
					 */
					for (int i = 0; i < selectedAnswers.size() ; i++) {
						
						chartanalytics analytics = new chartanalytics();

						int answer1count = 0;
						int answer2count = 0;
						int answer3count = 0;
						int answer4count = 0;

						if (getAnalyticsList != null) {

							/**
							 * Get the answer count data for the each variable
							 */
							answer1count = getAnalyticsList.get(i).getAnswer1Count();
							answer2count = getAnalyticsList.get(i).getAnswer2Count();
							answer3count = getAnalyticsList.get(i).getAnswer3Count();
							answer4count = getAnalyticsList.get(i).getAnswer4Count();
						}

						/**
						 * Set the player ID and the question ID as an arraylist.
						 */
						analytics.setplayerID(selectedplayerId);
						analytics.setQuestionID(selectedAnswers.get(i).getQuestionId());

						/**
						 * If user selected an answer then it will increment answer count by 1 according to the answer that
						 * user selected
						 */
						if (selectedAnswers.get(i).getSelectedAnswerId() == 1) {
							analytics.setAnswer1Count(answer1count + 1);
							analytics.setAnswer2Count(answer2count);
							analytics.setAnswer3Count(answer3count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectedAnswers.get(i).getSelectedAnswerId() == 2) {
							analytics.setAnswer2Count(answer2count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer3Count(answer3count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectedAnswers.get(i).getSelectedAnswerId() == 3) {
							analytics.setAnswer3Count(answer3count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer2Count(answer2count);
							analytics.setAnswer4Count(answer4count);

						} else if (selectedAnswers.get(i).getSelectedAnswerId() == 4) {
							analytics.setAnswer4Count(answer4count + 1);
							analytics.setAnswer1Count(answer1count);
							analytics.setAnswer2Count(answer2count);
							analytics.setAnswer3Count(answer3count);
						}


						/**
						 * Call Update analytics according to the data that user selected
						 */
						try {
							questionnaireController.updateAnalytics(analytics);
						} catch (Exception e1) {
							System.out.println(e1);
						}
					

					}

					position = 0;
					answer = null;
					currentQuestion = null;
					questionList.clear();
					homescreen hs = new homescreen();
					hs.frame.setVisible(true);
					questionnaireform.this.dispose();
					
				} else if (response == JOptionPane.CLOSED_OPTION) {
				}

			}
		});
		
		/**
		 * when Next button clicked
		 */
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_2.setBounds(678, 419, 100, 34);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("Next");

		/**
		 * If user didn't select an answer, Next button will remain disable. If user has selected one of the answer then
		 * Next button will be enable.
		 */
		if (answer == null) {
			btnNewButton_1.setEnabled(false);
			
		} else {
			btnNewButton_1.setEnabled(true);	
		}

		/**
		 * ActionListener to handle Next button action
		 */
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * When new question loads then the Next button of that GUI remains disable until user select an answer.
				 */
				btnNewButton_1.setEnabled(false);

				/**
				 * When Next button clicked it increment the position by 1 and then it will 
				 * display the new question with new answers this happen until the position = 6 because it start with 0 and has 7 questions.
				 */
				answers selectAnswer = new answers(currentQuestionId, answerId);
				selectedAnswers.add(selectAnswer);

				position++;

				/**
				 * According to the new position it set the new question and answers for that new question.
				 */
				if (position < questionList.size()) {
					currentQuestion = questionList.get(position);
					currentQuestionId = questionList.get(position).getQuestionId();
					lblNewLabel_2.setText(currentQuestion.getQuestion());
					lblNewLabel_4.setText(currentQuestion.getAnswer1());
					lblNewLabel_5.setText(currentQuestion.getAnswer2());
					lblNewLabel_6.setText(currentQuestion.getAnswer3());
					lblNewLabel_7.setText(currentQuestion.getAnswer4());

					/**
					 * When a new question and answers loads then it will set the images of the answers to black and white images
					 * until user select an image.
					 */
					excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellentB.png")));
					goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/goodB.png")));
					averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/averageB.png")));
					badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/badB.png")));

					answer = null;

					int questionNumber = position + 1;

					lblNewLabel_8.setText("0" + Integer.toString(questionNumber));

				}

				/**
				 * If position reaches 6 then it will display the Finish button.
				 */
				if (position + 1 == questionList.size()) {
					
					btnNewButton_2.setVisible(true);
				} else {
					btnNewButton_2.setVisible(false);
				}

				if (position != 0) {
					panel_1.setVisible(false);
				}

			}
		});
		
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton_1.setBounds(678, 419, 100, 34);
		contentPane.add(btnNewButton_1);
		
		/**
		 * Mouse click events that triggers when answer 1 is selected it changes from black and white image to color image and other
		 * answer images remain same as the black and white
		 */
		excellentImg.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 */
			public void mouseClicked(MouseEvent e) {
				answerId = 1;
				excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellent.png")));
				goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/goodB.png")));
				averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/averageB.png")));
				badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/badB.png")));

				/**
				 * If that image label clicked, then get the answer label which is below that image label.
				 */
				answer = lblNewLabel_4.getText();
				/**
				 * If the answer is selected then the Next button will enable otherwise user cannot click it(Disabled).
				 */
				btnNewButton_1.setEnabled(true);
				

			}
		});

		/**
		 * Mouse click events that triggers when answer 2 is selected it changes from black and white image to color image and other
		 * answer images remain same as the black and white
		 */
		goodImg.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 */
			public void mouseClicked(MouseEvent e) {
				answerId = 2;
				goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/good.png")));
				excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellentB.png")));
				averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/averageB.png")));
				badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/badB.png")));

				/**
				 * If that image label clicked, then get the answer label which is below that image label.
				 */
				answer = lblNewLabel_5.getText();
				/**
				 * If the answer is selected then the Next button will enable otherwise user cannot click it(Disabled).
				 */
				btnNewButton_1.setEnabled(true);
				

			}
		});

		/**
		 * Mouse click events that triggers when answer 3 is selected it changes from black and white image to color image and other
		 * answer images remain same as the black and white
		 */
		averageImg.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 */
			public void mouseClicked(MouseEvent e) {
				answerId = 3;
				averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/average.png")));
				excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellentB.png")));
				goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/goodB.png")));
				badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/badB.png")));

				/**
				 * If that image label clicked, then get the answer label which is below that image label.
				 */
				answer = lblNewLabel_6.getText();
				/**
				 * If the answer is selected then the Next button will enable otherwise user cannot click it(Disabled).
				 */
				btnNewButton_1.setEnabled(true);
				

			}
		});

		/**
		 * Mouse click events that triggers when answer 4 is selected it changes from black and white image to color image and other
		 * answer images remain same as the black and white
		 */
		badImg.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * Invoked when the mouse button has been clicked (pressed and released) on a component.
			 */
			public void mouseClicked(MouseEvent e) {
				answerId = 4;
				badImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/bad.png")));
				excellentImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/excellentB.png")));
				goodImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/goodB.png")));
				averageImg.setIcon(new ImageIcon(questionnaireform.class.getResource("/img/averageB.png")));

				/**
				 * If that image label clicked, then get the answer label which is below that image label.
				 */
				answer = lblNewLabel_7.getText();
				/**
				 * If the answer is selected then the Next button will enable otherwise user cannot click it(Disabled).
				 */
				btnNewButton_1.setEnabled(true);
				

			}
		});

	}
}

