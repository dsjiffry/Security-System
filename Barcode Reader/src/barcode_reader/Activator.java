package barcode_reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import auth_util.AuthObject;
import auth_util.BarcodeAuhentication;
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
	 * Will take the barcode and ask the authentication service if it's valid.
	 * If valid then the front door will be unlocked.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception
    {
        m_context = context;

        // Create a service tracker objects
        auth_tracker = new ServiceTracker<>(m_context,AuthenticationService.class.getName(),null);
        report_tracker = new ServiceTracker<>(m_context,ReportingService.class.getName(),null);
        
        auth_tracker.open();
       
        while (true)
        {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			// Ask the user to scan barcode
	        System.out.print("\nScan Barcode: ");
	        String userID = in.readLine();
	        
	        List<Integer> barcode = new ArrayList<Integer>(); 
	        for(char c : userID.toCharArray())
	        {
	        	barcode.add(Character.getNumericValue(c));
	        }
	        
	        AuthenticationService authenticate = (AuthenticationService) m_context.getService(auth_tracker.getServiceReference());
	        AuthObject authObject = new BarcodeAuhentication(barcode);
	
	        // If the user entered a blank line, then exit
	        if (userID.length() == 0)
	        {
	            return;
	        }
	        else if (authenticate == null)
	        {
	            System.out.println("Authentication Service not found.");
	        }
	        // Otherwise print whether the barcode is correct or not.
	        else if (authObject.authenticate())
	        {
	      
	            report_tracker.open();
	            ReportingService report = (ReportingService) m_context.getService(report_tracker.getServiceReference());
	            
	            //If this returns false we have a Security Issue
	            if(!report.logUserID(authObject.getUser().getUserId()))
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
		
	}
	
	
	
	

    @Override
	public void stop(BundleContext context)
    {
    	System.out.println("Barcode service stopped");
    }
}