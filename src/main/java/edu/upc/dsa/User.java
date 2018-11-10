package edu.upc.dsa;

public class User {
    int id;     // identification number
    String name;  // username
    String password;       //user password (hash)
    static int lastId;

    public User() {this.setId(lastId++);}

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        User.lastId = lastId;
    }
}
