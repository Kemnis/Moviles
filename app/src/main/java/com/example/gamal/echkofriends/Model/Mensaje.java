package com.example.gamal.echkofriends.Model;

/**
 * Created by gamal on 22/05/2018.
 */

public class Mensaje {
    String name;
    String timeSend;
    String message;
    String endpoinImg;

    public Mensaje(String name, String timeSend, String message, String endpoinImg) {
        this.name = name;
        this.timeSend = timeSend;
        this.message = message;
        this.endpoinImg = endpoinImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndpoinImg() {
        return endpoinImg;
    }

    public void setEndpoinImg(String endpoinImg) {
        this.endpoinImg = endpoinImg;
    }
}
