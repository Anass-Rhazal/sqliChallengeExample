package com.sqli.test.elevators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ElevatorsServiceImpl implements ElevatorsService {

	/**
	 *  get the closer elevator to requested floor
	 */
	@Override
	public Elevator closerElevatorToRequestedFloor(int requestedFloor,Elevators elevators) {
		
	List<Elevator> sortedElevators = 	this.sortElevatorsByCurrentFloorDesc(elevators);
	
		for(Elevator e: sortedElevators) 
			    System.out.println(e.getIdElevator()+","+e.getCurrentFloor());
	 
		return sortedElevators.get(0) ;
	
		
		
	}
	

	/**
	 *  sort Elevator by current floor
	 */
	
	@Override
	public List<Elevator> sortElevatorsByCurrentFloorDesc(Elevators elevators) {
		
		
	  Collections.sort(elevators.getListOfElevators(), new Comparator<Elevator>() {
		     @Override
		     public int compare(Elevator e1, Elevator e2) {
		         
		    	 if(e1.getCurrentFloor() < e2.getCurrentFloor() ) return 1;
		    	 else  if(e1.getCurrentFloor() > e2.getCurrentFloor() ) return -1;
		    	 return 0;
		     }
		 });
		return elevators.getListOfElevators();
	}

	/**
	 *  move elevator to top floor
	 */

	@Override
	public void moveElevatorToTop(String elevatorId,Elevators elevators) {
		
		 int indexOfElevator=elevators.getListOfElevators().indexOf(new Elevator(elevatorId)); 
	    	
		  Elevator selectedElevator= elevators.getListOfElevators().get(indexOfElevator);
				
		
		  selectedElevator.setCurrentFloor(10);
		  selectedElevator.setState("UP");
	}


	/**
	 *  move elevator  to first floor
	 */
	
	@Override
	public void moveElevatorToBottom(String elevatorId,Elevators elevators) {
		
  int indexOfElevator=elevators.getListOfElevators().indexOf(new Elevator(elevatorId)); 
    	
  Elevator selectedElevator= elevators.getListOfElevators().get(indexOfElevator);
		
  selectedElevator.setCurrentFloor(1);
  selectedElevator.setState("DOWN");
		
	}

	
	/**
	 *  stop elevator at indicated floor  
	 */

	@Override
	public void stopElevatorInFloor(String elevatorId,Elevators elevators, int floor) {
		
		  int indexOfElevator=elevators.getListOfElevators().indexOf(new Elevator(elevatorId)); 
	    	
		  Elevator selectedElevator= elevators.getListOfElevators().get(indexOfElevator);
		
		  selectedElevator.setCurrentFloor(floor);
		  selectedElevator.setState("REST");
		
	}
	
	
	/**
	 *  get index of the rested elevator from list of elevators
	 */
	
	@Override
	public int indexofRestedElevator(List<Elevator> elevators) {
		
		for(Elevator e:elevators) 
			
			if(e.getState().equals("REST") ) 
			{
				int indexOfElevator=elevators.indexOf(e); 
				
				return indexOfElevator;
			}
		
		return -1;
		
	}

}
