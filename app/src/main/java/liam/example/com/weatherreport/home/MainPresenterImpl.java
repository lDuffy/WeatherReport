package liam.example.com.weatherreport.home;

import java.util.List;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.dao.WeatherFeed;
import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.utils.RxUtils;
import liam.example.com.weatherreport.utils.DateTimeUtils;
import rx.subjects.PublishSubject;

public class MainPresenterImpl implements MainContract.MainPresenter {

    final PublishSubject<Boolean> onDestroySubject;
    final DataProvider weatherApi;
    final RxUtils rxUtils;
    MainContract.MainView mainView;

    public MainPresenterImpl(DataProvider weatherApi, RxUtils rxUtils) {
        this.rxUtils = rxUtils;
        this.weatherApi = weatherApi;
        onDestroySubject = PublishSubject.create();
    }

    @Override
    public void onViewAttached(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onViewDetached() {
        onDestroySubject.onNext(true);
        mainView = null;
    }

    @Override
    public void fetchDate() {
        mainView.setProgressVisible(true);
        weatherApi.loadWeatherFeed()
                .compose(rxUtils.newOnDestroyTransformer(onDestroySubject))
                .compose(rxUtils.newIoToMainTransformer())
                .subscribe(this::sortResultsAndPopulateList,
                        this::setError,
                        () -> mainView.setProgressVisible(false));
    }

    void sortResultsAndPopulateList(WeatherFeed result) {
        List<Day> datesByDay = DateTimeUtils.getDaysFromFeed(result);
        mainView.populateList(datesByDay);
    }

    void setError(Throwable throwable) {
        mainView.showToast(throwable.toString());
        mainView.setProgressVisible(false);
    }

}
