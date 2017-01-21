package liam.example.com.weatherreport.base;

import android.app.Application;

import io.realm.Realm;
import liam.example.com.weatherreport.dagger.components.AppComponent;
import liam.example.com.weatherreport.dagger.components.DaggerAppComponent;
import liam.example.com.weatherreport.dagger.modules.AppModule;
import liam.example.com.weatherreport.dagger.modules.DataModule;


public class WeatherReportApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).dataModule(new DataModule()).build();
        Realm.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
