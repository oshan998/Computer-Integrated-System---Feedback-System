
package backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * 
 * RMI server implementation
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class rmi {

	public rmi() throws Exception {
	}

	public static void main(String args[]) {
		System.out.println("Attempting to start the QuestionService...");
		try {
			/**
			 * dbobject is the object which is going to register(remote object)
			 * implementation class
			 */
			db_connection dbObject = new db_connection();
			/**
			 *bind the remote object in the registry 
			 */
			Registry reg = LocateRegistry.createRegistry(1099);
			/**
			 * to bind with url
			 */
			reg.bind("QuestionService", dbObject);

			System.out.println("Server has started successfully. You can proceed now.");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}