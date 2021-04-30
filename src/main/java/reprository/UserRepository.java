package reprository;

import entity.User;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository {
    public static ArrayList<User> arr;
    private static Logger logger = Logger.getLogger(UserRepository.class.getName());

    //Реализация паттерна Singleton для
    public static ArrayList<User> getUsersList(){
        if(arr == null){
            arr = new ArrayList<>();
        }
        return arr;
    }

    //Добавление пользователей в список
    public static void addToListUser(User name) {
        try {
            arr = getUsersList();
            if (!arr.contains(name)) {
                arr.add(name);
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exeption: " + e);
        }

    }

    //Выход из классрума
    public static void logOut(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            User user = new User(context.getExternalContext().getSessionMap().get("name").toString(), false);
            int index = 0;
            for(User u: arr)  {
                if(u.name.equals(user.name)) {
                    index = arr.indexOf(u);
                    break;
                }
            }
            arr.remove(index);
            context.getExternalContext().invalidateSession();
        } catch (Exception e){
            logger.log(Level.SEVERE, "Failed logOut: " + e);
        }
    }

    //Поднятие и опускание руки
    public static void invertBool(User user){
        try {
            User user1;
            int index = 0;
            for(User u: arr) {
                if(u.name.equals(user.name)) {
                    index = arr.indexOf(u);
                    break;
                }
            }
            user1 = arr.get(index);
            user1.setHandUp(!user1.handUp);
            arr.set(index, user1);
        } catch (Exception e){
            logger.log(Level.SEVERE, "Failed handUp/handDown: " + e);
        }

    }
}
