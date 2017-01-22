package liam.example.com.weatherreport.dagger.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.home.MainContract;
import liam.example.com.weatherreport.home.MainPresenterImpl;
import liam.example.com.weatherreport.utils.RxUtils;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    public MainContract.MainPresenter providesMainPresenter(DataProvider weatherApi, RxUtils rxUtils){
        return new MainPresenterImpl(weatherApi, rxUtils);
    }

}
