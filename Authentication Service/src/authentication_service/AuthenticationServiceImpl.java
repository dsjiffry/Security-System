package authentication_service;

import java.util.List;

import auth_util.AuthObject;
import auth_util.User;

/**
 * IdAuthImplementation
 */
public class AuthenticationServiceImpl implements AuthenticationService {


    User user;
    AuthObject authObject;

    public final String ANSI_RED = "\u001B[31m";
	public final String ANSI_RESET = "\u001B[0m";

   @Override
    public boolean setAuthObject(AuthObject authObject) {
	    if(authenticate(authObject))
	    {
	    	this.authObject = authObject;
	    	return true;
	    }
	    return false;
    }
    

    @Override
    public boolean authenticate(AuthObject authObject) {
        
        return authObject.authenticate();
    }

    @Override
    public List<Integer> getAccessableFloors() {
        
        return this.authObject.getUser().getAccessableFloors();
    }

    @Override
    public void securityAlert(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }



    
}