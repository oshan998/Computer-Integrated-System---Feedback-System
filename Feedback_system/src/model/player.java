package model;

import java.io.Serializable;

/**
 *
 * Stores the data related to player
 * This the parent class of the admin class because both admin class and player class has set of similar variables
 * It has implemented serializable because to store data into an object stream that we can send over the network or save it as file or
 * store in database.
 * 
 * @author Oshan Lakshitha Nanayakkara
 * UOB Number - 2022802
 */
public class player implements Serializable {

	private static final long serialVersionUID = -6970460026835978519L;
	
	int staffID;
	String name;
	
	public player(int staffID, String name) {
		
		this.staffID = staffID;
		this.name = name;
	}
	
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}