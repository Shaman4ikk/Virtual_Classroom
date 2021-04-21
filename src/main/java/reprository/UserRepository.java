package reprository;

import entity.User;

import javax.faces.context.FacesContext;
import java.util.ArrayList;

public class UserRepository {
    public static ArrayList<User> arr;

    public static ArrayList<User> getUsersList(){
        if(arr == null){
            arr = new ArrayList<>();
        }
        return arr;
    }

    public static void addToListUser(User name) {
        arr = getUsersList();
        if (!arr.contains(name)) {
            arr.add(name);
        }
    }

    public static void logOut(){
        FacesContext context = FacesContext.getCurrentInstance();
        User user = new User(context.getExternalContext().getSessionMap().get("name").toString(), false);
        int index = 0;
        for(User u: arr)  {
            if(u.name.equals(user.name)) {
                index = arr.indexOf(u);
            }
        }
        arr.remove(index);
        context.getExternalContext().invalidateSession();
    }

    public static void invertBool(User user){
        User user1;
        int index = 0;
        for(User u: arr) {
            if(u.name.equals(user.name)) {
                index = arr.indexOf(u);
            }
        }
        user1 = arr.get(index);
        user1.setHandUp(!user1.handUp);
        arr.set(index, user1);
    }
}
