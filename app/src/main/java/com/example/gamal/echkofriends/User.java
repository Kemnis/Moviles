package com.example.gamal.echkofriends;

import android.widget.ImageView;

import com.google.gson.Gson;

/**
 * Created by Gama on 14/05/2018.
 */

public class User {
    private int id;
    private String Nombre;
    private String Email;
    private String Contrasena;

    public User(int id, String nombre, String email, String Contrasena) {
        this.id = id;
        Nombre = nombre;
        Email = email;
        Contrasena = Contrasena;
    }
    public User(String nombre, String email, String contrasena) {
        Nombre = nombre;
        Email = email;
        Contrasena = contrasena;
    }
    public User()
    {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        Contrasena = Contrasena;
    }

    public String toJSON()
    {
        return new Gson().toJson(this);
    }
}
