package auth_util;


public interface AuthObject {

    public boolean authenticate();
    public User getUser();
    
}