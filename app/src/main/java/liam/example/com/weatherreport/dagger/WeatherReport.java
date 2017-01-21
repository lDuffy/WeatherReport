package liam.example.com.weatherreport.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by lduf0001 on 18/01/2017.
 */
@Retention(RetentionPolicy.SOURCE)
@Qualifier
public @interface WeatherReport {
}
