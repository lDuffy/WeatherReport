package liam.example.com.weatherreport.components;

import dagger.Component;
import liam.example.com.weatherreport.MainPresenterTest;
import liam.example.com.weatherreport.dagger.PerActivity;
import liam.example.com.weatherreport.dagger.components.ActivityComponent;
import liam.example.com.weatherreport.dagger.components.AppComponent;

@PerActivity
@Component(modules = TestActivityModule.class, dependencies = {AppComponent.class})
public interface TestActivityComponent extends ActivityComponent{
    void inject(MainPresenterTest test);
}
