package com.jdcasasmoviles.appvideo.Views;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.R;
import com.jdcasasmoviles.appvideo.ViewModel.VMEditarUsuario;
import java.io.File;
import java.io.IOException;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class UsuarioEditar extends AppCompatActivity {
    Bitmap bitmap = null;
    String APP_DIRECTORY = "MyPictureApp/";
    String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int SELECT_PICTURE = 300;
    private final  int PHOTO_CODE = 200;
    private final int MY_PERMISSIONS = 123;
    String mPath;
    float   anchoIServidor=640;
    final int  REQUEST_CODE_PERMISO=777;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_usuario_editar );
        try{
            configView();
        }
        catch (Exception e) {
            CUView.botonLog(e);
        }

    }

    private void configView(){
        CUView.ContextAEditarUsuario=this;
        VMEditarUsuario.et_name=(TextInputEditText) findViewById( R.id.et_name );
        VMEditarUsuario.et_username=(TextInputEditText) findViewById( R.id.et_username );
        VMEditarUsuario.et_biography=(TextInputEditText) findViewById( R.id.et_biography );
        VMEditarUsuario.iv_guardar=(ImageView) findViewById( R.id.iv_guardar );
        VMEditarUsuario.iv_cerrar=(ImageView) findViewById( R.id.iv_cerrar );
        VMEditarUsuario.iv_camara=(ImageView) findViewById( R.id.profileActivityImage );
        VMEditarUsuario.bt_cambiar_foto=(Button) findViewById( R.id.bt_cambiar_foto );
        VMEditarUsuario.imagenUsuario=CUView.usuarioPrincipal.getProfilePicture();
        CUView.hackFixHintsForMeizu(CUView.ContextAEditarUsuario,VMEditarUsuario.et_name,VMEditarUsuario.et_username,VMEditarUsuario.et_biography);
        VMEditarUsuario.setearDatodUsuarios();
        botonGuardar();
        botonCerrar();
        botoncargarImagen();
    }

    private void botoncargarImagen() {
        VMEditarUsuario.bt_cambiar_foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                    if(forzaPermisoALaFuerza())  muestraOpciones();
                    else forzaPermisoALaFuerza();
                }
                CUView.ultimoClick= SystemClock.elapsedRealtime();

            }
        });
    }

    private void botonGuardar() {
        VMEditarUsuario.iv_guardar.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                    VMEditarUsuario.botonGuardarCambios();
                }
                CUView.ultimoClick= SystemClock.elapsedRealtime();
            }
        } );
    }

    private void botonCerrar() {
        VMEditarUsuario.iv_cerrar.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                    CUView.botonCerrar(CUView.ContextAEditarUsuario);
                }
                CUView.ultimoClick= SystemClock.elapsedRealtime();
            }
        } );
    }
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////PERMISOS/////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public boolean  forzaPermisoALaFuerza(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission( WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) &&
                    (checkSelfPermission( CAMERA ) != PackageManager.PERMISSION_GRANTED)
            ) {//NO hay permiso y se pide mediante un dialogo
                requestPermissions( new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, REQUEST_CODE_PERMISO );

                if ((shouldShowRequestPermissionRationale( WRITE_EXTERNAL_STORAGE )) ||
                        (shouldShowRequestPermissionRationale( CAMERA ))
                )
                {
                    // true si no hay permiso
                    return  false;

                } else { // false  si  hay permiso
                    return false;
                }
            } else {
                return true;
            }
        }
        else  {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISO: {
                if(grantResults.length == 2 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                )
                {
                    Toast.makeText(CUView.ContextAEditarUsuario, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CUView.ContextAEditarUsuario, "El permiso denegadoo ", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////OPCIONES DE ELEGIR IMAGEN///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private void muestraOpciones() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(CUView.ContextAEditarUsuario,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }
                else if(option[which] == "Elegir de galeria"){
                   Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), SELECT_PICTURE);
                }
                else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    ///////////////////////////////////OPCION CAMARA//////////////////////////////////
    public  void openCamera() {
        File file = new File( Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();
        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();
        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";
            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;
            File newFile = new File(mPath);
            Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));

            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            startActivityForResult(intent, PHOTO_CODE);
        }
    }
    ////////////////////////onActivityResult//////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(CUView.ContextAEditarUsuario,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });
                    bitmap = BitmapFactory.decodeFile(mPath);
                    VMEditarUsuario.imagenUsuario = VMEditarUsuario.getStringImage(VMEditarUsuario.redmensionaImagen(bitmap,anchoIServidor));
                    //tamaño para mostrar en activity
                    bitmap=Bitmap.createScaledBitmap(bitmap, 300,300, true);
                    CUView.imagenCircular(CUView.ContextAEditarUsuario,VMEditarUsuario.iv_camara,bitmap);
                    break;
                case SELECT_PICTURE:
                    Uri filepath=data.getData();
                    try {
                        bitmap= MediaStore.Images.Media.getBitmap( getContentResolver(),filepath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    VMEditarUsuario.imagenUsuario = VMEditarUsuario.getStringImage(VMEditarUsuario.redmensionaImagen(bitmap,anchoIServidor));
                    //tamaño para mostrar en activity
                    bitmap=Bitmap.createScaledBitmap(bitmap, 500,500, true);
                    CUView.imagenCircular(CUView.ContextAEditarUsuario,VMEditarUsuario.iv_camara,bitmap);
                    break;
            }
        }
    }

}
