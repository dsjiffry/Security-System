package auth_util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * IdAuthentication
 */
public class IdAuthentication implements AuthObject{

    private User user;

    private String userId;

    private List<User> users;

    public IdAuthentication(String userID)
    {
        //Create Dummy users
        User usr1 = new User("USR001", Arrays.asList(1, 2, 3));
        User usr2 = new User("USR002", Arrays.asList(1, 2, 3, 4, 5));
        User usr3 = new User("USR003", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        users = new ArrayList<User>();
        users.add(usr1);
        users.add(usr2);
        users.add(usr3);
        
        this.userId = userID;

    }

    

    @Override
    public boolean authenticate() {
        
        for(int i = 0 ; i < this.users.size() ; i++ )
        {
            if(users.get(i).getUserId() == this.userId)
            {
                this.user = users.get(i);
                return true;
            }
        }

        return false;
    }



    @Override
    public User getUser() {
        // TODO Auto-generated method stub
        return this.user;
    }


    
}