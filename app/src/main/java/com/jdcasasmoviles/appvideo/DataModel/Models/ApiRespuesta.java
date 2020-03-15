package com.jdcasasmoviles.appvideo.DataModel.Models;
import java.util.ArrayList;
public class ApiRespuesta {
    private String message;
    private ArrayList<Usuarios> data;
    public ApiRespuesta(String message, ArrayList<Usuarios> data) {
        this.setMessage( message );
        this.setData( data );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Usuarios> getData() {
        return data;
    }

    public void setData(ArrayList<Usuarios> data) {
        this.data = data;
    }
}
