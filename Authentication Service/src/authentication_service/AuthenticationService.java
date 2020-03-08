package authentication_service;

import java.util.List;

public interface AuthenticationService 
{
	
	public boolean checkUserID(String userID);
	public List<Integer> getAccessableFloors(String userID);

}
