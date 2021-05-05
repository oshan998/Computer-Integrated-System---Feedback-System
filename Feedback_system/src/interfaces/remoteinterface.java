
package interfaces;

import java.rmi.Remote;
import java.util.List;

import model.admin;
import model.chartanalytics;
import model.player;
import model.questionnaire;

/**
 * 
 * RMI interface which consist with abstract methods for various functionalities with database to exchange the data with
 * server
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 * 
 */

public interface remoteinterface extends Remote {
	
	
	public List<questionnaire> getQuestions() throws Exception;

	/**
	 * Read question list from the database
	 * 
	 * @return Questionnaire list
	 * @throws Exception
	 */
	public List<player> playerList() throws Exception;

	/**
	 * Read player list from the database
	 * 
	 * @return player list
	 * @throws Exception
	 */
	public Boolean signInData(admin adminlog) throws Exception;

	/**
	 * check the admin credentials that client application provided with admin credentials in the
	 * database to login to the system
	 * 
	 * @param admin
	 * @return the provided admin credentials is correct or not
	 * @throws Exception
	 */
	public void setAnalytics(chartanalytics analytics) throws Exception;

	/**
	 * Update the selected player's answer count for each question
	 * 
	 * @param analytics
	 * @throws Exception
	 */
	public List<chartanalytics> getAnalytics(int pId) throws Exception;

	/**
	 * Read the answer count for each question of selected player
	 * 
	 * @param pId
	 * @return Analytics list
	 * @throws Exception
	 */
	public String chartConfigure(String chartType, List<chartanalytics> analyticsList, int questionId) throws Exception;

	/**
	 * quickchart.io web API integration. This method can generate bar chart
	 * 
	 * @param chartType
	 * @param analyticsList
	 * @param questionId
	 * @return chart url
	 * @throws Exception
	 */
	

}