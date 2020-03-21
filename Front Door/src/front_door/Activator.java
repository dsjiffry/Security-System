package front_door;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

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
	 * Will take the User ID and ask the authentication service if it's valid.
	 * If valid then the front door will be unlocked.
	 */
	public void start(BundleContext context) throws Exception
    {
        m_context = context;

        // Create a service tracker objects
        auth_tracker = new ServiceTracker<>(m_context,AuthenticationService.class.getName(),null);
        report_tracker = new ServiceTracker<>(m_context,ReportingService.class.getName(),null);
        
        auth_tracker.open();
        

        try
        {
        	while (true)
            {
                System.out.print("\n"
                						+ "1. User is Entering the Building \n"
										+ "2. User is Leaving the Building	\n"
										+ "Select the Action Required: "
				);
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				int selection = Integer.valueOf(in.readLine());
				System.out.println();
				
				switch(selection)
				{
					case 1:
							entranceService();
							break;
					case 2:
							leavingService();
							break;
					default:
						System.out.println("Incorrect Value");
				}
            }
        } catch (Exception ignored) { }
    }
	
	
	/**
	 * This handles entering to the Building
	 * @param userID the ID of user who is entering
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void entranceService() throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Ask the user to enter a word.
        System.out.print("Enter ID: ");
        String userID = in.readLine();

        AuthenticationService authenticate = (AuthenticationService) m_context.getService(auth_tracker.getServiceReference());

        // If the user entered a blank line, then exit
        if (userID.length() == 0)
        {
            return;
        }
        else if (authenticate == null)
        {
            System.out.println("Authentication Service not found.");
        }
        // Otherwise print whether the word is correct or not.
        else if (authenticate.checkUserID(userID))
        {
      
            report_tracker.open();
            ReportingService report = (ReportingService) m_context.getService(report_tracker.getServiceReference());
            
            //If this returns false we have a Security Issue
            if(!report.logUserID(userID))
            {
            	authenticate.securityAlert("User is attempting to re-enter the building without leaving.");
            	return;
            }
            System.out.println("ID Recognized, Opening door.");
            
        }
        else
        {
            System.out.println("Unrecognized ID.");
        }
		
	}
	
	
	/**
	 * This handles Leaving from the Building
	 * @param userID the ID of user who is Leaving
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void leavingService() throws IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Ask the user to enter a word.
        System.out.print("Enter ID: ");
        String userID = in.readLine();

        AuthenticationService authenticate = (AuthenticationService) m_context.getService(auth_tracker.getServiceReference());

        // If the user entered a blank line, then exit
        if (userID.length() == 0)
        {
            return;
        }
        else if (authenticate == null)
        {
            System.out.println("Authentication Service not found.");
        }
        // Otherwise print whether the word is correct or not.
        else if (authenticate.checkUserID(userID))
        {
      
            report_tracker.open();
            ReportingService report = (ReportingService) m_context.getService(report_tracker.getServiceReference());
            
            //If this returns false we have a Security Issue
            if(!report.removeUserID(userID))
            {
            	authenticate.securityAlert("User is attempting to exit the building without entering.");
            	return;
            }
            System.out.println("ID Recognized, Opening door.");
            
        }
        else
        {
            System.out.println("Unrecognized ID.");
        }
		
	}
	
	
	
	
	

    public void stop(BundleContext context)
    {
    }
}