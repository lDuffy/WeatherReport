package liam.example.com.weatherreport.rest;

import liam.example.com.weatherreport.dao.WeatherFeed;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@FunctionalInterface
public interface WeatherApi {

        @GET("data/2.5/forecast/")
        Observable<WeatherFeed> list(@Query("q") String city);

}
