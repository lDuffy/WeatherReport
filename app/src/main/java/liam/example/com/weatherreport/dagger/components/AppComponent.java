package liam.example.com.weatherreport.dagger.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.RealmConfiguration;
import liam.example.com.weatherreport.dagger.modules.AppModule;
import liam.example.com.weatherreport.dagger.modules.RestModule;
import liam.example.com.weatherreport.rest.WeatherApi;

/**
 * Component for application global objects
 */

@Singleton
@Component(modules = {AppModule.class, RestModule.class})
public interface AppComponent {
    Context context();
    WeatherApi api();
    RealmConfiguration realmConfig();
}