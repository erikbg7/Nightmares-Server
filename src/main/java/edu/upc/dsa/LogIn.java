package edu.upc.dsa;

public class LogIn {

    String name;  // username
    String password;       //user password (hash)


    public LogIn() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
