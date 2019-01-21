package edu.upc.dsa;

import edu.upc.dsa.exceptions.NameAlreadyInUseException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.management.FactorySession;
import edu.upc.dsa.management.Session;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Score;
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
    protected  List<Game> games;

    private static Logger logger = Logger.getLogger(GameManagerImpl.class.getName());

    private GameManagerImpl() {
        this.items = new LinkedList<>();
        this.users = new LinkedList<>();
        this.games = new LinkedList<>();
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
    //Login debe devolver un User o un int con la Id?
    public boolean logIn(String username, String password) throws UserNotFoundException{
        Session session = null;
        boolean log = false;


        try{
            session = FactorySession.openSession();
            int userID = session.checkLogIn(User.class, username, password);
            if(userID != -1) {
                System.out.println("HOLA AMOIGO  "+username +" " + password);
                log = true;
                //session.save(user);
            }
            else{
                System.out.println("ADIOS AMOIGO");
                throw new UserNotFoundException();

            }
        }
        catch(Exception e){
            e.printStackTrace(); //"Error trying to open the session: " +e.getMessage());
        }
        finally{
            session.close();
        }
        return log;
    }

    public void signUp(String username, String password) throws NameAlreadyInUseException {

        Session session = null;

        try{
            session = FactorySession.openSession();
            User user = new User(username, password, 0);
            //System.out.println(username + " " + user.getUsername() + user.getPassword()+"");
            boolean exists = session.checkNameAlreadyInUse(User.class, username);
            if(exists == false) {
                //System.out.println("HOLA AMOIGO  "+username +" " + user.getPassword());

                session.save(user);
            }
            else{
                //System.out.println("ADIOS AMOIGO");

                throw new NameAlreadyInUseException();
            }
        }
        catch(Exception e){
            e.printStackTrace(); //"Error trying to open the session: " +e.getMessage());
            throw new NameAlreadyInUseException();
            //throw new MySqlErrorException();
        }
        finally{
            session.close();
        }
    }

    public List<User> findAllUsers() {

        Session session = null;

        try{
            session = FactorySession.openSession();
            List<User> usersList = session.findAll(User.class);
            System.out.println(usersList.size());
            return usersList;
        }
        catch(Exception e){
            e.printStackTrace(); //"Error trying to open the session: " +e.getMessage());
            return null;
            //throw new MySqlErrorException();
        }
        finally{
            session.close();
        }
    }

    public List<Score> usersOrderedByScore() {

        List<User> sortedList = new LinkedList<User>(this.findAllUsers());
        Collections.sort(sortedList, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getScore(), u1.getScore());
            }
        });
        List<Score> scoresList = new LinkedList<>();
        for(User u: sortedList){
            scoresList.add(new Score(u.getUsername(), u.getScore()));
        }
        return scoresList;
    }
    @Override
    public void deleteUser(int id) {
        this.users.remove(id);
    }

    /////////////////////////////////////////////////////////////////////////
    ////////////////////////////GAMES////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    public List<Game> findAllGames(){
        return games;
    }
    public void addGame(int ID, int userid, String username, int score, int kills, int duration){
        this.addGame(new Game(ID, userid, username, score, kills, duration));
    }
    public void addGame(Game g){
        this.games.add (g);
    }






    public void clear() {
        instance = null;
    }

}
