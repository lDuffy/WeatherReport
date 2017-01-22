package liam.example.com.weatherreport.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import liam.example.com.weatherreport.dagger.modules.AppModule;
import liam.example.com.weatherreport.dagger.modules.DataModule;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.utils.RxUtils;

/**
 * Component for application global objects
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    Context context();
    DataProvider dataProvider();
    RxUtils rxUtils();
}