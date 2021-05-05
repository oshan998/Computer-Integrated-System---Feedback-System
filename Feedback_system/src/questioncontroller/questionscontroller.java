package questioncontroller;

import java.rmi.Naming;
import java.util.List;

import interfaces.remoteinterface;
import model.admin;
import model.chartanalytics;
import model.player;
import model.questionnaire;

  /**
   * 
   * This class is accessible for all GUI's. This is the class which is access the client. This provides remote object reference
   * by centralized remote methods.
   * 
   * @author Oshan Lakshitha Nanayakkara
   * UOB Number - 2022802
   */

  public class questionscontroller {

	 remoteinterface remoteInterface;
	 

	/**
	 * Client GUI has to get the reference for the remote object to locate where the server is.
	 * After that client call the interface methods 
	 */
	 public questionscontroller() {
		try {
			remoteInterface = (remoteinterface) Naming.lookup("rmi://localhost/QuestionService");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<player> playerList() throws Exception {
		return remoteInterface.playerList();
	}

	public List<questionnaire> getQuestions() throws Exception {
		return remoteInterface.getQuestions();
	}

	public void updateAnalytics(chartanalytics analytics) throws Exception {
		remoteInterface.setAnalytics(analytics);
	}

	public List<chartanalytics> getAnalytics(int PlayerId) throws Exception {
		return remoteInterface.getAnalytics(PlayerId);
	}

	public String chartConfiguration(String chartType, List<chartanalytics> analyticsList, int questionId) throws Exception {
		return remoteInterface.chartConfigure(chartType, analyticsList, questionId);
	}

	public Boolean signIn(admin adminlog) throws Exception {
		return remoteInterface.signInData(adminlog);
	}


}