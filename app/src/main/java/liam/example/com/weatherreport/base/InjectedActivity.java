package liam.example.com.weatherreport.base;

import android.support.v7.app.AppCompatActivity;

import liam.example.com.weatherreport.dagger.components.AppComponent;
import liam.example.com.weatherreport.dagger.components.ComponentProvider;

/**
 * Any activity intending on using dependency injection inherits from this class.
 * InjectedActivity reduces the boilerplate code of attaching the app component
 * to the activity component by providing it in getAppComponent()
 */

public abstract class InjectedActivity<T> extends AppCompatActivity
        implements ComponentProvider<T> {

    protected AppComponent getAppComponent() {
        WeatherReportApplication application = (WeatherReportApplication) getApplication();
        return application.getAppComponent();
    }
}
