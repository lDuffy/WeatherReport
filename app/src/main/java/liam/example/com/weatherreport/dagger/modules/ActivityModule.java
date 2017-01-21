package liam.example.com.weatherreport.dagger.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.home.MainContract;
import liam.example.com.weatherreport.home.MainPresenterImpl;
import liam.example.com.weatherreport.navigation.AppLauncher;
import liam.example.com.weatherreport.navigation.Launcher;
import liam.example.com.weatherreport.rest.WeatherApi;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    Launcher providesLauncher() {
        return new AppLauncher(activity);
    }

    @Provides
    MainContract.MainPresenter providesMainPresenter(WeatherApi weatherApi){
        return new MainPresenterImpl(weatherApi);
    }

}
