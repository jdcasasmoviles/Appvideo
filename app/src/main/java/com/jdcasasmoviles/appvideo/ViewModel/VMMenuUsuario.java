package com.jdcasasmoviles.appvideo.ViewModel;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones.ApiService;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.Models.Grabaciones;
import com.jdcasasmoviles.appvideo.ViewModel.Adapter.RecyclerAdapterGrabaciones;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VMMenuUsuario extends ViewModel {
  public static TextView tv_name,tv_username,tv_biografia,tv_followers,tv_followed,tv_viewa;
  public static ImageView iv_imagen_usuario;
  public static  Button bt_ediatr_usuario;
  public static Toolbar toolbar;
  public static CollapsingToolbarLayout collapsingToolbarLayout;
    public static RecyclerAdapterGrabaciones adapterGrabaciones;
    public  static RecyclerView rv_grabaciones;
    public static void setearDatodUsuarios(){
      VMMenuUsuario.tv_name.setText( CUView.usuarioPrincipal.getName());
      VMMenuUsuario.tv_username.setText( CUView.usuarioPrincipal.getUsername());
      VMMenuUsuario.tv_biografia.setText( CUView.usuarioPrincipal.getBiography());
      VMMenuUsuario.tv_followers.setText( CUView.usuarioPrincipal.getFollowers());
      VMMenuUsuario.tv_followed.setText( CUView.usuarioPrincipal.getFollowed());
      VMMenuUsuario.tv_viewa.setText( CUView.usuarioPrincipal.getViews());
      CUView.getStringABitmap(CUView.usuarioPrincipal.getProfilePicture(),CUView.ContextAMenuUsuario,VMMenuUsuario.iv_imagen_usuario);
      VMMenuUsuario.configToolbar();
    }


  public static  void configToolbar() {
    VMMenuUsuario.collapsingToolbarLayout.setExpandedTitleColor( Color.argb(0,0,0,0) );
    VMMenuUsuario.collapsingToolbarLayout.setTitle( "" +CUView.usuarioPrincipal.getName() );
    ((AppCompatActivity)CUView.ContextAMenuUsuario).setSupportActionBar(VMMenuUsuario.toolbar);
  }


  public static  void rvConfigGrabaciones() {
    VMMenuUsuario.rv_grabaciones.setHasFixedSize( true );
    VMMenuUsuario.rv_grabaciones.setLayoutManager(new LinearLayoutManager(CUView.ContextAMenuUsuario, LinearLayoutManager.VERTICAL, true));
    VMMenuUsuario.adapterGrabaciones=new RecyclerAdapterGrabaciones( CUView.GrabacionesList );
    VMMenuUsuario.rv_grabaciones.setAdapter( VMMenuUsuario.adapterGrabaciones);
  }

  public static  void loadGrabaciones(ApiService client) {
    Call<List<Grabaciones>> call = client.getJSON();
    call.enqueue(new Callback<List<Grabaciones>>() {
      @Override
      public void onResponse(Call<List<Grabaciones>> call, Response<List<Grabaciones>> response) {
        VMMenuUsuario.adapterGrabaciones.setData(response.body());
      }
      @Override
      public void onFailure(Call<List<Grabaciones>> call, Throwable t) {
        Log.d("TAG1", "Error: " + t.getMessage());
        Toast.makeText(CUView.ContextAMenuUsuario, "Error: " + t.getMessage() , Toast.LENGTH_SHORT).show();

      }
    });
  }


}
