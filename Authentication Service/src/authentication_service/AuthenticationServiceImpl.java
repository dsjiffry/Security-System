package authentication_service;

public class AuthenticationServiceImpl implements AuthenticationService
{
	
	String[] validIDs = {"USR001", "USR002", "USR003"};

	@Override
	public boolean checkUserID(String userID) 
	{
		
		for(String ValidID : validIDs)
		{
			if(ValidID.equals(userID))
			{
				return true;
			}
		}		
		return false;
	}
	
	

}
