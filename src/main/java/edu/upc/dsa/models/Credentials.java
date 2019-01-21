package edu.upc.dsa.models;

public class Credentials {

    String name;  // username
    String password;       //user password (hash)


    public Credentials() {}


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
