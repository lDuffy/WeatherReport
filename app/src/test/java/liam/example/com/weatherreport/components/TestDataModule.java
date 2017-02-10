package liam.example.com.weatherreport.components;

import org.mockito.Mockito;

import liam.example.com.weatherreport.base.WeatherReportApplication;
import liam.example.com.weatherreport.dagger.modules.DataModule;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.rest.WeatherApi;
import liam.example.com.weatherreport.utils.RxUtils;

/*
 * Module extending DataModule that returns Mock implementations of classes for testing.
 */
public class TestDataModule extends DataModule {

    @Override
    public WeatherApi providesApi(WeatherReportApplication application) {
        return Mockito.mock(WeatherApi.class);
    }

    @Override
    public DataProvider providesLocalDataProvider(WeatherApi weatherApi) {
        return Mockito.mock(DataProvider.class);
    }

    @Override
    public RxUtils providesRxUtils() {
        return Mockito.mock(RxUtils.class);
    }

}
