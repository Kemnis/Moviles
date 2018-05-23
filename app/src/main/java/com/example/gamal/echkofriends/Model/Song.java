package com.example.gamal.echkofriends.Model;

/**
 * Created by gamal on 22/05/2018.
 */

public class Song {
    private String name;
    private String genero;
    private String endpointImage;

    public Song()
    {

    }

    public Song(String name, String genero, String endpointImage) {
        this.name = name;
        this.genero = genero;
        this.endpointImage = endpointImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEndpointImage() {
        return endpointImage;
    }

    public void setEndpointImage(String endpointImage) {
        this.endpointImage = endpointImage;
    }
}
