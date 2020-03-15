package com.jdcasasmoviles.appvideo.Views.Dialogo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUCredenciales;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.R;

public class DialogoApp {
    public static  void cargarDialogoCerrarSesion(){
        final AlertDialog.Builder builder = new AlertDialog.Builder( CUView.ContextAMenuUsuario,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setIcon( R.drawable.ic_user_nero);
        builder.setTitle("¿Estas seguro de cerrar sesion?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CUCredenciales.cerrarSesion( CUView.ContextAMenuUsuario);
                ((Activity) CUView.ContextAMenuUsuario).finish();
                dialog.cancel();
            }
        });
        //cancelar
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        if(!((Activity) CUView.ContextAMenuUsuario).isFinishing())
        {
            builder.show();
        }
    }

    public static void cargarDialogo(String mensaje){
        final AlertDialog.Builder builder = new AlertDialog.Builder(CUView.ContextAMenuUsuario);
        builder.setTitle("MENSAJE")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        builder.setIcon( R.drawable.ic_user_nero);
        builder.create().show();
        if(!((Activity) CUView.ContextAMenuUsuario).isFinishing())
        {
            builder.show();
        }
    }


}
