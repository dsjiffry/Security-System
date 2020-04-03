package doors_and_lights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import auth_util.AuthObject;
import auth_util.IdAuthentication;
import authentication_service.AuthenticationService;
import reporting_service.ReportingService;

public class Activator implements BundleActivator
{
    // Bundle's context.
    private BundleContext m_context = null;

    // Create a service tracker to monitor Authentication services.
	private ServiceTracker auth_tracker = null;
	// Create a service tracker to monitor Reporting services.
	private ServiceTracker report_tracker = null;

	
	/**
	 * Will take the user ID and ask the Authentication Service for the doors that the particular user is allowed to access.
	 * Then it turns on the lights for the rooms the user is allowed access to.
	 */
	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception
    {
        m_context = context;

        // Create service tracker objects
        auth_tracker = new ServiceTracker<>(m_context,AuthenticationService.class.getName(),null);
        report_tracker = new ServiceTracker<>(m_context,ReportingService.class.getName(),null);
        
        auth_tracker.open();
        report_tracker.open();
        

        try
        {
            String userID = "";
            String roomN = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                // Ask the user to enter a ID.
            	System.out.println("\nEnter a User ID to Access the Door.");
                System.out.print("Enter ID: ");
                userID = in.readLine();

                AuthenticationService authenticate = (AuthenticationService) m_context.getService(auth_tracker.getServiceReference());
                ReportingService report = (ReportingService) m_context.getService(report_tracker.getServiceReference());
                
                // If the user entered a blank line, then
                // exit the loop.
                if (userID.length() == 0)
                {
                    break;
                }
                else if (authenticate != null)
                {                	
                    AuthObject authObject = new IdAuthentication(userID);
                    authenticate.setAuthObject(authObject);
                	List<String> authorizedDoors = authenticate.getAccessableDoors();
                	
                	if(authorizedDoors == null)
                	{
                		//Means there is no such ID in the valid ID list.
                		System.out.println("Unrecognized ID");
                	}
                	else
                	{
                    	if(!report.isInsideBuilding(userID))
                    	{
                    		//This User did not enter through the front door.
                    		authenticate.securityAlert("User Not logged by front door.");
                    		continue;
                    	}
                		
                		
                    	System.out.print("Accessable Doors");
	                	for(String door : authorizedDoors)
	                	{
	                		System.out.print(" "+door+",");
	                	}
	                	System.out.println(" for user: "+userID);
	                	
	                	// Ask the user to confirm room number.
	                	System.out.println("\nConfirm the Door Number Please.");
	                    System.out.print("Enter Room Number: ");
	                    roomN = in.readLine();
	                    int check = 1;
	                    
	                    for(String door: authorizedDoors)
	                    {
	                    	if(door.equals(roomN)) 
	                    	{
	                    		System.out.println("\nDoor Unlocked for Room "+roomN+" and Lights turned on.\n");
	                    		check = 1;
	                    		report.logDoorsAccess(userID, roomN);
	                    		break;
	                    	}
	                    	else 
	                    	{	                    		
	                    		check = 0;
	                    	}
	                    }
	                    
	                    if(check == 0)
	                    {
	                    	System.out.println("\nAccess to Room not Permitted/Room does not Exist");
	                    }
	                 
                	}
                }
                else
                {
                	System.out.println("Authentication Service not found.");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void stop(BundleContext context)
    {
    	System.out.println("Doors and Lights Service has stopped");
    }
}