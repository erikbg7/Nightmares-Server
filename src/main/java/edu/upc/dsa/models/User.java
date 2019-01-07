package edu.upc.dsa.models;

public class User {
    int ID;     // identification number
    String username;  // username
    String password;       //user password (hash)
    int score = 0;
    //static int lastId = 0;

    public User() {}//this.ID = lastId++;}

    public User(String username, String password, int score) {
        this();
        this.setUsername(username);
        this.setPassword(password);
        this.setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        User.lastId = lastId;
    }*/
}
