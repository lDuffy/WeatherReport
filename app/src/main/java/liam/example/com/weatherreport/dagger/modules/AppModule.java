package liam.example.com.weatherreport.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.base.WeatherReportApplication;

/**
 * Dependancy injection module for providing application wide objects
 */

@Module
public class AppModule {
    private WeatherReportApplication application;

    public AppModule(WeatherReportApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public WeatherReportApplication providesApplication() {
        return application;
    }

}


