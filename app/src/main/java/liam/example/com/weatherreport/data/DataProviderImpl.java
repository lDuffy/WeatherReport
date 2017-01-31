package liam.example.com.weatherreport.data;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.inject.Inject;

import com.google.gson.Gson;

import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.rest.WeatherApi;
import liam.example.com.weatherreport.utils.RestServiceUtil;
import rx.Observable;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class DataProviderImpl implements DataProvider {

    public static final String JSON = "feed.json";
    final WeatherApi weatherApi;
    final Context context;
    final Gson gson;

    @Inject
    public DataProviderImpl(Context context, WeatherApi weatherApi, Gson gson) {
        this.weatherApi = weatherApi;
        this.context = context;
        this.gson = gson;
    }

    private Observable<WeatherFeed> cachedFeed() {
        return Observable.just(getFeed());
    }

    private Observable<WeatherFeed> remoteFeed(Location location) {
        return weatherApi.list(location.getLatitude(),location.getLongitude()).retryWhen(RestServiceUtil.defaultRetry());
    }

    /**
     * Loads weather observables feed from cached and remote source.
     * @return first Observable<WeatherFeed> emission from either source. Cached content will be
     * replaced by network response once it becomes available. If network response comes in before cache,
     * observable will unsubscribe before returning cache. If no data is returned, error will eventually propogate.
     * @param location
     */

    @Override
    public Observable<WeatherFeed> loadWeatherFeed(Location location) {

        Observable<WeatherFeed> local = cachedFeed();
        Observable<WeatherFeed> remote = remoteFeed(location).doOnNext(this::saveFeed);

        return remote.publish(network -> Observable.merge(network, local.takeUntil(network)));
    }

    private void saveFeed(WeatherFeed weatherFeed) {
        try {
            FileOutputStream fileout = context.openFileOutput(JSON, MODE_PRIVATE);
            try (OutputStreamWriter outputWriter = new OutputStreamWriter(fileout)) {
                outputWriter.write(gson.toJson(weatherFeed));
                outputWriter.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "save feed error: ", e);
        }
    }

    private WeatherFeed getFeed() {
        StringBuilder temp = new StringBuilder();
        try {
            FileInputStream fin = context.openFileInput(JSON);
            int c;

            while (-1 != (c = fin.read())) {
                temp.append(Character.toString((char) c));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "read feed error: ", e);
            return null;
        } catch (IOException e) {
            Log.e(TAG, "read feed error: ", e);
            return null;
        }

        return gson.fromJson(temp.toString(), WeatherFeed.class);

    }
}