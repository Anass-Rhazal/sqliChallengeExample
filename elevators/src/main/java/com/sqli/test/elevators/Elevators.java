package com.sqli.test.elevators;

import java.util.LinkedList;
import java.util.List;

public class Elevators {
	
	/**
	 *   Represent list of elevator 
	 */
	
	List<Elevator> listOfElevators;

	public Elevators() {
		listOfElevators=new LinkedList<>();
	}

	public List<Elevator> getListOfElevators() {
		return listOfElevators;
	}

	public void setListOfElevators(List<Elevator> listOfElevators) {
		this.listOfElevators = listOfElevators;
	}
	
	
	

}
