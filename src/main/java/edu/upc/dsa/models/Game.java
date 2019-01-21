package edu.upc.dsa.models;

public class Game {
    int ID;
    int userid;
    String username;
    int score;
    int kills;
    int duration;


    public Game() {}

    public Game(int ID, int userid, String username, int score, int kills, int duration) {
        this.ID = ID;
        this.userid = userid;
        this.username = username;
        this.score = score;
        this.kills = kills;
        this.duration = duration;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}