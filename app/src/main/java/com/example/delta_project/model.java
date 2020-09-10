package com.example.delta_project;

public class model {
    String id, name, url,info;

    model() {

    }

    public model(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;

    }
    public model( String name, String url) {

        this.name = name;
        this.url = url;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
