package front_door;

import authentication_service.AuthenticationService;
import reporting_service.ReportingService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;


public class Activator implements BundleActivator
{
    // Bundle's context.
    private BundleContext m_context = null;

    // Create a service tracker to monitor Authentication services.
	private ServiceTracker auth_tracker = null;
	// Create a service tracker to monitor Reporting services.
	private ServiceTracker report_tracker = null;

	public void start(BundleContext context) throws Exception
    {
        m_context = context;

        // Create a service tracker objects
        auth_tracker = new ServiceTracker<>(m_context,AuthenticationService.class.getName(),null);
        report_tracker = new ServiceTracker<>(m_context,ReportingService.class.getName(),null);
        
        auth_tracker.open();
        

        try
        {
            System.out.println("Enter a User ID to Authenticate.");
            String userID = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (true)
            {
                // Ask the user to enter a word.
                System.out.print("Enter ID: ");
                userID = in.readLine();

                AuthenticationService authenticate = (AuthenticationService) m_context.getService(auth_tracker.getServiceReference());

                // If the user entered a blank line, then
                // exit the loop.
                if (userID.length() == 0)
                {
                    break;
                }
                else if(userID.equals("STOP"))
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
                    System.out.println("ID Recognized, Opening door.");
                    report_tracker.open();
                    ReportingService report = (ReportingService) m_context.getService(report_tracker.getServiceReference());
                    
                    //If this returns false we have a Security Issue
                    if(!report.LogUserID(userID))
                    {
                    	//TODO: Send Security Alert
                    }
                    
                    
                }
                else
                {
                    System.out.println("Unrecognized ID.");
                }
            }
        } catch (Exception ex) { }
    }

    public void stop(BundleContext context)
    {
    }
}