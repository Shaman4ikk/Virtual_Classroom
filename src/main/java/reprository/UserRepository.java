package reprository;

import classes.User;
import entity.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    static final Logger logger = LogManager.getLogger(UserRepository.class);

    public static List<UserDTO> arr;

    //Реализация паттерна Singleton для списка
    public static List<UserDTO> getUsersList() {
        if (arr == null) {
            arr = new ArrayList<>();
        }
        return arr;
    }

    public static void setUsersList(List<UserDTO> arr) {
        UserRepository.arr = arr;
    }

    public static boolean checkUser(Object name) {
        boolean result = false;
        for (UserDTO u : arr) {
            if (u.getName().equals(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean login(String login, String password) {
        try {
            password = hashMD5(password);
            Session session = HibernateUtil.getSession().openSession();
            session.beginTransaction();
            User user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).add(Restrictions.eq("password", password)).uniqueResult();
            session.getTransaction().commit();
            UserDTO userDTO = new UserDTO(user.login, user.handUp);
            addToListUser(userDTO);
            return true;
        } catch (HibernateException | NullPointerException e) {
            System.out.println("CheckCatch");
            logger.debug("Exeption: " + e);
        }
        return false;
    }

    public static boolean register(String login, String password) {
        try {
            password = hashMD5(password);
            System.out.println(password);
            Session session = HibernateUtil.getSession().openSession();
            session.beginTransaction();
            User user = new User();
            user.setName(login);
            user.setPassword(password);
            user.setHandUp(false);
            session.save(user);
            session.getTransaction().commit();
            if (user.getName() != null && user.getPassword() != null) {
                UserDTO userDTO = new UserDTO(user.login, user.handUp);
                addToListUser(userDTO);
                return true;
            }
        } catch (HibernateException | NullPointerException e) {
            System.out.println("CheckCatch");
            logger.debug("Exeption: " + e);
        }
        return false;
    }

    //Добавление пользователей в список
    public static void addToListUser(UserDTO user) {
        try {
            arr = getUsersList();
            if (!arr.contains(user)) {
                arr.add(user);
            }
        } catch (Exception e) {
            logger.debug("Exeption: " + e);
        }
    }

    public static String hashMD5(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static void checkNull() {
        arr.removeIf(u -> u.getName() == null);
    }

    //Выход из классрума
    public static void logOut(String name) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            for (UserDTO u : arr) {
                if (u.getName().equals(name)) {
                    arr.remove(u);
                    break;
                }
            }
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {
            logger.debug("Failed logOut: " + e);
        }
    }

    public static void connect() {
        Session session = HibernateUtil.getSession().openSession();
        session.beginTransaction();
        session.getTransaction().commit();
    }

    //Поднятие и опускание руки
    public static void invertBool(UserDTO user) {
        try {
            UserDTO user1;
            int index = 0;
            for (UserDTO u : arr) {
                if (u.getName().equals(user.getName())) {
                    index = arr.indexOf(u);
                    break;
                }
            }
            user1 = arr.get(index);
            user1.setHandUp(!user1.isHandUp());
            arr.set(index, user1);
        } catch (Exception e) {
            logger.debug("Failed handUp/handDown: " + e);
        }
    }
}
