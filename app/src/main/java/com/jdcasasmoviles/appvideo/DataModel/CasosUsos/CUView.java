package com.jdcasasmoviles.appvideo.DataModel.CasosUsos;
import android.content.Context;

import com.jdcasasmoviles.appvideo.DataModel.Models.Grabaciones;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.jdcasasmoviles.appvideo.DataModel.Models.Usuarios;
import com.jdcasasmoviles.appvideo.R;
import java.util.Locale;

public class CUView {
    public  static Usuarios usuarioEditar,usuarioPrincipal;
    public static List<Grabaciones> GrabacionesList =new ArrayList<>(  );
    ///////////////CONTEXT Actividades//////////////////////////////
    public static Context ContextAMenuUsuario;
    public static Context ContextAEditarUsuario;
    public static Context ContextALogin;
    /////PARA EVITAR CLICK SEGUIDOS
    public static final long TIEMPO_MINIMO = 400; // Minimo de espera para click
    public static long ultimoClick = 0; // Fecha del Ultimo click capturado
  //  public static Credenciales credencialesUsuario;

    public static void botonCerrar(Context context) {
        ((Activity)context).finish();
    }

    public static void imagenCircular(Context context, ImageView iv_foto,Bitmap originalBitmap) {
        try{
            //creamos el drawable redondeado
            RoundedBitmapDrawable roundedDrawable =
                    RoundedBitmapDrawableFactory.create(context.getResources(), originalBitmap);
            //asignamos el CornerRadius
            roundedDrawable.setCornerRadius(originalBitmap.getHeight());
            iv_foto.setImageDrawable(roundedDrawable);
        }
        catch (Exception e) {
           // CUUsuarios.botonLog(e);
        }
    }

    public static void hackFixHintsForMeizu(Context context, TextInputEditText... editTexts) {
        try{
            String manufacturer = Build.MANUFACTURER.toUpperCase(Locale.US);
            if (manufacturer.contains("MEIZU")) {
                for (TextInputEditText editText : editTexts) {
                    editText.setHintTextColor(ContextCompat.getColor(context, R.color.color_hint_et));
                    editText.setHint(editText.getHint());
                }
            }
        }
        catch (Exception e){
           // CULog.botonLog(e);
        }
    }

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void botonLog(Exception e) {
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        System.out.println("ERRORRRRR   : \n"+errors.toString());
    }

    public static void getStringABitmap(String profilePicture, Context context, ImageView iv_foto) {
        if(!profilePicture.equals( null ) && !profilePicture.equals( "" )){
            byte [] decodedByte=Base64.decode(profilePicture,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
            CUView.imagenCircular( context,  iv_foto,bitmap);
        }
    }
}
