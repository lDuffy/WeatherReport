package liam.example.com.weatherreport.utils;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {

    public <T> Observable.Transformer<T, T> newIoToMainTransformer() {
        return source -> source.subscribeOn(Schedulers.io()).observeOn(mainScheduler());
    }

    Scheduler mainScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
