package liam.example.com.weatherreport.data;

import android.location.Location;

import javax.inject.Inject;

import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.rest.WeatherApi;
import rx.Observable;

import static liam.example.com.weatherreport.utils.RetrofitUtils.defaultRetry;

public class DataProviderImpl implements DataProvider {

    final WeatherApi weatherApi;

    @Inject
    public DataProviderImpl(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Observable<WeatherFeed> loadWeatherFeed(Location location) {
        return weatherApi.list(location.getLatitude(),location.getLongitude()).retryWhen(defaultRetry());
    }
}