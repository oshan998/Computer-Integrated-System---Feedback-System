package model;

import java.io.Serializable;

/**
 * 
 * chartanalytics class stores the data which will display in the charts
 * it has implemented serializable because to store data into an object stream that we can send over the network or save it as file or
 * store in database.
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class chartanalytics implements Serializable {

	private static final long serialVersionUID = -4271194907664394749L;
	int PlayerID;
	int questionID;
	int answer1Count;
	int answer2Count;
	int answer3Count;
	int answer4Count;

	public chartanalytics() {
		super();
	}

	public chartanalytics(int PlayerID, int questionID, int answer1Count, int answer2Count, int answer3Count,
			int answer4Count ) {
		
		this.PlayerID = PlayerID;
		this.questionID = questionID;
		this.answer1Count = answer1Count;
		this.answer2Count = answer2Count;
		this.answer3Count = answer3Count;
		this.answer4Count = answer4Count;
		
	}

	public int getplayerID() {
		return PlayerID;
	}

	public void setplayerID(int PlayerID) {
		this.PlayerID = PlayerID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getAnswer1Count() {
		return answer1Count;
	}

	public void setAnswer1Count(int answer1Count) {
		this.answer1Count = answer1Count;
	}

	public int getAnswer2Count() {
		return answer2Count;
	}

	public void setAnswer2Count(int answer2Count) {
		this.answer2Count = answer2Count;
	}

	public int getAnswer3Count() {
		return answer3Count;
	}

	public void setAnswer3Count(int answer3Count) {
		this.answer3Count = answer3Count;
	}

	public int getAnswer4Count() {
		return answer4Count;
	}

	public void setAnswer4Count(int answer4Count) {
		this.answer4Count = answer4Count;
	}
	

	/**
	 * Inbuild method which will help when have non string variables including encapsulation. This helps to return a string
	 */
	@Override
	public String toString() {
		return "Analytics [PlayerID=" + PlayerID + ", questionID=" + questionID + ", answer1Count=" + answer1Count
				+ ", answer2Count=" + answer2Count + ", answer3Count=" + answer3Count + ", answer4Count=" + answer4Count
				+ "]";
	}

}
