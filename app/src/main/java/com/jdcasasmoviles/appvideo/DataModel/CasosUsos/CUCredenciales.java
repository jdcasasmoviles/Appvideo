package com.jdcasasmoviles.appvideo.DataModel.CasosUsos;
import android.content.Context;
import android.content.SharedPreferences;

import com.jdcasasmoviles.appvideo.DataModel.Models.Usuarios;

import static android.content.Context.MODE_PRIVATE;
public class CUCredenciales {
    private static String PREFS_KEY = "app_video_preferencias";

    public static void guardarValor(Context context, String keyPref, String valor) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(keyPref, valor);
        editor.commit();
    }

    public static String leerValor(Context context, String keyPref) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, MODE_PRIVATE);
        return  preferences.getString(keyPref, "");
    }

    public static void guardarUsuario(Context context){
        guardarValor(context,"name", CUView.usuarioPrincipal.getName());
        guardarValor(context,"username", CUView.usuarioPrincipal.getUsername());
        guardarValor(context,"biography", CUView.usuarioPrincipal.getBiography());
        guardarValor(context,"followers", CUView.usuarioPrincipal.getFollowers());
        guardarValor(context,"followed", CUView.usuarioPrincipal.getFollowed());
        guardarValor(context,"views", CUView.usuarioPrincipal.getViews());
        guardarValor(context,"profilePicture", CUView.usuarioPrincipal.getProfilePicture());
        guardarValor(context,"password", CUView.usuarioPrincipal.getPassword());

    }

    public static Usuarios leerUsuario(Context context){
        Usuarios usuario=new Usuarios(
                leerValor(context,"name"),
                leerValor(context,"username"),
                leerValor(context,"biography"),
                leerValor(context,"followers"),
                leerValor(context,"followed"),
                leerValor(context,"views"),
                leerValor(context,"profilePicture"),
                leerValor(context,"password")
        );
        return  usuario;
    }

    public static void cerrarSesion(Context context){
          SharedPreferences preferences =context.getSharedPreferences(PREFS_KEY,Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = preferences.edit();
          editor.clear();
          editor.commit();
        guardarValor(context,"password","");
    }
}
