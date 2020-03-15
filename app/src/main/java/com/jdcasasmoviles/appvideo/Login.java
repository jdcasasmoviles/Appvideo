package com.jdcasasmoviles.appvideo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones.Peticiones;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUCredenciales;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.ViewModel.VMLogin;

public class Login extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        CUView.ContextALogin=this;
        if(CUCredenciales.leerValor(this,"username")!=null && !CUCredenciales.leerValor(this,"username").equals("") &&
                CUCredenciales.leerValor(this,"password")!=null && !CUCredenciales.leerValor(this,"password").equals("")
        )
        {
            CUView.usuarioPrincipal=CUCredenciales.leerUsuario(  CUView.ContextALogin );
            Peticiones.irAMenuPrincipal();
        }
        else{
            setContentView( R.layout.activity_main );
            configView();
            botonMenu();
        }
    }

    private void configView() {
        VMLogin.bt_login=(Button)findViewById( R.id.bt_menu );
        VMLogin.et_usuario=(EditText)findViewById( R.id.et_usuario );
        VMLogin.et_password=(EditText)findViewById( R.id.et_password);

    }

    private void botonMenu() {
        VMLogin.bt_login.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                    VMLogin.datosAcceso();
                }
                CUView.ultimoClick= SystemClock.elapsedRealtime();
            }
        } );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
