package com.jdcasasmoviles.appvideo.ViewModel.Adapter;
import android.animation.Animator;
import android.net.Uri;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jdcasasmoviles.appvideo.DataModel.CasosUsos.CUView;
import com.jdcasasmoviles.appvideo.DataModel.Models.Grabaciones;
import com.jdcasasmoviles.appvideo.R;
import java.util.List;
import android.app.Activity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.VideoView;

public class RecyclerAdapterGrabaciones extends RecyclerView.Adapter<RecyclerAdapterGrabaciones.ViewHolder> {
    List<Grabaciones> grabacionesList;

    public RecyclerAdapterGrabaciones(List<Grabaciones> grabacionesList) {
        this.grabacionesList = grabacionesList;
    }

    @Override
    public RecyclerAdapterGrabaciones.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( CUView.ContextAMenuUsuario );
        View view = inflater.inflate( R.layout.cv_v_grabaciones, null );
        return new RecyclerAdapterGrabaciones.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterGrabaciones.ViewHolder holder, int position) {
        Grabaciones grabaciones = grabacionesList.get( position );
        holder.tv_nombre_usuario.setText( ""+grabaciones.getProfile().getName());
        holder.tv_nombre_grabacion_cabecera.setText(""+grabaciones.getDescripcion());
        holder.tv_nombre_grabacion.setText( ""+grabaciones.getDescripcion() );
        int valorEntero = (int)Math.floor(Math.random()*(15)+1);
        holder.tv_reproducciones.setText(valorEntero+" reproductions");
        holder.path=grabaciones.getRecord_video();
        cargaImagen( grabaciones.getProfile().getImg(), holder.iv_imagen_usuario  );
        cargaImagen( grabaciones.getPreview_img() , holder.iv_preview_img  );
    }


    private void cargaImagen(String rutaImagen,ImageView imageView){
        Glide.with(CUView.ContextAMenuUsuario)
                .load(rutaImagen)
                .error( R.drawable.ic_close )
                .centerCrop()
                .crossFade()
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return grabacionesList.size();
    }

    public void setData(List<Grabaciones> grabacionesList){
        this.grabacionesList.clear();
        this.grabacionesList = grabacionesList;
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Grabaciones> grabacionesList) {
        grabacionesList.addAll(grabacionesList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nombre_grabacion_cabecera,tv_nombre_usuario,tv_nombre_grabacion,tv_reproducciones,tv_titulo_like;
        ImageView iv_preview_img,iv_imagen_usuario,iv_play,iv_videocamara,iv_puntos,iv_like;
        private String path="";
        private static final String TAG = "VideoViewDemo";
        private VideoView mVideoView;
     private LottieAnimationView like;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            iv_preview_img = (ImageView) itemView.findViewById( R.id.iv_preview_img );
            iv_imagen_usuario = (ImageView) itemView.findViewById( R.id.iv_imagen_usuario );
            tv_nombre_usuario = (TextView) itemView.findViewById( R.id.tv_nombre_usuario );
            tv_nombre_grabacion_cabecera = (TextView) itemView.findViewById( R.id.tv_nombre_grabacion_cabecera );
            tv_nombre_grabacion = (TextView) itemView.findViewById( R.id.tv_nombre_grabacion );
            tv_reproducciones = (TextView) itemView.findViewById( R.id.tv_reproducciones );
            tv_titulo_like = (TextView) itemView.findViewById( R.id.tv_titulo_like );
            iv_videocamara=(ImageView) itemView.findViewById(R.id.iv_videocamara);
            iv_puntos=(ImageView) itemView.findViewById(R.id.iv_puntos);
            iv_play=(ImageView) itemView.findViewById(R.id.iv_play);
            mVideoView = (VideoView) itemView.findViewById(R.id.vv_video);
            iv_like=(ImageView)itemView.findViewById( R.id.iv_like );
            like=(LottieAnimationView)itemView.findViewById(R.id.animation_view_like);
           botonLike();
            mirarVideo();
        }

        public void botonLike(){
            tv_titulo_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                        animacion(like);
                        like.setVisibility( View.VISIBLE );
                        like.playAnimation();
                    }
                    CUView.ultimoClick= SystemClock.elapsedRealtime();
                }
            });
            iv_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SystemClock.elapsedRealtime() - CUView.ultimoClick > CUView.TIEMPO_MINIMO){
                        animacion(like);
                        like.setVisibility( View.VISIBLE );
                        like.playAnimation();
                    }
                    CUView.ultimoClick= SystemClock.elapsedRealtime();
                }
            });
        }

        public void  animacion(LottieAnimationView animacion){
            animacion.addAnimatorListener( new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }
                @Override
                public void onAnimationEnd(Animator animator) {
                    like.setVisibility( View.GONE );
                }
                @Override
                public void onAnimationCancel(Animator animator) {
                }
                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            } );
        }

        public void mirarVideo(){
            try{
                iv_play.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                       ocultarViewsVideos();
                        playVideo();
                    }
                });
            }catch (Exception e){
                ((Activity)CUView.ContextAMenuUsuario).runOnUiThread(new Runnable(){
                    public void run() {
                        mVideoView.stopPlayback();
                        visibleViewsVideos();
                    }
                });
                mVideoView.stopPlayback();
                visibleViewsVideos();
            }
        }

        private void playVideo() {
            Uri uri=Uri.parse( path );
            try {
                MediaController mediaController=new MediaController( CUView.ContextAMenuUsuario );
                mVideoView.setMediaController(mediaController  );
                mVideoView.setVideoURI( uri );
                mVideoView.requestFocus();
                mediaController.setAnchorView( mVideoView );
                mVideoView.start();
            } catch (Exception e) {
                Log.e(TAG, "error: " + e.getMessage(), e);
                if (mVideoView != null) {
                    mVideoView.stopPlayback();
                    visibleViewsVideos();
                }else {
                    visibleViewsVideos();
                }
            }
        }

        private void visibleViewsVideos(){
            iv_preview_img.setVisibility( View.VISIBLE );
            iv_play.setVisibility( View.VISIBLE );
            iv_videocamara.setVisibility( View.VISIBLE );
            iv_puntos.setVisibility( View.VISIBLE );
            tv_nombre_grabacion.setVisibility( View.VISIBLE );
            tv_reproducciones.setVisibility( View.VISIBLE );
        }

        private void   ocultarViewsVideos(){
            iv_preview_img.setVisibility( View.GONE );
            iv_play.setVisibility( View.GONE );
            iv_videocamara.setVisibility( View.GONE );
            iv_puntos.setVisibility( View.GONE );
            tv_nombre_grabacion.setVisibility( View.GONE );
            tv_reproducciones.setVisibility( View.GONE );
        }

    }
}