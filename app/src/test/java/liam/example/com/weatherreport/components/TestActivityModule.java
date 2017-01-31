package liam.example.com.weatherreport.components;

import dagger.Module;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.home.MainActivity;

@Module
public class TestActivityModule extends ActivityModule {
    public TestActivityModule(MainActivity activity) {
        super(activity);
    }
}