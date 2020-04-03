package auth_util;
import java.util.List;


/**
 * User
 */
public class User {

    private String userId;
    private  List<Integer> accessableFloors;
    private List<String> accessableDoors;

    public User(String userId,  List<Integer> accessableFloors, List<String> accessableDoors)
    {
        this.userId = userId;
        this.accessableFloors = accessableFloors;
        this.accessableDoors = accessableDoors;
    }

    public String getUserId()
    {
        return this.userId;
    }

    public  List<Integer> getAccessableFloors()
    {
        return this.accessableFloors;
    }
    
    public List<String> getAccessableDoors()
    {
    	return this.accessableDoors;
    }
    
}