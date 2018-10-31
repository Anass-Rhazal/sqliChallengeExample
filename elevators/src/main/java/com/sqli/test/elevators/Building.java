package com.sqli.test.elevators;

public class Building {
	
	private int numberOfFloors;
	private Elevators ListElevators;
	
	ElevatorsServiceImpl  ElevatorsImpl=new  ElevatorsServiceImpl();
	

    /**
     * @param numberOfFloors: the number of floors in the building
     * @param elevators: an array of descriptions of the elevators of the building. 
     *                   A description has the following format "[elevator_id]:[elevator_current_floor]".
     */
    public Building(int numberOfFloors, String... elevators) {
    		
    		this.numberOfFloors=numberOfFloors;
    		ListElevators=new Elevators();
    	for(String  el:elevators) {
    		
    		String [] parts =el.split(":");
    		
    		String idElevator=parts[0];
    		int currentPosition = Integer.parseInt(parts[1]);
    		
    		Elevator e= new Elevator(idElevator,currentPosition,"REST");
    		
    		ListElevators.getListOfElevators().add(e);
    		
    	}	
    }
    
    
    

    public int getNumberOfFloors() {
		return numberOfFloors;
	}




	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}




	public Elevators getListElevators() {
		return ListElevators;
	}




	public void setListElevators(Elevators listElevators) {
		ListElevators = listElevators;
	}




	/**
     * Request an elevator at floor zero.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator() {
       
    	Elevator closerElevator = ElevatorsImpl.closerElevatorToRequestedFloor(numberOfFloors, ListElevators);
    	
    	
    	return closerElevator.getIdElevator() ;
    }

    /**
     * Request an elevator at floor indicate by the {@code floor} param.
     * @param floor : the floor where the request is made.
     * @return the id of the elevator that should serve the request.
     */
//    public String requestElevator(int floor) {
//        throw new UnsupportedOperationException("Still to be implemented");
//    }

    /**
     * Request the elevator with the id {@code elevatorId} to stop at the floor indicated by the {@code floor} param.
     * @param elevatorId : the id of the elevator to whom give the order.
     * @param floor : the floor at which the elevator should stop
     */
    public void stopAt(String elevatorId, int floor) {
    	   	
    	ElevatorsImpl.stopElevatorInFloor(elevatorId,this.ListElevators,floor);
    }

    /**
     * Move the elevator with the id {@code elevatorId} in the direction indicated by the {@code direction} param.
     * @param elevatorId : the id of the elevator to move.
     * @param direction : the direction to go. Can be "UP" or "DOWN".
     */
    public void move(String elevatorId, String direction) {
        
    	 	
    	if(direction.equals("UP")) ElevatorsImpl.moveElevatorToTop(elevatorId,this.ListElevators);
    	else if(direction.equals("DOWN")) ElevatorsImpl.moveElevatorToBottom(elevatorId,this.ListElevators);
    	
    	
    	
    }

}
