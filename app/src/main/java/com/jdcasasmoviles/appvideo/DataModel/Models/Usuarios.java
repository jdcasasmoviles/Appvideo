package com.jdcasasmoviles.appvideo.DataModel.Models;

public class Usuarios {
    private String name;
    private String username;
    private String biography;
    private String followers;
    private String followed;
    private String views;
    private String profilepicture;
    private String usernameprimero;
    private String password;

    //usuario principal
    public Usuarios(String name, String username, String biography, String followers, String followed, String views, String profilePicture,String password) {
        this.name = name;
        this.username = username;
        this.biography = biography;
        this.followers = followers;
        this.followed = followed;
        this.views = views;
        this.profilepicture = profilePicture;
        this.password=password;
    }
    //usuario editar
    public Usuarios(String name, String userName, String biography, String profilePicture,String usernameprimero) {
        this.name = name;
        this.username = userName;
        this.biography = biography;
        this.profilepicture = profilePicture;
        this.usernameprimero=usernameprimero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getProfilePicture() {
        return profilepicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilepicture = profilePicture;
    }

    public String getUsernameprimero() {
        return usernameprimero;
    }

    public void setUsernameprimero(String usernameprimero) {
        this.usernameprimero = usernameprimero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
