package edu.upc.dsa;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParameterList;
import edu.upc.dsa.exceptions.NameAlreadyInUseException;
import edu.upc.dsa.management.FactorySession;
import edu.upc.dsa.management.Session;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Item> items;
    protected List<User> users;

    private static Logger logger = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManagerImpl() {
        this.items = new LinkedList<>();
        this.users = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }


    public void add(Object o){
        switch (o.getClass().toString()){
            case "Item": this.items.add((Item) o); break;
            case "User": this.users.add((User) o); break;
        }
    }

    public void add(ParameterList pl){
    }

    public int size(Object o){
        int size = 0;
        switch (o.getClass().toString()){
            case "Item": size = this.items.size(); break;
            case "User": size = this.users.size(); break;
        }
        return size;
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

    /*public boolean checkLogin(String username, String password) throws UserNotFoundException {

        try{
            boolean ok = false;

            for(User u:users){
                if(u.getName().equals(username) && u.getPassword().equals(password)){ok = true;}
            }
            return ok;
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }*/
    //public boolean checkLogin(String username, String password) throws UserNotFoundException {}


    public void signUp(String username, String password) throws NameAlreadyInUseException {

        Session session = null;

        try{
            session = FactorySession.openSession();
            User user = new User(username, password, 0);
            boolean exists = session.checkNameAlreadyInUse(User.class, username);
            if(exists == false) {
                session.save(user);
            }
            else{
                throw new NameAlreadyInUseException();
            }
        }
        catch(Exception e){
            e.printStackTrace(); //"Error trying to open the session: " +e.getMessage());
            throw new NameAlreadyInUseException();
        }
        finally{
            session.close();
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



    public void clear() {
        instance = null;
    }

}
