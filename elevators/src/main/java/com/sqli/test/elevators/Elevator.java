package com.sqli.test.elevators;

public class Elevator {
	
	/**
	 *  the id of the elevator
	 */
	private String idElevator;

	/**
	 *  indicate the current floor of the elevator  
	 */
	
	private int currentFloor;
	
	/**
	 *  indicate the current state of the elevator
	 *   3 value possible  UP, DOWN or REST  
	 *  
	 */
	
	private String state;

	public Elevator(String idElevator, int currentFloor,String state) {
		super();
		this.idElevator = idElevator;
		this.currentFloor = currentFloor;
		this.state = state;
	}
	
	public Elevator(String idElevator, int currentFloor) {
		super();
		this.idElevator = idElevator;
		this.currentFloor = currentFloor;
	}
	
	public Elevator(String idElevator) {
		super();
		this.idElevator = idElevator;
		
	}

	public String getIdElevator() {
		return idElevator;
	}

	public void setIdElevator(String idElevator) {
		this.idElevator = idElevator;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idElevator == null) ? 0 : idElevator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elevator other = (Elevator) obj;
		if (idElevator == null) {
			if (other.idElevator != null)
				return false;
		} else if (!idElevator.equals(other.idElevator))
			return false;
		return true;
	}

	
	
	
	
	
	
	
}
