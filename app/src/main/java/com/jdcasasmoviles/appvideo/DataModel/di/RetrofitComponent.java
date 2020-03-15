package com.jdcasasmoviles.appvideo.DataModel.di;
import com.jdcasasmoviles.appvideo.Views.MenuPrincipal;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(MenuPrincipal menuPrincipal);
}