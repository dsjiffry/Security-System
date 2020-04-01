package auth_util;
import java.util.List;


/**
 * User
 */
public class User {

    private String userId;
    private  List<Integer> accessableFloors;

    public User(String userId,  List<Integer> accessableFloors)
    {
        this.userId = userId;
        this.accessableFloors = accessableFloors;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public  List<Integer> getAccessableFloors()
    {
        return this.accessableFloors;
    }
    
}