package entity;

public class User {
    public String name;
    public boolean handUp;


    public User(String name, boolean handUp) {
        this.handUp = handUp;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public boolean isHandUp() {
        return handUp;
    }

    public void setHandUp(boolean handUp) {
        this.handUp = handUp;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
