package com.jdcasasmoviles.appvideo.DataModel.Models;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("name")
    private String name;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("img")
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
