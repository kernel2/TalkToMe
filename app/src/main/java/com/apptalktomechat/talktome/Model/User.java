package com.apptalktomechat.talktome.Model;

public class User {

    private String id;
    private String username;
    private String image;

    public User(String id, String image, String username) {
        this.id = id;
        this.username = username;
        this.image = image;
    }

    public User(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
