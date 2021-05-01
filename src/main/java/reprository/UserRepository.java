package reprository;

import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    public static List<User> arr;

    //Реализация паттерна Singleton для
    public static List<User> getUsersList() {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        return arr;
    }

    public static void setUsersList(List<User> arr) {
        UserRepository.arr = arr;
    }

    //Добавление пользователей в список
    public static void addToListUser(User name) {
        try {
            arr = getUsersList();
            if (!arr.contains(name)) {
                arr.add(name);
            }
        } catch (Exception e) {
            logger.debug("Exeption: " + e);
        }

    }

    //Выход из классрума
    public static void logOut() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            User user = new User(context.getExternalContext().getSessionMap().get("name").toString(), false);
            int index = 0;
            for (User u : arr) {
                if (u.name.equals(user.name)) {
                    index = arr.indexOf(u);
                    break;
                }
            }
            arr.remove(index);
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {
            logger.debug("Failed logOut: " + e);
        }
    }

    //Поднятие и опускание руки
    public static void invertBool(User user) {
        try {
            User user1;
            int index = 0;
            for (User u : arr) {
                if (u.name.equals(user.name)) {
                    index = arr.indexOf(u);
                    break;
                }
            }
            user1 = arr.get(index);
            user1.setHandUp(!user1.handUp);
            arr.set(index, user1);
        } catch (Exception e) {
            logger.debug( "Failed handUp/handDown: " + e);
        }

    }
}
