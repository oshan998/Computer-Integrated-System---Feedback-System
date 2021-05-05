package model;

/** 
 * 
 * Answer class store the data related to system which collect from the user
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class answers {

	int questionId;
	int selectedAnswerId;
	public answers(int questionId, int selectedAnswerId) {
		
		this.questionId = questionId;
		this.selectedAnswerId = selectedAnswerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getSelectedAnswerId() {
		return selectedAnswerId;
	}
	public void setSelectedAnswerId(int selectedAnswerId) {
		this.selectedAnswerId = selectedAnswerId;
	}
	
	
}