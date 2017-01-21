package rx.subjects;

/**
 * Created by lduf0001 on 21/01/2017.
 */

public class PublishSubject <T> extends Subject<T, T> {


    protected PublishSubject(OnSubscribe<T> onSubscribe) {
        super(null);
    }

    public <T> rx.subjects.PublishSubject<T> create() {
        return null;
    }

    @Override
    public boolean hasObservers() {
        return false;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
