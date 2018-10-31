package com.sqli.test.elevators;

import java.util.List;

public interface ElevatorsService {

	
	public Elevator closerElevatorToRequestedFloor(int requestedFloor,Elevators elevators);
	
	public List<Elevator> sortElevatorsByCurrentFloorDesc(Elevators elevators);
	
	public void moveElevatorToTop(String elevatorId,Elevators elevators);
	
	public void moveElevatorToBottom(String elevatorId,Elevators elevators);
	
	public void stopElevatorInFloor(String elevatorId,Elevators elevators, int floor);
	
	public int indexofRestedElevator(List<Elevator> elevators);
	
	
}
