package beans;

import entity.User;
import reprository.UsersList;

import javax.faces.application.FacesMessage;
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

    public String setSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("name", name);
        User user = new User(context.getExternalContext().getSessionMap().get("name").toString());
        UsersBean.addToList(user);
        return "/views/secondPage.xhtml";
    }

    public String logOut(){
        UsersList.logOut();
        return "mainPage";
    }
}
