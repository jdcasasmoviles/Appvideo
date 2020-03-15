package com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones;
import com.jdcasasmoviles.appvideo.DataModel.Models.ApiRespuesta;
import com.jdcasasmoviles.appvideo.DataModel.Models.Grabaciones;
import com.jdcasasmoviles.appvideo.DataModel.Models.Usuarios;
import com.jdcasasmoviles.appvideo.Views.UsuarioEditar;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("5e669952310000d2fc23a20e")
    Call<List<Grabaciones>>  getJSON();

    @POST("editarUsuario.php")
     Call<ApiRespuesta> createTask(@Body Usuarios usuarios);

    @POST("loginUsuario.php")
    Call<ApiRespuesta> createLogin(@Body Usuarios usuarios );
}