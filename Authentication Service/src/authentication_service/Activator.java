package authentication_service;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator 
{

	@Override
	public void start(BundleContext context)
	{
		Hashtable<String, String> props = new Hashtable<String, String>();
		context.registerService(AuthenticationService.class.getName(), new AuthenticationServiceImpl(), props);
		System.out.println("Authentication Service started");
	}

	@Override
	public void stop(BundleContext context)
	{
		//Service is automatically unregistered
	}


}
