package liam.example.com.weatherreport.dagger.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.navigation.AppLauncher;
import liam.example.com.weatherreport.navigation.Launcher;

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


}
