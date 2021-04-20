package beans;

import entity.User;
import reprository.UsersList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;

@ManagedBean(name = "usersBean")
public class UsersBean {

    public String name;
    public boolean handUp;

    public static void addToList(User name) {
        UsersList.addToListUser(name);
    }

    public static void setHand(){
        FacesContext context = FacesContext.getCurrentInstance();
        User user = new User(context.getExternalContext().getSessionMap().get("name").toString());
        UsersList.invertBool(user);
    }

    public ArrayList<User> getUsersList(){
        return UsersList.getUsersList();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
