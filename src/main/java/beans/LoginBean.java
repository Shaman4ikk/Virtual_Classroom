package beans;

import entity.User;
import reprository.UserRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginBean")
public class LoginBean {
    private String name;

    public String getInputText() {
        return name;
    }

    public void setInputText(String name) {
        this.name = name;
    }

    public static String nextPage() {
        return "/views/secondPage.xhtml";
    }

    public String getName(){
        return name;
    }

    //Выход из классрума
    public String logOut(){
       return "mainPage";
    }
}
