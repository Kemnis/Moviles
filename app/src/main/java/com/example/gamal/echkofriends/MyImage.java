package com.example.gamal.echkofriends;

/**
 * Created by Gama on 14/05/2018.
 */

public class MyImage {
    private String m_name;
    private String m_path;

    public MyImage(String m_name, String m_path) {
        this.m_name = m_name;
        this.m_path = m_path;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_path() {
        return m_path;
    }

    public void setM_path(String m_path) {
        this.m_path = m_path;
    }
}
