package auth_util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * BarcodeAuhentication
 */
public class BarcodeAuhentication implements AuthObject {


    private User user;

    private List<Integer> barcode;

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(List<Integer> barcode) {
        this.barcode = barcode;
    }

    private List<User> users;

 
	HashMap<List<Integer>, User> barcode_user;


    public BarcodeAuhentication(List<Integer> p_barcode)
    {
         //Create Dummy users
         User usr1 = new User("USR001", Arrays.asList(1, 2, 3));
         User usr2 = new User("USR002", Arrays.asList(1, 2, 3, 4, 5));
         User usr3 = new User("USR003", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
 
         users = new ArrayList<User>();
         users.add(usr1);
         users.add(usr2);
         users.add(usr3);
         
         this.barcode = p_barcode;

        // @SuppressWarnings("serial")
        this.barcode_user = new HashMap<List<Integer>, User>()
        {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            // | || | || | || | would be represent in binary 1 for bar 0 for space

            {
                put(Arrays.asList(1, 0 , 1, 0, 1, 0, 1, 0, 1, 0), usr1);
                put(Arrays.asList(1, 1 , 1, 0, 1, 0, 1, 0, 1, 0), usr2);
                put(Arrays.asList(1, 1 , 1, 0, 1, 0, 1, 0, 1, 1), usr3);
            }
        };



    }

    @Override
    public boolean authenticate() {
        for (Map.Entry<List<Integer>, User> entry : this.barcode_user.entrySet())
        {
            if(this.barcode.equals(entry.getKey()) )
            {
                this.user = entry.getValue();
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