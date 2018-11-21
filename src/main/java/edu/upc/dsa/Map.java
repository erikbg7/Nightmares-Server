package edu.upc.dsa;

public class Map{

    public int idMap;
    public String descripcion;
    public int posicionX;
    public int posicionY;


    public Map(int idMap, String descripcion, int posicionX, int posicionY) {
        this.idMap = idMap;
        this.descripcion = descripcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }


    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }


}
