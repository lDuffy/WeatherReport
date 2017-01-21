package liam.example.com.weatherreport.dagger.components;

import dagger.Component;
import liam.example.com.weatherreport.dagger.PerActivity;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.home.MainActivity;
import liam.example.com.weatherreport.home.MainFragment;

/**
 * Activity component used in dependancy injection.
 */

@PerActivity
@Component(modules = ActivityModule.class, dependencies = {AppComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
}