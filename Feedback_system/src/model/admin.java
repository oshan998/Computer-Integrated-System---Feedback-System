package model;

/**
 *
 * admin class is a child class which consist of data related to admin
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class admin extends player {

	private static final long serialVersionUID = -2567828114301586478L;
	private String password;

	public admin(int adminID, String name, String password) {
		/**
		 * call parent class variables in the constructor
		 */
		super(adminID, name);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}