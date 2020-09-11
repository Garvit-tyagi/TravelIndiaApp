package com.example.delta_project;

public class model {
    String id, name, url,info,stw;

    model() {

    }

    public model(String id, String name, String url,String info) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.info=info;

    }
    public model( String name, String url,String info) {

        this.name = name;
        this.url = url;
        this.info=info;

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

    public String getStw() {
        return stw;
    }

    public void setStw(String stw) {
        this.stw = stw;
    }
}
