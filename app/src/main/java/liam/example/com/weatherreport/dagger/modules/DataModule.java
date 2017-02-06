package liam.example.com.weatherreport.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import liam.example.com.weatherreport.base.WeatherReportApplication;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.data.DataProviderImpl;
import liam.example.com.weatherreport.rest.WeatherApi;
import liam.example.com.weatherreport.utils.RxUtils;

import static liam.example.com.weatherreport.utils.RetrofitUtils.provideRetrofit;

/**
 * Module for rest api code
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    public DataProvider providesLocalDataProvider(WeatherApi weatherApi) {
        return new DataProviderImpl(weatherApi);
    }

    @Provides
    @Singleton
    public WeatherApi providesApi(WeatherReportApplication application) {
        return provideRetrofit(application).create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public RxUtils providesRxUtils() {
        return new RxUtils();
    }

}
