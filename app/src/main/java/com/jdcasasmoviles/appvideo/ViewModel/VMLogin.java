package com.jdcasasmoviles.appvideo.ViewModel;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones.Peticiones;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.Models.Usuarios;

public class VMLogin extends ViewModel {;
        public static Button bt_login;
        public static EditText et_usuario,et_password;
        public static void  datosAcceso(){
            if(TextUtils.isEmpty( et_usuario.getText().toString().trim()))
                Toast.makeText( CUView.ContextALogin,"COLOCAR USUARIO", Toast.LENGTH_LONG).show();
            else if(TextUtils.isEmpty(et_password.getText().toString().trim())
            )
                Toast.makeText( CUView.ContextALogin,"COLOCAR PASSWORD \nDEBE SER MAYOR A 4 CARACTERES", Toast.LENGTH_LONG).show();
            else {
                CUView.usuarioPrincipal=new Usuarios(
                        "",
                        et_usuario.getText().toString().trim(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        et_password.getText().toString().trim()
                );
                Peticiones.autentificacion();
            }
        }
}
