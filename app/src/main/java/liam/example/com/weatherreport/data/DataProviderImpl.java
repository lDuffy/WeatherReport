package liam.example.com.weatherreport.data;

import javax.inject.Inject;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.rest.RestServiceUtil;
import liam.example.com.weatherreport.rest.WeatherApi;
import rx.Observable;
import rx.schedulers.Schedulers;

public class DataProviderImpl implements DataProvider {

    RealmConfiguration configuration;
    WeatherApi weatherApi;

    @Inject
    public DataProviderImpl(RealmConfiguration configuration, WeatherApi weatherApi) {
        this.configuration = configuration;
        this.weatherApi = weatherApi;
    }

    private Observable<WeatherFeed> cachedFeed() {
        return Observable.create(subscriber -> {

            Realm realm = Realm.getInstance(configuration);
            WeatherFeed result = realm.where(WeatherFeed.class).findFirst();

            if (null != result) {
                subscriber.onNext(realm.copyFromRealm(result));
            } else {
                subscriber.onError(null);
            }

            realm.close();
            subscriber.onCompleted();

        });
    }

    private Observable<WeatherFeed> remoteFeed() {
        return weatherApi.list("Dublin").retryWhen(RestServiceUtil.defaultRetry());
    }

    /**
     * Loads weather observables feed from cached and remote source.
     * @return first Observable<WeatherFeed> emission from either source. Cached content will be
     * replaced by network response once it becomes available. If network response comes in before cache,
     * observable will unsubscribe before returning cache. If no data is returned, error will eventually propogate.
     */

    @Override
    @RxLogObservable
    public Observable<WeatherFeed> loadWeatherFeed() {

        Observable<WeatherFeed> local = cachedFeed().subscribeOn(Schedulers.io());
        Observable<WeatherFeed> remote = remoteFeed().doOnNext(this::saveFeed).subscribeOn(Schedulers.io());

        return remote.publish(network -> Observable.merge(network, local.takeUntil(network)));
    }

    private void saveFeed(WeatherFeed weatherFeed) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(weatherFeed);
        realm.commitTransaction();
        realm.close();
    }

}