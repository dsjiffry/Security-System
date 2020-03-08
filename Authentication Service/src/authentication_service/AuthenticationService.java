package authentication_service;

public interface AuthenticationService 
{
	
	public boolean checkUserID(String userID);
	public int[] getAccessableFloors(String userID);

}
