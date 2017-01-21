package liam.example.com.weatherreport.rest;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@FunctionalInterface
public interface WeatherApi {

        @GET("data/2.5/forecast/")
        Observable<String> list(@Query("q") String page);

}
