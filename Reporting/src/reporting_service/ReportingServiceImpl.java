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
	
	//Keeping a log of people who accessed each Doors
	private HashMap<String, String> doorAccessHistory = new HashMap<>();
	
	public static final String ANSI_YELLOW = "\u001B[33m";
	public final String ANSI_RESET = "\u001B[0m";

	
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
		printMsg("User: "+userID+" Entered Building at: "+currentDateTime);
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
		printMsg("User: "+userID+" Left the Building at: "+currentDateTime);
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
		printMsg("User: "+userID+" Accessed Elevator at: "+currentDateTime);
	}
	
	/**
	 * Will Log the user and the dateTime when called
	 * @param userID ID of the user
	 * @return If logging was successful then true
	 */
	@Override
	public void logDoorsAccess(String userID, String room) 
	{	
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String currentDateTime = dtf.format(now);
		
		//Storing the userID and the current DateTime
		doorAccessHistory.put(userID, currentDateTime);
		printMsg("User: "+userID+" Accessed Door "+room+" at: "+currentDateTime);
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
	
	/**
	 * Printing messages in yellow so that they can be identified
	 * @param msg message to print
	 */
	private void printMsg(String msg)
	{
		System.out.println(ANSI_YELLOW+msg+ANSI_RESET);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
