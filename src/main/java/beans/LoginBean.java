package beans;

import entity.User;
import reprository.UserRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginBean")
//Страница логина
public class LoginBean {
    private String name;

    public String getInputText() {
        return name;
    }

    public void setInputText(String name) {
        this.name = name;
    }

    //установка сессии
    public String setSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("name", name);
        User user = new User(context.getExternalContext().getSessionMap().get("name").toString());
        UsersBean.addToList(user);
        return "/views/secondPage.xhtml";
    }

    public String getName(){
        return name;
    }

    //Выход из классрума
    public String logOut(){
        UserRepository.logOut();
        return "mainPage";
    }
}
