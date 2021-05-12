package beans;

import classes.User;
import entity.UserDTO;
import reprository.UserRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "usersBean")
public class UsersBean {

    public String name;

    public static void addToList(UserDTO name) {
        UserRepository.addToListUser(name);
    }

    public static void setHand() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserDTO user = new UserDTO(context.getExternalContext().getSessionMap().get("name").toString());
        UserRepository.invertBool(user);
    }

    public List<UserDTO> getUsersList() {
        System.out.println(UserRepository.getUsersList());
        return UserRepository.getUsersList();
    }

    public String getName() {
        FacesContext context = FacesContext.getCurrentInstance();
        name = context.getExternalContext().getSessionMap().get("name").toString();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
