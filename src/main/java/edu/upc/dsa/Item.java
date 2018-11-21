package edu.upc.dsa;

public class Item {

    int id;     //numero de identificacion
    String name;  //nombre del objeto
    int type;       //tipo de objecto: arma, vida, monedas
    int strength;   // fuerza del impacto
    int cadency;    // cadencia del impacto
    int life;       // unidades de salud que aumenta
    static int lastId = 1;

    public Item() {this.setId(lastId++);}

    public Item(int id, String name, int type, int strength, int cadency, int life) {
        this.id = lastId++;
        this.name = name;
        this.type = type;
        this.strength = strength;
        this.cadency = cadency;
        this.life = life;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCadency() {
        return cadency;
    }

    public void setCadency(int cadency) {
        this.cadency = cadency;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        Item.lastId = lastId;
    }
}
