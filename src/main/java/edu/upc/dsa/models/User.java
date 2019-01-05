package edu.upc.dsa.models;

public class User {
    int id;     // identification number
    String name;  // username
    String password;       //user password (hash)
    int score = 0;
    static int lastId = 0;

    public User() {this.id = lastId++;}

    public User(String name, String password, int score) {
        this();
        this.setName(name);
        this.setPassword(password);
        this.setScore(score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {}

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
