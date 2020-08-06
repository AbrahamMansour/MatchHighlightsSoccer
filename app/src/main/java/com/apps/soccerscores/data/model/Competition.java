package com.apps.soccerscores.data.model;

public class Competition {
    private String name;
    private float id;
    private String url;


    // Getter Methods

    public String getName() {
        return name;
    }

    public float getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
