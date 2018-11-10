package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected List<Item> items;
    protected List<User> users;


    private GameManagerImpl() {
        this.items = new LinkedList<>();
        this.users = new LinkedList<>();

    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

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
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    public int sizeUsers() {
        return this.users.size();
    }
    public void addUser(User u) {
        this.users.add(u);
    }
    public void addUser(int id, String name, String password) {
        this.addUser(new User(id, name, password));
    }
    public User getUser(int i) {
        User u=null;
        if (i<this.users.size()) u = this.users.get(i);
        return u;
    }
    public List<User> findAllUsers() {
        return this.users;
    }
    @Override
    public void deleteUser(int id) {
        this.users.remove(id);

    }
    @Override
    public void updateUser(User user) {
        User u = this.getUser(user.getId());
        u.setId(user.getId());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
    }

}
