package liam.example.com.weatherreport.components;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;

@Module
public class TestActivityModule extends ActivityModule {
    public TestActivityModule(AppCompatActivity activity) {
        super(activity);
    }
}