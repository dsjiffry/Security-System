package reporting_service;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

	private static BundleContext context;

	@Override
	public void start(BundleContext context)
	{
		Hashtable<String, String> props = new Hashtable<String, String>();
		context.registerService(ReportingService.class.getName(), new ReportingServiceImpl(), props);
		System.out.println("Reporting Service started");
	}

	@Override
	public void stop(BundleContext context)
	{
		//Service is automatically unregistered
	}

}
