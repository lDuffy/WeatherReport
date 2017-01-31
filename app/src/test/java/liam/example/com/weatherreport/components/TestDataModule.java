package liam.example.com.weatherreport.components;

import android.app.Application;

import org.mockito.Mockito;

import com.google.gson.Gson;

import liam.example.com.weatherreport.dagger.WeatherReport;
import liam.example.com.weatherreport.dagger.modules.DataModule;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.rest.WeatherApi;
import liam.example.com.weatherreport.utils.RxUtils;
import retrofit2.Retrofit;

/*
 * Module extending DataModule that returns Mock implementations of classes for testing.
 */
public class TestDataModule extends DataModule {

    @Override
    public WeatherApi providesApi(@WeatherReport Retrofit restAdapter) {
        return Mockito.mock(WeatherApi.class);
    }

    @Override
    public DataProvider providesLocalDataProvider(Application application, WeatherApi weatherApi, Gson gson) {
        return Mockito.mock(DataProvider.class);
    }

    @Override
    public RxUtils providesRxUtils() {
        return Mockito.mock(RxUtils.class);
    }

}
