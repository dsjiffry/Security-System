package reporting_service;

import java.util.HashMap;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class ReportingServiceImpl implements ReportingService
{
	
	//Keeping a log of the people currently inside the building
	private HashMap<String, String> peopleInsideTheBuilding = new HashMap<>();
	
	//Keeping a log of people who left the Building
	private HashMap<String, String> buildingVisitHistory = new HashMap<>();
	
	//Keeping a log of people who accessed the Elevator
	private HashMap<String, String> elevatorAccessHistory = new HashMap<>();

	
	/**
	 * Will Log the user and the dateTime when called
	 * @param userID ID of the user
	 * @return If logging was successful then true
	 */
	@Override
	public boolean logUserID(String userID) 
	{
		if(peopleInsideTheBuilding.containsKey(userID))
		{
			//How is the user re-entering the building without leaving first?
			return false;
		}
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String currentDateTime = dtf.format(now);
		
		//Storing the userID and the current DateTime
		peopleInsideTheBuilding.put(userID, currentDateTime);
		System.out.println("User: "+userID+" Entered Building at: "+currentDateTime);
		return true;
	}


	/**
	 * Removes a user from the log
	 * @param userID ID of the user
	 * @return If successfully removed then true
	 */
	@Override
	public boolean removeUserID(String userID) {
		if(!peopleInsideTheBuilding.containsKey(userID))
		{
			//How is the user exiting the building without entering first?
			return false;
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String currentDateTime = dtf.format(now);
		
		//Removing the User from the building
		peopleInsideTheBuilding.remove(userID);
		
		//Using dateTime as key since we need to keep a track of all the times a user leaves the building.
		buildingVisitHistory.put(currentDateTime, userID);
		System.out.println("User: "+userID+" Left the Building at: "+currentDateTime);
		return true;
	}
	
	
	/**
	 * Will Log the user and the dateTime when called
	 * @param userID ID of the user
	 * @return If logging was successful then true
	 */
	@Override
	public void logElevatorAccess(String userID) 
	{	
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String currentDateTime = dtf.format(now);
		
		//Storing the userID and the current DateTime
		elevatorAccessHistory.put(userID, currentDateTime);
		System.out.println("User: "+userID+" Accessed Elevator at: "+currentDateTime);
	}
	
	
	/**
	 * Check if someone has entered the Building through the front door
	 * @param userID the userID to check
	 * @return true if they came through the front door
	 */
	@Override
	public boolean isInsideBuilding(String userID)
	{
		if(peopleInsideTheBuilding.containsKey(userID))
		{
			return true;
		}
		return false;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
