package com.jdcasasmoviles.appvideo.Views;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones.ApiService;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.di.BaseApplication;
import com.jdcasasmoviles.appvideo.R;
import com.jdcasasmoviles.appvideo.ViewModel.VMMenuUsuario;
import com.jdcasasmoviles.appvideo.Views.Dialogo.DialogoApp;
import javax.inject.Inject;

public class MenuPrincipal extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    @Inject
    ApiService client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_principal );
        setUpDagger();
        configView();
    }

    private void setUpDagger(){
        ((BaseApplication)getApplication()).getRetrofitComponent().inject(this);
    }

    private void configView() {
        CUView.ContextAMenuUsuario=this;
        VMMenuUsuario.toolbar = (Toolbar) findViewById( R.id.toolbar );
        VMMenuUsuario.collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById( R.id.colapse_toolbar );
        VMMenuUsuario.tv_name=(TextView)findViewById( R.id.tv_name);
        VMMenuUsuario.tv_username=(TextView)findViewById( R.id.tv_username);
        VMMenuUsuario.tv_biografia=(TextView)findViewById( R.id.tv_biografia);
        VMMenuUsuario.tv_followers=(TextView)findViewById( R.id.tv_followers);
        VMMenuUsuario.tv_followed=(TextView)findViewById( R.id.tv_followed);
        VMMenuUsuario.tv_viewa=(TextView)findViewById( R.id.tv_views);
        VMMenuUsuario.bt_ediatr_usuario=(Button)findViewById( R.id.bt_editar_usuario );
        VMMenuUsuario.iv_imagen_usuario=(ImageView)findViewById( R.id.iv_imagen_usuario );
        VMMenuUsuario.rv_grabaciones = (RecyclerView) findViewById( R.id.rv_grabaciones);
        VMMenuUsuario.setearDatodUsuarios();
        VMMenuUsuario.rvConfigGrabaciones();
        VMMenuUsuario.loadGrabaciones(client);
        botonEditarUsuario();
        botonNavegacionFragmentos();
    }

    private void botonEditarUsuario() {
        VMMenuUsuario.bt_ediatr_usuario.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                    Intent intent = new Intent(  CUView.ContextAMenuUsuario, UsuarioEditar.class);
                    CUView.ContextAMenuUsuario.startActivity(intent);
                }
                CUView.ultimoClick= SystemClock.elapsedRealtime();
            }
        } );
    }

    //////////////////////////////////////////////NAGETION ABAJO/////////////////////////////////////////////////////////////////
    private void botonNavegacionFragmentos() {
        bottomNavigation = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        if (bottomNavigation != null) {
            bottomNavigation.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            return  opcionesMenu(item);
                        }
                    });
        }
    }

    private boolean opcionesMenu(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_1:
                // fragment
                break;
            case R.id.menu_2:
                DialogoApp.cargarDialogo("Este item no esta disponible\n en la  app demo");
                break;
            case R.id.menu_3:
                DialogoApp.cargarDialogo("Este item no esta disponible\n en la  app demo");

                break;
            case R.id.menu_4:
                DialogoApp.cargarDialogo("Este item no esta disponible\n en la  app demo");
                break;
            case R.id.menu_5:
                DialogoApp.cargarDialogoCerrarSesion();
                break;
        }
        return true;
    }
    /////////////////////////////MENU ARRIBA////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_scrolling, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_1) {
            if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                Intent intent = new Intent(  CUView.ContextAMenuUsuario, UsuarioEditar.class);
                CUView.ContextAMenuUsuario.startActivity(intent);
            }
            CUView.ultimoClick= SystemClock.elapsedRealtime();

            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
