package com.app.usuario.recetas;

public class Receta {

    int id;
    String nombre;
    int tiempo;
    String ingredientes;

    public Receta(Integer id, String nombre, int tiempo, String ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
