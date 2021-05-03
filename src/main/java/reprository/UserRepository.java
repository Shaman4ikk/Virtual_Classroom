package reprository;

import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    public static List<User> arr;

    //Реализация паттерна Singleton для списка
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

    public static void checkNull() {
        arr.removeIf(u -> u.name == null);
    }

    //Выход из классрума
    public static void logOut(String name) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            for (User u : arr) {
                if (u.name.equals(name)) {
                    arr.remove(u);
                    break;
                }
            }
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
            logger.debug("Failed handUp/handDown: " + e);
        }

    }
}
