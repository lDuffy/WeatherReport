package liam.example.com.weatherreport.home;

import android.util.Log;
import android.view.View;

import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.rest.WeatherApi;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenterImpl implements MainContract.MainPresenter {
    MainContract.MainView mainView;

    final WeatherApi weatherApi;
    public MainPresenterImpl(WeatherApi weatherApi){
        this.weatherApi = weatherApi;
    }

    @Override
    public void onViewAttached(MainContract.MainView mainView) {
        this.mainView=mainView;
    }

    @Override
    public void onViewDetached() {
        mainView=null;
    }

    @Override
    public void fetchDate() {
        weatherApi.list("Dublin")
                .subscribeOn(Schedulers.io())
                .observeOn(mainScheduler())
                .subscribe(this::setResponse,
                        throwable -> mainView.showToast(throwable.toString()),
                        () -> mainView.setProgressVisible(View.GONE));

    }

    private void setResponse(WeatherFeed feed) {
        Log.d("setResponse", "setResponse: ");
    }

    public Scheduler mainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
