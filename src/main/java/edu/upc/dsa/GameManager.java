package edu.upc.dsa;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParameterList;
import edu.upc.dsa.exceptions.NameAlreadyInUseException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

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
    public boolean logIn(String user, String pass) throws UserNotFoundException;
    public void signUp(String username, String password) throws NameAlreadyInUseException;
    public List<User> findAllUsers();
    public void deleteUser(int id);
    public int sizeUsers();


    void clear();
}
