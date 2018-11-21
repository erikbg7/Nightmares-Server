package edu.upc.dsa;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Item> items;
    protected List<User> users;
    protected List<Map> maps;

    private static Logger logger = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManagerImpl() {
        this.items = new LinkedList<>();
        this.users = new LinkedList<>();
        this.maps = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    /////////////////////////////////////////////////////////////////////////
    ////////////////////////////ITEMS////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    public int sizeItems() {
        return this.items.size();
    }
    public void addItem(Item t) {
        this.items.add (t);
    }
    public void addItem(int id, String name, int type, int strength, int cadency, int life) {
        this.addItem(new Item(id, name, type, strength, cadency, life));
    }
    public Item getItem(int i) {
        Item t=null;
        if (i<this.items.size()) t = this.items.get(i);
        return t;
    }
    public List<Item> findAllItems() {
        return this.items;
    }
    @Override
    public void deleteItem(int id) {
        this.items.remove(id);

    }
    @Override
    public void updateItem(Item p) {
        Item t = this.getItem(p.getId());
        t.setId(p.getId());
        t.setCadency(p.getCadency());
        t.setLife(p.getLife());
        t.setName(p.getName());
        t.setStrength(p.getStrength());
        t.setType(p.getType());
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////USERS////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    public int sizeUsers() {
        return this.users.size();
    }
    public void addUser(User u) {
        this.users.add(u);
    }
    public void addUser(String name, String password, int score) {
        this.users.add(new User(name, password, score));
    }
    public User getUser(int i) {
        User u=null;
        if (i<this.users.size()) u = this.users.get(i);
        return u;
    }

    public boolean checkLogin(String username, String password) {

        try{
            boolean ok = false;

            for(User u:users){
                if(u.getName().equals(username) && u.getPassword().equals(password)){ok = true;}
            }
            return ok;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public List<User> usersOrderedByScore() {
        //log.log(Level.INFO, "SORTING BY SALES Products before sorting: \n"+ products.toString());
        List<User> sortedList = new LinkedList<User>(users);
        Collections.sort(sortedList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getScore(), u1.getScore());
            }
        });
        //log.log(Level.INFO, "Products after sorting: \n"+ sortedList.toString());
        return sortedList;
    }

    public List<User> findAllUsers() {
        return this.users;
    }
    @Override
    public void deleteUser(int id) {
        this.users.remove(id);
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////MAPS////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    public int sizeMaps() {
        return this.maps.size();
    }
    public void addMap(Map m) {
        this.maps.add(m);
    }
    public void addMap(int id, String description, int pX, int pY) {
        this.addMap(new Map(id, description, pX, pY));
    }
    public Map getMap(int i) {
        Map m=null;
        if (i<this.maps.size()) m = this.maps.get(i);
        return m;
    }
    public List<Map> listOfMaps() {
        return this.maps;
    }

    @Override
    public void userPosition(int idUser, int idMap, int positionX, int positionY) {

    }
}
