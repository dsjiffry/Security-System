package elevator_service;

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
	 * Will take the user ID and ask the Authentication Service for the floors that the particular user is allowed to access.
	 * Then it will unlock those floors only.
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
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                // Ask the user to enter a ID.
            	System.out.println("\nEnter a User ID to Access Floors.");
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
                	List<Integer> authorizedFloors = authenticate.getAccessableFloors();
                	
                	if(authorizedFloors == null)
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
                		
                		
	                	System.out.print("Unlocking floors:");
	                	for(int floor : authorizedFloors)
	                	{
	                		System.out.print(" "+floor+",");
	                	}
	                	System.out.println(" for user: "+userID);
	                	

	                    report.logElevatorAccess(userID);
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
    	System.out.println("Elevator Service has stopped");
    }
}