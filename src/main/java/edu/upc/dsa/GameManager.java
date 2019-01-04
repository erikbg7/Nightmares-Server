package edu.upc.dsa;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParameterList;
import edu.upc.dsa.exceptions.UserNotFoundException;

import java.util.List;

public interface GameManager {

    public void add(Object o);
    public void add(ParameterList pl);
    public int size(Object o);


    ////////////  ITEM METHODS  //////////////
    public void addItem(int id, String name, int type, int strength, int cadency, int life);
    public void addItem(Item t);
    public Item getItem(int i);
    public List<Item> findAllItems();
    public void deleteItem(int id);
    public void updateItem(Item t);
    public int sizeItems();

    ////////////  USER METHODS  //////////////
    public List<User> usersOrderedByScore();
    public void addUser(String name, String password, int score);
    public void addUser(User u);
    public User getUser(int i);
    public boolean checkLogin(String user, String pass) throws UserNotFoundException;
    public List<User> findAllUsers();
    public void deleteUser(int id);
    public int sizeUsers();


    ////////////  MAP METHODS  //////////////
    public void addMap(int id, String description, int pX, int pY);
    public void addMap(Map m);
    public Map getMap(int i);
    public List<Map> listOfMaps();
    public int sizeMaps();
    public void userPosition(int idUser, int idMap, int positionX, int positionY);

}