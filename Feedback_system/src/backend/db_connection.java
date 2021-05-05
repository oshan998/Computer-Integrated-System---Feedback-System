package backend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import model.chartanalytics;
import interfaces.remoteinterface;
import model.admin;
import model.player;
import model.questionnaire;




/**
 * 
 * Implementation of the remote interface. This class bind into a registry as a remote object
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class db_connection extends UnicastRemoteObject implements remoteinterface {

	private static final long serialVersionUID = -9088567564909054283L;
	Connection connection = null;
	Statement statement = null;

	/**
	 * connect to database using jdbc driver
	 * @throws Exception
	 */
	public db_connection() throws Exception {
		/**
		 * when we created object of db_connection it will call db_connection constructor and 
		 * indirectly call super() which will handle the exception 
		 */
		super(); 
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cricketfeedbackdb", "root", "");
			System.out.print("Connected database & ");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<questionnaire> getQuestions() throws Exception {
		ArrayList<questionnaire> questionnaireList = new ArrayList<>();

		/**
		 * creating the query to access the database  
		 */
		statement = connection.createStatement();
		
		/**
		 * SQL query to get data from database
		 */
		String sql = "SELECT * FROM questions";
        
		/**
		 * executing the query
		 */
		ResultSet resultSet = statement.executeQuery(sql);

		/**
		 * Extract data from ResultSet
		 */
		while (resultSet.next()) {
			questionnaire questions = new questionnaire(resultSet.getInt("QuestionID"),
					resultSet.getString("Question"), resultSet.getString("Answer1"), resultSet.getString("Answer2"),
					resultSet.getString("Answer3"), resultSet.getString("Answer4"));
			questionnaireList.add(questions);
		}

		resultSet.close();

		return questionnaireList;
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public List<player> playerList() throws Exception {

		ArrayList<player> playerList = new ArrayList<>();
		
		/**
		 * creating the query to access the database
		 */
		statement = connection.createStatement();
		
		/**
		 * SQL query to get data from database
		 */
		String sql = "SELECT * FROM player";
		
		/**
		 * executing the query
		 */
		ResultSet resultSet = statement.executeQuery(sql);
        
		/**
		 * Extract data from ResultSet
		 */
		while (resultSet.next()) {
			player playerInformation = new player(resultSet.getInt("playerID"),
					resultSet.getString("playerName"));
			playerList.add(playerInformation);
		}

		resultSet.close();

		return playerList;
	}

	/**
	 * Implementation of remote interface method.
	 * check username and password with database and sever return that data as a boolean to client
	 */
	@Override
	public Boolean signInData(admin adminlog) throws Exception {
		ResultSet resultSet = null;
		
		/**
		 * creating the query to access the database
		 */
		statement = connection.createStatement();
		
		/**
		 * SQL query to get data from database
		 */
		String sql = "SELECT * FROM admin WHERE UserName ='" + adminlog.getName() + "' AND Password ='"
				+ adminlog.getPassword() + "'";

		try {
			/**
			 * executing the query
			 */
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultSet.next()) {
			resultSet.close();
			return true;

		} else {
			resultSet.close();
			return false;
		}
	}

	/**
	 * Implementation of remote interface method.
	 */
	@Override
	public void setAnalytics(chartanalytics analytics) throws Exception {
		
		/**
		 * creating the query to access the database
		 */
		statement = connection.createStatement();
		
		/**
		 * SQL query to get data from database
		 */
		String sql = "UPDATE analytics SET Answer1Count = '" + analytics.getAnswer1Count() + "', Answer2Count = " + " '"
				+ analytics.getAnswer2Count() + "', Answer3Count = " + " '" + analytics.getAnswer3Count()
				+ "', Answer4Count = " + " '" + analytics.getAnswer4Count() + "' WHERE playerID = '"
				+ analytics.getplayerID() + "'AND QuestionID = '" + analytics.getQuestionID() + "'";
		
		/**
		 * executing the query
		 */
		statement.executeUpdate(sql);

	}

	/**
	 * Implementation of remote interface method.
	 */
	
	@Override
	public List<chartanalytics> getAnalytics(int pId) throws Exception {

		ArrayList<chartanalytics> analyticsList = new ArrayList<>();
		
		/**
		 * creating the query to access the database
		 */
		statement = connection.createStatement();
		
		/**
		 * SQL query to get data from database
		 */
		String sql = "SELECT * FROM analytics WHERE playerID = '" + pId + "'";
        
		/**
		 * executing the query
		 */
		ResultSet resultSet = statement.executeQuery(sql);

		/**
		 * Extract data from ResultSet
		 */
		while (resultSet.next()) {
			chartanalytics analytics = new chartanalytics(resultSet.getInt("playerID"), resultSet.getInt("QuestionID"),
					resultSet.getInt("Answer1Count"), resultSet.getInt("Answer2Count"),
					resultSet.getInt("Answer3Count"), resultSet.getInt("Answer4Count"));
			analyticsList.add(analytics);
		}

		return analyticsList;
	}
	
	/**
	 * Quickchart.io. 2021. Documentation. [online] Available 
	 * at: <https://quickchart.io/documentation/> [Accessed 1 April 2021].
	 * 
	 * Openjdk.java.net. 2021. Java HTTP Client - Examples and Recipes. [online] Available 
	 * at: <https://openjdk.java.net/groups/net/httpclient/recipes.html> [Accessed 1 April 2021].
	 * 
	 */
	
	@Override
	public String chartConfigure(String chartType, List<chartanalytics> analyticsList, int questionId) throws Exception {
		/**
		 * HttpClient for api connection
		 */
		HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		/**
		 * get the data in analytics table from the database to create the chart 
		 */
		chartanalytics analytics1 = new chartanalytics(analyticsList.get(0).getplayerID(), analyticsList.get(0).getQuestionID(),
				analyticsList.get(0).getAnswer1Count(), analyticsList.get(0).getAnswer2Count(),
				analyticsList.get(0).getAnswer3Count(), analyticsList.get(0).getAnswer4Count());
		chartanalytics analytics2 = new chartanalytics(analyticsList.get(1).getplayerID(), analyticsList.get(1).getQuestionID(),
				analyticsList.get(1).getAnswer1Count(), analyticsList.get(1).getAnswer2Count(),
				analyticsList.get(1).getAnswer3Count(), analyticsList.get(1).getAnswer4Count());
		chartanalytics analytics3 = new chartanalytics(analyticsList.get(2).getplayerID(), analyticsList.get(2).getQuestionID(),
				analyticsList.get(2).getAnswer1Count(), analyticsList.get(2).getAnswer2Count(),
				analyticsList.get(2).getAnswer3Count(), analyticsList.get(2).getAnswer4Count());
		chartanalytics analytics4 = new chartanalytics(analyticsList.get(3).getplayerID(), analyticsList.get(3).getQuestionID(),
				analyticsList.get(3).getAnswer1Count(), analyticsList.get(3).getAnswer2Count(),
				analyticsList.get(3).getAnswer3Count(), analyticsList.get(3).getAnswer4Count());
		chartanalytics analytics5 = new chartanalytics(analyticsList.get(4).getplayerID(), analyticsList.get(4).getQuestionID(),
				analyticsList.get(4).getAnswer1Count(), analyticsList.get(4).getAnswer2Count(),
				analyticsList.get(4).getAnswer3Count(), analyticsList.get(4).getAnswer4Count());
		chartanalytics analytics6 = new chartanalytics(analyticsList.get(5).getplayerID(), analyticsList.get(5).getQuestionID(),
				analyticsList.get(5).getAnswer1Count(), analyticsList.get(5).getAnswer2Count(),
				analyticsList.get(5).getAnswer3Count(), analyticsList.get(5).getAnswer4Count());
		chartanalytics analytics7 = new chartanalytics(analyticsList.get(6).getplayerID(), analyticsList.get(6).getQuestionID(),
				analyticsList.get(6).getAnswer1Count(), analyticsList.get(6).getAnswer2Count(),
				analyticsList.get(6).getAnswer3Count(), analyticsList.get(6).getAnswer4Count());

		String str = "";
		
		/**
		 * create chart. create according to how many times answer count for each 7 questions.
		 */
		
			str = "{\"chart\": {\"type\":\"" + chartType + "\",\"data\":"
					+ "{\"labels\":[\"Question 1\",\"Question 2\",\"Question 3\",\"Question 4\",\"Question 5\",\"Question 6\",\"Question 7\"],"
					+ "\"datasets\":[{\"label\":\"Excellent\",\"backgroundColor\":\"rgba(0, 255, 0, 0.5)\",\"borderColor\":\"rgb(0, 255, 0)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer1Count() + "," + analytics2.getAnswer1Count() + ","
					+ analytics3.getAnswer1Count() + "," + analytics4.getAnswer1Count() + ","
					+ analytics5.getAnswer1Count() + "," + analytics6.getAnswer1Count() + "," + analytics7.getAnswer1Count() + "]},"
					+ "{\"label\":\"Good\",\"backgroundColor\":\"rgba(30, 144, 255, 0.5)\",\"borderColor\":\"rgb(30, 144, 255)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer2Count() + "," + analytics2.getAnswer2Count() + ","
					+ analytics3.getAnswer2Count() + "," + analytics4.getAnswer2Count() + ","
					+ analytics5.getAnswer2Count() + "," + analytics6.getAnswer2Count() + "," + analytics7.getAnswer2Count() + "]},"
					+ "{\"label\":\"Average\",\"backgroundColor\":\"rgba(255, 215, 0, 0.5)\",\"borderColor\":\"rgb(255, 215, 0)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer3Count() + "," + analytics2.getAnswer3Count() + ","
					+ analytics3.getAnswer3Count() + "," + analytics4.getAnswer3Count() + ","
					+ analytics5.getAnswer3Count() + "," + analytics6.getAnswer3Count() + "," + analytics7.getAnswer3Count() + "]},"
					+ "{\"label\":\"Bad\",\"backgroundColor\":\"rgba(255, 0, 0, 0.5)\",\"borderColor\":\"rgb(255, 0, 0)\",\"borderWidth\":1,\"data\":["
					+ analytics1.getAnswer4Count() + "," + analytics2.getAnswer4Count() + ","
					+ analytics3.getAnswer4Count() + "," + analytics4.getAnswer4Count() + ","
					+ analytics5.getAnswer4Count() + "," + analytics6.getAnswer4Count() + "," + analytics7.getAnswer4Count() + "]}]},"
					+ "\"options\":{\"responsive\":true,\"legend\":{\"position\":\"top\"},\"title\":{\"display\":true,\"text\":\"Sri Lanka Cricketers Data\"}}}}";

	
		/**
		 * send a HttpRequest to quickchart.io server and request to read it as json by application/json
		 */
		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(str))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")
				.header("Content-Type", "application/json").build();

		/**
		 * get the url as a response 
		 */
		HttpResponse<String> response = null;
		try {
			/**
			 * receive generated chart image url through httpclient
			 */
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		String url = null;

		/**
		 *  returned body may represent the body after it was read (such as byte[]). get jsonobject of url
		 */
		JSONObject jsonObject = new JSONObject(response.body());
		url = (String) jsonObject.get("url");
		

		/**
		 * get the generated image from the url and show it in image view by converting to file
		 */
		URL url_ = new URL(url);
		InputStream is = url_.openStream();
		OutputStream os = new FileOutputStream("chart.png");
	

		/**
		 * Conversion of url to file
		 */
		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();

		return url;
	}

}
