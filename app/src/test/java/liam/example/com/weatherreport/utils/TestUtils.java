package liam.example.com.weatherreport.utils;

import android.support.annotation.NonNull;

import io.realm.RealmList;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.dao.WeatherListItem;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class TestUtils {

    @NonNull
    public static WeatherFeed getTestWeatherFeed() {
        RealmList<WeatherListItem> items = new RealmList<>();
        for (int i = 0; 3 > i; i++) {
            items.add(new WeatherListItem(System.currentTimeMillis() + i));
            items.add(new WeatherListItem(System.currentTimeMillis() * i));
        }

        return new WeatherFeed(items);
    }

    public static <T> Observable.Transformer<T, T> newIoToMainTransformer() {
        return source -> source.subscribeOn(Schedulers.immediate()).observeOn(Schedulers.immediate());
    }

    public static <T> Observable.Transformer<T, T> newOnDestroyTransformer(PublishSubject<Boolean> onDestroySubject) {
        return source -> source.takeUntil(onDestroySubject);
    }
}
