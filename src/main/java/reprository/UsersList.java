package reprository;

import entity.User;

import javax.faces.context.FacesContext;
import java.util.ArrayList;

public class UsersList {
    public static ArrayList<User> arr;
    public int handUp;

    public static ArrayList<User> getUsersList(){
        if(arr == null){
            arr = new ArrayList<>();
        }
        return arr;
    }

    public static void addToListUser(User name) {
        arr = getUsersList();
        name.setHandUp(false);
        if (!arr.contains(name)) {
            arr.add(name);
        }
    }

    public static void logOut(){
        FacesContext context = FacesContext.getCurrentInstance();
        User user = new User(context.getExternalContext().getSessionMap().get("name").toString(), false);
        if(arr.size() == 1) {
            arr = null;
        }
        else {
            arr.remove(user);
        }
        context.getExternalContext().invalidateSession();
    }

    public static void invertBool(User user){
        User user1;
        int index = 0;
        for(User u: arr)
        {
            if(u.name.equals(user.name))
            {
                index = arr.indexOf(u);
            }
        }
        user1 = arr.get(index);
        user1.setHandUp(!user1.handUp);
        System.out.println(user1.handUp);
        arr.set(index, user1);
    }
}
