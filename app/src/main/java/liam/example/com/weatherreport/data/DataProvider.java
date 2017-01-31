package liam.example.com.weatherreport.data;


import android.location.Location;

import liam.example.com.weatherreport.dao.WeatherFeed;
import rx.Observable;

@FunctionalInterface
public interface DataProvider {

    Observable<WeatherFeed> loadWeatherFeed(final Location location);
}
