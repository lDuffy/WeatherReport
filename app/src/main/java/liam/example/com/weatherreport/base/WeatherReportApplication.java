package liam.example.com.weatherreport.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import liam.example.com.weatherreport.dagger.components.AppComponent;
import liam.example.com.weatherreport.dagger.components.DaggerAppComponent;
import liam.example.com.weatherreport.dagger.modules.DataModule;


public class WeatherReportApplication extends Application {


    private static WeatherReportApplication instance;

    private AppComponent appComponent;

    public static boolean hasNetwork() {
        return instance.checkIfHasNetwork();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder().dataModule(new DataModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static WeatherReportApplication getInstance() {
        return instance;
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (null != networkInfo) && networkInfo.isConnected();
    }
}
