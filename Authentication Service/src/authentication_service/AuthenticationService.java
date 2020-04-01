package authentication_service;

import java.util.List;

import auth_util.AuthObject;

public interface AuthenticationService 
{
	
	public boolean authenticate(AuthObject authObject);
	public List<Integer> getAccessableFloors();
	public void securityAlert(String msg);
	public boolean setAuthObject(AuthObject authObject);
}
