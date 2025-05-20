package model;

public class Users {
    private String username;
    private String passworld;

    public Users(String username, String passworld) {
        this.username = username;
        this.passworld = passworld;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassworld() {
        return passworld;
    }

    public void setPassworld(String passworld) {
        this.passworld = passworld;
    }


    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", passworld='" + passworld + '\'' +
                '}';
    }
}
