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
    private String endpointImg;
    public String Base64Image;
    private String endpointPort;

    public User(String base64Image) {
        Base64Image = base64Image;
    }

    public String getBase64Image() {
        return Base64Image;
    }

    public void setBase64Image(String base64Image) {
        Base64Image = base64Image;
    }


    public User(int id, String nombre, String email, String Contrasena) {
        this.id = id;
        Nombre = nombre;
        Email = email;
        Contrasena = Contrasena;

    }
    public User(String nombre, String email, String contrasena, String Imagen) {
        Nombre = nombre;
        Email = email;
        Contrasena = contrasena;
        Base64Image = Imagen;
    }
    public User(String email, String contrasena) {
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

    public String getEndpointImg() {
        return endpointImg;
    }

    public void setEndpointImg(String endpointImg) {
        this.endpointImg = endpointImg;
    }

    public String getEndpointPort() {
        return endpointPort;
    }

    public void setEndpointPort(String endpointPort) {
        this.endpointPort = endpointPort;
    }
}
