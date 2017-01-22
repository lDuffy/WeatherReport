package liam.example.com.weatherreport.home;

import liam.example.com.weatherreport.data.DataProvider;
import liam.example.com.weatherreport.rest.RxUtils;
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
                .subscribe(mainView::populateList,
                        throwable -> mainView.showToast(throwable.toString()),
                        () -> mainView.setProgressVisible(false));
    }


}
