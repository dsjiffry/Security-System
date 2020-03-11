package authentication_service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService
{
	
	public final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	
	@SuppressWarnings("serial")
	HashMap<String, List<Integer>> usersAndAccessableFloors = new HashMap< String, List<Integer> >()
	{{
		put("USR001", Arrays.asList(1, 2, 3));
		put("USR002", Arrays.asList(1, 2, 3, 4, 5));
		put("USR003", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));	
		
	}};

	/**
	 * Checks the user ID with the valid IDs in the system.
	 * @param userID The ID to check
	 * @return if ID is found to be valid then true
	 */
	@Override
	public boolean checkUserID(String userID) 
	{
		
		if(usersAndAccessableFloors.containsKey(userID))
		{
			return true;
		}
		return false;		
	}

	
	/**
	 * Checks the floors that are accessible by a particular User
	 * @param userID The ID of the user
	 * @return An array of accessible floor numbers
	 */
	@Override
	public List<Integer> getAccessableFloors(String userID) 
	{
		return usersAndAccessableFloors.get(userID);	
	}
	
	
	/**
	 * Output Alert messages if security violations are detected
	 * @param msg the message to output
	 */
	public void securityAlert(String msg)
	{
		System.out.println(ANSI_RED + msg + ANSI_RESET);
	}
	
	

}
