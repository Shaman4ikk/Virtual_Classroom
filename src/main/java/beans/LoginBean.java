package beans;

import reprository.UserRepository;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "loginBean")
public class LoginBean {
    private String login;
    private String password;

    public static String nextPage() {
        return "nextPage";
    }

    public String toRegPage(){
        return "regPage";
    }

    public String login(){
        if(UserRepository.login(login, password)){
             return nextPage();
        }
       return "mainPage";
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin(){
        return login;
    }

}
