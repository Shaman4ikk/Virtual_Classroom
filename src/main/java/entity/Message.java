package entity;

import java.util.List;

public class Message {

    private String name;
    private boolean handStatus;
    private String action;
    private List<User> userSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHandStatus() {
        return handStatus;
    }

    public void setHandStatus(boolean handStatus) {
        this.handStatus = handStatus;
    }


    public List<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(List<User> userSet) {
        this.userSet = userSet;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
