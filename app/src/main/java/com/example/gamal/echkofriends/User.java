package com.example.gamal.echkofriends;

import android.widget.ImageView;

import com.google.gson.Gson;

/**
 * Created by Gama on 14/05/2018.
 */

public class User {
    private int id;
    private String Nombre;
    private String Contraseña;
    private ImageView Perfil;

    public User(int id, String nombre, String contraseña, ImageView image) {
        this.id = id;
        Nombre = nombre;
        Contraseña = contraseña;
        Perfil = image;
    }
    public User(String nombre, String contraseña, ImageView image) {
        Nombre = nombre;
        Contraseña = contraseña;
        Perfil = image;
    }
    public User(String nombre, String contraseña) {
        Nombre = nombre;
        Contraseña = contraseña;
    }
    public User()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public ImageView getPerfil() {
        return Perfil;
    }

    public void setPerfil(ImageView perfil) {
        Perfil = perfil;
    }

    public String toJSON()
    {
        return new Gson().toJson(this);
    }
}
