package com.jdcasasmoviles.appvideo.ViewModel;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.jdcasasmoviles.appvideo.DataModel.ApiPeticiones.Peticiones;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.Models.Usuarios;
import java.io.ByteArrayOutputStream;

public class VMEditarUsuario extends ViewModel {
    public static ImageView iv_guardar,iv_cerrar,iv_camara;
    public static TextInputEditText et_name,et_biography,et_username;
    public static Button bt_cambiar_foto;
    public static  String imagenUsuario;

    public static void botonGuardarCambios() {
        if(TextUtils.isEmpty(VMEditarUsuario.et_name.getText().toString().trim()))
            Toast.makeText( CUView.ContextAMenuUsuario,"NOMBRE  VACIO", Toast.LENGTH_LONG).show();
        else   if(TextUtils.isEmpty(VMEditarUsuario.et_username.getText().toString().trim()))
            Toast.makeText( CUView.ContextAMenuUsuario,"NOMBRE DE USUARIO  VACIO", Toast.LENGTH_LONG).show();
        else   if(TextUtils.isEmpty(VMEditarUsuario.et_biography.getText().toString().trim()))
            Toast.makeText( CUView.ContextAMenuUsuario,"BIOGRAFIA  VACIO", Toast.LENGTH_LONG).show();
      else {
            CUView.usuarioEditar=new Usuarios(
                    VMEditarUsuario.et_name.getText().toString().trim(),
            VMEditarUsuario.et_username.getText().toString().trim(),
            VMEditarUsuario.et_biography.getText().toString().trim(),
            VMEditarUsuario.imagenUsuario,
                    CUView.usuarioPrincipal.getUsername()
            );
              Peticiones.peticionEditarUsuario();
        }

    }

    //A continuacion el metodo para decodificar la imagen obtenida
    public static String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public static Bitmap redmensionaImagen(Bitmap bitmap, float anchoServidor){
        int ancho=bitmap.getWidth(); //W
        int  alto=bitmap.getHeight();   //H
        float factor=anchoServidor/ancho;   // f=w/W
        float altoServidor=factor*alto;    //h=f*H
        Matrix matrix=new Matrix();
        matrix.postScale(factor,factor);
        final Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, false);
        return   bitmap1;
    }

    public static void setearDatodUsuarios(){
        VMEditarUsuario.et_name.setText( CUView.usuarioPrincipal.getName());
        VMEditarUsuario.et_username.setText( CUView.usuarioPrincipal.getUsername());
        VMEditarUsuario.et_biography.setText( CUView.usuarioPrincipal.getBiography());
        CUView.getStringABitmap(CUView.usuarioPrincipal.getProfilePicture(),CUView.ContextAEditarUsuario,VMEditarUsuario.iv_camara);
    }



}