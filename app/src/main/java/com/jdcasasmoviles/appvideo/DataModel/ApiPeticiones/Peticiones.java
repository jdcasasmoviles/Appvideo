package com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUCredenciales;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.Models.ApiRespuesta;
import com.jdcasasmoviles.appvideo.ViewModel.VMMenuUsuario;
import com.jdcasasmoviles.appvideo.Views.MenuPrincipal;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Peticiones {
    private static Retrofit retrofit;
   // private static final String BASE_URL="http://192.168.99.1/appvideo/v1/";
    public static String BASE_URL="http://jdcasasciviltopia.atwebpages.com/appvideo/v1/";
    private static HttpLoggingInterceptor loggingInterceptor;
    private static OkHttpClient.Builder httpClientBuilder;
    private static  ApiService apiService;

    private static void configClientRetrofit() {
        try{
            loggingInterceptor = new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
            apiService = retrofit.create(ApiService.class);
        }catch (Exception e){
            Toast.makeText(CUView.ContextAEditarUsuario, "Error: " + e.getMessage() , Toast.LENGTH_SHORT).show();

        }

    }
    ////////////////////////////////EDITAR USUARIO//////////////////////////////////////////////////////////////////////77
    public static void peticionEditarUsuario() {
        configClientRetrofit();
        if(apiService!=null){
            Call<ApiRespuesta> call = apiService.createTask( CUView.usuarioEditar );
            call.enqueue(new Callback<ApiRespuesta>() {
                @Override
                public void onResponse(Call<ApiRespuesta> call, Response<ApiRespuesta> response) {
                    recibeServidorEditarUsuario(response.body());
                }
                @Override
                public void onFailure(Call<ApiRespuesta> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                    Toast.makeText(CUView.ContextAEditarUsuario, "Error: " + t.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private static void recibeServidorEditarUsuario(ApiRespuesta respuestaServidor) {
        try {
            if(respuestaServidor.getMessage().equals("ok")){
                CUView.usuarioPrincipal=respuestaServidor.getData().get( 0 );
                CUCredenciales.guardarUsuario( CUView.ContextAEditarUsuario );
                VMMenuUsuario.setearDatodUsuarios();
                    Toast.makeText(CUView.ContextAEditarUsuario,respuestaServidor.getMessage(), Toast.LENGTH_SHORT).show();
                ((Activity)  CUView.ContextAEditarUsuario).finish();
            }else {
                Toast.makeText(CUView.ContextAEditarUsuario, respuestaServidor.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(CUView.ContextAEditarUsuario, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    /////////////////////////////LOGIN AUTENTIFICACION///////////////////////////////////////////////
    public static void autentificacion() {
        configClientRetrofit();
        Call<ApiRespuesta> call = apiService.createLogin( CUView.usuarioPrincipal);
        call.enqueue(new Callback<ApiRespuesta>() {
            @Override
            public void onResponse(Call<ApiRespuesta> call, Response<ApiRespuesta> response) {
                recibeServidorPeticionLogin(response.body());
            }
            @Override
            public void onFailure(Call<ApiRespuesta> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
                Toast.makeText(CUView.ContextALogin, "Error: " + t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void recibeServidorPeticionLogin(ApiRespuesta respuestaServidor) {
        try {
            if(respuestaServidor.getMessage().equals("ok")){
                CUView.usuarioPrincipal=respuestaServidor.getData().get( 0 );
                CUCredenciales.guardarUsuario( CUView.ContextALogin );
              irAMenuPrincipal();
            }else {
                Toast.makeText(CUView.ContextALogin, "SIN ACCESO", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(CUView.ContextALogin, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void irAMenuPrincipal() {
        ((Activity)  CUView.ContextALogin).finish();
        Intent intent = new Intent(  CUView.ContextALogin, MenuPrincipal.class);
        CUView.ContextALogin.startActivity(intent);
    }


}
