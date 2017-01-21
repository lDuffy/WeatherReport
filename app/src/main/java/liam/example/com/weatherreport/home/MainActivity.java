package liam.example.com.weatherreport.home;

import android.os.Bundle;

import javax.inject.Inject;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.base.InjectedActivity;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.DaggerActivityComponent;
import liam.example.com.weatherreport.dagger.modules.ActivityModule;
import liam.example.com.weatherreport.navigation.Launcher;

public class MainActivity extends InjectedActivity<ActivityComponent> {

    @Inject Launcher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_main);
        launcher.openHome();
    }


    @Override
    public ActivityComponent getComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(getAppComponent())
                .build();
    }
}
