package com.example.delta_project;

public class middleDisplayModel {
    private String id;
    private String url;
    private String name;
    private String info;
        middleDisplayModel(){

        }
    public middleDisplayModel(String id, String url, String name, String info) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
