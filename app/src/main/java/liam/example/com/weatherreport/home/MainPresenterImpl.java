package liam.example.com.weatherreport.home;

import android.location.Location;
import android.view.View;

import java.util.List;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.utils.DateTimeUtils;
import liam.example.com.weatherreport.utils.LocationProvider;
import liam.example.com.weatherreport.utils.RxUtils;
import rx.Subscription;

public class MainPresenterImpl implements MainContract.MainPresenter, LocationProvider.LocationCallback {

    final DataProvider weatherApi;
    final RxUtils rxUtils;
    MainContract.MainView mainView;
    Subscription subscription;
    private LocationProvider locationProvider;

    public MainPresenterImpl(DataProvider weatherApi, RxUtils rxUtils) {
        this.rxUtils = rxUtils;
        this.weatherApi = weatherApi;

    }

    @Override
    public void onViewAttached(MainContract.MainView mainView) {
        this.mainView = mainView;
        locationProvider = new LocationProvider(mainView, this);
        locationProvider.connect();
    }

    @Override
    public void onViewDetached() {
        locationProvider.disconnect();
        if ((null != subscription) && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        mainView = null;
    }

    @Override
    public void fetchDate() {
        locationProvider.onConnected(null);
    }

    private void fetchDate(Location location) {
        mainView.setProgressVisible(View.VISIBLE);
        subscription = weatherApi.loadWeatherFeed(location)
                .compose(rxUtils.newIoToMainTransformer())
                .subscribe(this::sortResultsAndPopulateList,
                        this::setError,
                        () -> mainView.setProgressVisible(View.GONE));
    }

    void sortResultsAndPopulateList(WeatherFeed result) {
        if (null != result) {
            List<Day> datesByDay = DateTimeUtils.getDaysFromFeed(result);
            if (null != mainView) {
                mainView.populateList(result.getList());
            }
        }
    }

    void setError(Throwable throwable) {
        if (null != mainView) {
            mainView.showToast(throwable.toString());
            mainView.setProgressVisible(View.GONE);
        }
    }

    @Override
    public void handleNewLocation(Location location) {
        fetchDate(location);
    }

    @Override
    public void handleLocationError(Throwable error) {
        setError(error);
    }

}
