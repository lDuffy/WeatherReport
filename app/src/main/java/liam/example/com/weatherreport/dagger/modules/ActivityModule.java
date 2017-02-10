package liam.example.com.weatherreport.dagger.modules;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.home.MainActivity;
import liam.example.com.weatherreport.home.MainContract;
import liam.example.com.weatherreport.home.MainPresenterImpl;
import liam.example.com.weatherreport.utils.LocationProvider;
import liam.example.com.weatherreport.utils.RxUtils;

@Module
public class ActivityModule {

    private final MainActivity activity;

    public ActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LocationProvider providesLocationhProvider(){
        return new LocationProvider(activity);
    }

    @Provides
    public MainContract.MainPresenter providesMainPresenter(DataProvider weatherApi, RxUtils rxUtils, LocationProvider locationProvider) {
        return new MainPresenterImpl(weatherApi, rxUtils, locationProvider);
    }

}
