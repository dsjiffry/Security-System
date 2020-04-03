package reporting_service;

public interface ReportingService 
{
	
	public boolean logUserID(String userID);
	public boolean removeUserID(String userID);
	public void logElevatorAccess(String userID);
	public void logDoorsAccess(String userID, String room);
	public boolean isInsideBuilding(String userID);
}
