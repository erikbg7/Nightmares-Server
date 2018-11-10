package edu.upc.dsa;

import java.util.List;

public interface GameManager {

    ////////////  ITEM METHODS  //////////////
    public void addItem(int id, String name, int type, int strength, int cadency, int life);
    public void addItem(Item t);
    public Item getItem(int i);
    public List<Item> findAllItems();
    public void deleteItem(int id);
    public void updateItem(Item t);
    public int sizeItems();

    ////////////  MAP METHODS  //////////////

    ////////////  AUTHENTICATION METHODS  //////////////
    public void addUser(int id, String name, String password);
    public void addUser(User u);
    public User getUser(int i);
    public List<User> findAllUsers();
    public void deleteUser(int id);
    public void updateUser(User u);
    public int sizeUsers();

}
